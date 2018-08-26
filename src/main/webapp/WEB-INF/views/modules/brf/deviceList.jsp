<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理</title>
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
		<li class="active"><a href="${ctx}/brf/device/">设备列表</a></li>
		<shiro:hasPermission name="brf:device:edit"><li><a href="${ctx}/brf/device/form">设备添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="device" action="${ctx}/brf/device/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备序列号：</label>
				<form:input path="seq" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<%-- <li><label>设备名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>识别设备：</label>
				<form:input path="aisle" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>设备序列号</th>
				<th>设备名称</th>
				<th>识别设备</th>
				<th>版本号</th>
				<th>创建时间</th>
				<shiro:hasPermission name="brf:device:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="device">
			<tr>
				<td><a href="${ctx}/brf/device/form?id=${device.id}">
					${device.seq}
				</a></td>
				<td>
					${device.name}
				</td>
				<td>
					${device.aisle}
				</td>
				<td>
					${device.ver}
				</td>
				<td>
					<fmt:formatDate value="${device.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="brf:device:edit"><td>
    				<a href="${ctx}/brf/device/form?id=${device.id}">修改</a>
					<a href="${ctx}/brf/device/delete?id=${device.id}" onclick="return confirmx('确认要删除该设备吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>