<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>机构管理</title>
<meta name="decorator" content="default" />
<%-- <%@include file="/WEB-INF/views/include/treetable.jsp"%> --%>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var tpl = $("#treeTableTpl").html().replace(
								/(\/\/\<!\-\-)|(\/\/\-\->)/g, "");
						var data = ${fns:toJson(list)}, rootId = "${not empty office.id ? office.id : '0'}";
						addRow("#treeTableList", tpl, data, rootId, true);
						$("#treeTable").treeTable({
							expandLevel : 5
						});
					});
	function addRow(list, tpl, data, pid, root) {
		for (var i = 0; i < data.length; i++) {
			var row = data[i];
			if ((${fns:jsGetVal('row.parentId')}) == pid) {
				$(list)
						.append(
								Mustache
										.render(
												tpl,
												{
													dict : {
														type : getDictLabel(
																${fns:toJson(fns:getDictList('sys_office_type'))},
																row.type)
													},
													pid : (root ? 0 : pid),
													row : row
												}));
				addRow(list, tpl, data, row.id);
			}
		}
	}
	function page(n,s){
		if(n) $("#pageNo").val(n);
		if(s) $("#pageSize").val(s);
		$("#searchForm").attr("action","${ctx}/sys/office/list");
		$("#searchForm").submit();
    	return false;
    }
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/sys/office/list?id=${office.id}&parentIds=${office.parentIds}">部门列表</a></li>
		<shiro:hasPermission name="sys:office:edit">
			<li><a href="${ctx}/sys/office/form?parent.id=${office.id}">部门添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="office"
		action="${ctx}/sys/office/list" method="post"
		class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}"
			callback="page();" />
		<ul class="ul-form">
			<li><label>部门名称：</label> 
			<form:input path="name" htmlEscape="false" maxlength="50" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" onclick="return page();" />
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="treeTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>一级部门</th>
				<th>二级部门</th>
				<shiro:hasPermission name="sys:office:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList">
			<c:forEach items="${page.list}" varStatus="status" var="office">
				<tr>
					<td>${status.count}</td>
					<td>${office.parent.name}</td>
					<td>${office.name}</td>
					<shiro:hasPermission name="sys:office:edit">
						<td><a href="${ctx}/sys/office/form?id=${office.id}">修改</a> <a
							href="${ctx}/sys/office/delete?id=${office.id}"
							onclick="return confirmx('要删除该机构及所有子机构项吗？', this.href)">删除</a> <%-- <a href="${ctx}/sys/office/form?parent.id={{row.id}}">添加下级机构</a>  --%>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>