<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>记录管理</title>
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
		<li class="active"><a href="${ctx}/brf/empRecord/">记录列表</a></li>
		<shiro:hasPermission name="brf:empRecord:edit"><li><a href="${ctx}/brf/empRecord/form">记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="empRecord" action="${ctx}/brf/empRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>工号：</label>
				<form:input path="empNo" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="empName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>部门：</label>
				<form:input path="offName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>识别时间：</label>
				<input name="beginShowtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${empRecord.beginShowtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endShowtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${empRecord.endShowtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<%-- <li><label>识别模式</label>
				<form:input path="recmode" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>员工工号</th>
				<th>员工姓名</th>
				<th>员工部门</th>
				<th>员工识别图片</th>
				<th>识别时间</th>
				<th>识别设备</th>
				<th>识别模式</th>
				<shiro:hasPermission name="brf:empRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="empRecord">
			<tr>
				<td><a href="${ctx}/brf/empRecord/form?id=${empRecord.id}">
					${empRecord.emp.no}
				</a></td>
				<td>
					${empRecord.emp.name}
				</td>
				<td>
					${empRecord.offName}
				</td>
				<td>
					<img src="${empRecord.photourl}" style="width: 50px;height: 50px;">
				</td>
				<td>
					<fmt:formatDate value="${empRecord.showtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${empRecord.dev.aisle}
				</td>
				<td>
					${fns:getDictLabel(empRecord.recmode, "recmode", "")}
				</td>
				<shiro:hasPermission name="brf:empRecord:edit"><td>
    				<a href="${ctx}/brf/empRecord/form?id=${empRecord.id}">修改</a>
					<a href="${ctx}/brf/empRecord/delete?id=${empRecord.id}" onclick="return confirmx('确认要删除该记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>