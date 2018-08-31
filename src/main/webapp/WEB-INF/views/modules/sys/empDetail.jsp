<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>员工管理</title>
		<link rel="stylesheet" href="${ctxStatic}/new-templates/css/index.css">
		<meta name="decorator" content="default" />
	</head>
	<body>
<!--查看-->
<div class="workcontent view_big">
    <div class="big_top">
        <div class="operation fl">
            <div>个人信息</div>
        </div>
    </div>
    <div class="big_bottom short">
        <div>
            <label>工号：</label>
            <input type="text" value="${user.no }" readonly="readonly" class="scInput worknum">
            <label>部门：</label>
            <input type="text" value="${user.office.name }" readonly="readonly" class="scInput depart">
        </div>
        <div>
            <label>姓名：</label>
            <input type="text" value="${user.name }" readonly="readonly"  class="scInput name">
            <label>员工类型：</label>
            <input type="text" value="${fns:getDictLabel(user.userType, 'sys_user_type', '')}" readonly="readonly"  class="scInput type">
        </div>
        <div>
            <label>电话号：</label>
            <input type="text" value="${user.phone }" readonly="readonly" class="scInput phone">
            <label>有效开始时间：</label>
            <input type="text" value="<fmt:formatDate value="${user.createDate}" type="both" />" readonly="readonly" class="scInput starttime">
        </div>
        <div>
            <label>身份证号：</label>
            <input type="text" value="${user.idCard }" readonly="readonly" class="scInput idnum">
            <label>有效结束时间：</label>
            <input type="text" value="<fmt:formatDate value="${user.endDate}" type="both" />" readonly="readonly" class="scInput effective">
        </div>
    </div>
    <div class="big_top">
        <div class="operation">
            <div>照片</div>
        </div>
    </div>
    <div class="upload_img">
        <div class="left fl">
            <img src="${user.photo}" >
        </div>
    </div>
    <div class="big_top">
        <div class="operation">
            <div>授权设备</div>
        </div>
    </div>
    <div class="big_bottom">
    	<c:forEach items="${list }" var="sub">
        <div>
            <label>设备名称：</label>
            <input type="text" readonly="readonly" value="${sub.device.name }" class="scInput worknum">
            <label>识别设备：</label>
            <input type="text" readonly="readonly" value="${sub.device.aisle }" class="scInput depart">
        </div>
    	</c:forEach>
    </div>
    <div class="big_bottom">
    <p class="fr goback"><a href="javascript:history.go(-1)" >返回</a></p>
	</div>
</div>
	</body>
</html>
