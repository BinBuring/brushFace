<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="decorator" content="default"/>
    <link rel="stylesheet" href="${ctxStatic}/new-templates/css/index.css">
    <script src="${ctxStatic}/new-templates/js/jquery-3.3.1.min.js"></script>
    <script src="${ctxStatic}/new-templates/js/dis_dev.js"></script>
    <style>
    	.scInput{
    		padding: 0!important;
    	}
    	.big_bottom .chang{
    		height: 45px!important;
    	}
    </style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/brf/device/">设备列表</a></li>
		<li class="active"><a href="${ctx}/brf/device/form?id=${device.id}">设备<shiro:hasPermission name="brf:device:edit">${not empty device.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="brf:device:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="device" action="${ctx}/brf/device/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
<div class="workcontent add_big" style="display: block;">
    <div class="big_top">
        <div class="operation fl">
            <div>添加设备</div>
        </div>
    </div>
    <div class="big_bottom">
        <div>
            <label>终端序列号：</label>
            <form:input path="seq" htmlEscape="false" maxlength="255"  class="scInput chang required" placeholder="请输入设备序列号"/>
            
        </div>
        <div>
        	<label>设备名称：</label>
            <form:input path="name" htmlEscape="false" maxlength="255" class="scInput chang " placeholder="请输入设备名称"/>
			<form:hidden path="startus" value="1" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
        <div>
            <label>识别设备：</label>
            <form:input path="aisle" htmlEscape="false" maxlength="16"  class="scInput chang" placeholder="请输入识别设备"/>
           
        </div>
        <div>
        	 <label>版本号：</label>
        	 <form:input path="ver" htmlEscape="false" maxlength="255" class="scInput chang" placeholder="请输入版本号"/>
        </div>
        <div class="button">
        	<shiro:hasPermission name="brf:device:edit">
				<input id="btnSubmit" class="confirm fl" type="submit"value="确 认" />&nbsp;
			</shiro:hasPermission>
            <input type="button" class="return fr" onclick="history.go(-1)" value="返 回">
        </div>
       </div>
</div>
</form:form>
</body>
</html>