<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>授权管理</title>
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
		<li class="active"><a href="${ctx}/brf/devUser/">授权列表</a></li>
		<shiro:hasPermission name="brf:devUser:edit"><li><a href="${ctx}/brf/devUser/form">授权添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="devUser" action="${ctx}/brf/devUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户id：</label>
				<sys:treeselect id="user" name="user.id" value="${devUser.user.id}" labelName="user.name" labelValue="${devUser.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>设备id：</label>
				<form:input path="devId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>授权时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${devUser.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${devUser.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户id</th>
				<th>设备id</th>
				<shiro:hasPermission name="brf:devUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="devUser">
			<tr>
				<td><a href="${ctx}/brf/devUser/form?id=${devUser.id}">
					${devUser.user.name}
				</a></td>
				<td>
					${devUser.devId}
				</td>
				<shiro:hasPermission name="brf:devUser:edit"><td>
    				<a href="${ctx}/brf/devUser/form?id=${devUser.id}">修改</a>
					<a href="${ctx}/brf/devUser/delete?id=${devUser.id}" onclick="return confirmx('确认要删除该授权吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>