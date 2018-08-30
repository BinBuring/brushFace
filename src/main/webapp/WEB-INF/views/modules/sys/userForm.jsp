<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

	<head>
		<title>用户管理</title>
		<meta name="decorator" content="default" />
		<link rel="stylesheet" href="${ctxStatic}/new-templates/css/index.css">
		<style>
			.chang {
				height: 40px!important;
			}
			.big_bottom select,.select2-choice{
				height: 42px!important;
				line-height: 42px!important;
				padding: 0!important;
			}
			.big_bottom option{
				
			}
			#type span{
				display: inline-block!important;
				width: 80px!important;
			}
			#type label{
				width: auto;
			}
			#type label:first-child{
				width: 120px!important;
			}
			#type .required{
				height: 20px;
				width: 20px;
				margin-top: 35px;
				float: left;
			}
		</style>
		
	</head>

	<body>
		<ul class="nav nav-tabs">
			<li>
				<a href="${ctx}/sys/user/list">用户列表</a>
			</li>
			<li class="active">
				<a href="${ctx}/sys/user/form?id=${user.id}">用户
					<shiro:hasPermission name="sys:user:edit">${not empty user.id?'修改':'添加'}</shiro:hasPermission>
					<shiro:lacksPermission name="sys:user:edit">查看</shiro:lacksPermission>
				</a>
			</li>
		</ul><br/>
		<div class="workcontent add_big" style="display: block;">
			<div class="big_top">
				<div class="operation fl">
					<div>用户信息</div>
				</div>
			</div>
			<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/save" method="post" class="form-horizontal">
				<div class="big_bottom">
					<div >
						<label>用户登录名：</label>
						<input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
						<form:input path="loginName" htmlEscape="false" maxlength="50" class="scInput chang" placeholder="请输入用户登录名" />
					</div>
					<div >
						<label>用户名：</label>
						<input id="name" name="name" type="hidden" value="${user.name}">
						<form:input path="name" htmlEscape="false" maxlength="50" class="scInput chang" placeholder="请输入用户登录名" />
					</div>
					<div id="type">
						<label>用户类型：</label>
						<form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="name" itemValue="id" htmlEscape="false" class="required"/>
					</div>	
					<div >
						<label>用户密码：</label>
						<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="scInput chang" placeholder="请输入用户密码" />
						<label>确认用户密码：</label>
						<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#newPassword" class="scInput chang" placeholder="请输入确认密码" />
					</div>
					<div class="enlogin">
						<label >是否允许登录:</label>
							<form:select path="loginFlag" class="scInput chang" style="width: 70px;">
								<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
							<span class="help-inline" style="height: 42px;display: inline-block;margin-top: 15px;line-height: 50px;"></span>
					</div>
					<div class="button">
						<shiro:hasPermission name="sys:user:edit"><input type="submit" class="confirm fl" value="确认"></shiro:hasPermission>
						<input type="button" class="return fr" onclick="history.go(-1)" value="返回">
					</div>
				</div>
			</form:form>
		</div>
	</body>
	<script type="text/javascript">
			$(document).ready(function() {
				$("#no").focus();
				$("#inputForm").validate({
					rules: {
						loginName: {
							remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')
						}
					},
					messages: {
						loginName: {
							remote: "用户登录名已存在"
						},
						confirmNewPassword: {
							equalTo: "输入与上面相同的密码"
						}
					},
					submitHandler: function(form) {
						loading('正在提交，请稍等...');
						form.submit();
					},
					errorContainer: "#messageBox",
					errorPlacement: function(error, element) {
						$("#messageBox").text("输入有误，请先更正。");
						if(element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
							error.appendTo(element.parent().parent());
						} else {
							error.insertAfter(element);
						}
					}
				});
			});
		</script>
</html>