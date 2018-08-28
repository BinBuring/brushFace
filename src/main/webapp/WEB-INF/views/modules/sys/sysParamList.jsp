<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>参数管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/sysParam/">参数列表</a></li>
		<li><a href="${ctx}/sys/sysParam/form">参数添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sysParam" action="${ctx}/sys/sysParam/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<!-- <ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> -->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>参数类型</th>
				<th>参数内容</th>
				<th>修改时间</th>
				<shiro:hasPermission name="sys:sysParam:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysParam">
			<tr>
				<td><a href="${ctx}/sys/sysParam/form?id=${sysParam.id}">
					${sysParam.type}
				</a></td>
				<td>
					${sysParam.content}
				</td>
				<td>
					<fmt:formatDate value="${sysParam.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/sys/sysParam/form?id=${sysParam.id}">修改</a>
					<a href="${ctx}/sys/sysParam/delete?id=${sysParam.id}" onclick="return confirmx('确认要删除该参数吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>