<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Title</title>
		<link rel="stylesheet" href="${ctxStatic}/new-templates/css/index.css">
		<meta name="decorator" content="default" />
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<script src="${ctxStatic}/new-templates/js/user_list.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#btnExport").click(function() {
					top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function(v, h, f) {
						if(v == "ok") {
							$("#searchForm").attr("action", "${ctx}/sys/user/export");
							$("#searchForm").submit();
						}
					}, {
						buttonsFocus: 1
					});
					top.$('.jbox-body .jbox-icon').css('top', '55px');
				});
				$("#btnImport").click(function() {
					$.jBox($("#importBox").html(), {
						title: "导入数据",
						buttons: {
							"关闭": true
						},
						bottomText: "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
					});
				});
			});

			function page(n, s) {
				if(n) $("#pageNo").val(n);
				if(s) $("#pageSize").val(s);
				$("#searchForm").attr("action", "${ctx}/sys/user/list");
				$("#searchForm").submit();
				return false;
			}
		</script>

		<style>
			#result tr {
				height: 40px;
			}
			#searchForm{
			padding: 0!important;
		}
		</style>
	</head>

	<body>
		<!--提示-->
		<div class="tishi"></div>
		<div class="qrcode_popup popup">
			<div class="pop_center">
				<div class="pc_top">
					<span class="fl">提示</span>
					<span class="fr close"><img src="${ctxStatic}/new-templates/img/close.png" alt=""></span>
				</div>
				<div class="pc_bottom">
					<p style="text-align: center"></p>
					<input type="button" class="query fr" value="确定">
				</div>
			</div>
		</div>
		<div class="workcontent mod_big">
			<div class="big_top">
				<div class="operation fl">
					<div>用户信息</div>
				</div>
			</div>
			<div class="big_bottom">
				<div>
					<label>用户登录名：</label>
					<input type="text" maxlength="16" value="" class="scInput chang" placeholder="请输入用户登录名">
					<label>用户类型：</label>
					<input type="text" maxlength="16" value="" class="scInput chang" placeholder="管理员">
				</div>
				<div class="button">
					<input type="button" class="confirm fl" value="确认修改">
					<input type="button" class="return fr" value="返回">
				</div>
			</div>
		</div>
		<div class="workcontent add_big">
			<div class="big_top">
				<div class="operation fl">
					<div>用户信息</div>
				</div>
			</div>
			<div class="big_bottom">
				<div>
					<label>用户登录名：</label>
					<input type="text" maxlength="16" value="" class="scInput chang" placeholder="请输入用户登录名">
					<label>用户类型：</label>
					<input type="text" maxlength="16" value="" class="scInput chang" placeholder="管理员">
				</div>
				<div>
					<label>用户密码：</label>
					<input type="text" maxlength="16" value="" class="scInput chang" placeholder="请输入用户密码">
					<label>确认用户密码：</label>
					<input type="text" maxlength="16" value="" class="scInput chang" placeholder="请输入确认密码">
				</div>
				<div class="button">
					<input type="button" class="confirm fl" value="确认">
					<input type="button" class="return fr" value="返回">
				</div>
			</div>
		</div>
		<div class="workcontent all_big">
			<div class="c_top">
				<div class="operation fl">
					<shiro:hasPermission name="sys:user:edit">
						<div class="add">添加</div>
					</shiro:hasPermission>

					<div class="delete">删除</div>
				</div>
				<!-- <div class="information fr">
            <label>部门名称：</label>
            <input type="text" maxlength="16" value="" class="scInput chang" placeholder="请输入部门名称">
            <input type="button" class="query" value="查询">
        </div>-->
			</div>
			<div class="c_bottom">
				<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/list" method="post" class="breadcrumb form-search ">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
					<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();" />
				</form:form>
				<table class="tableList">
					<tbody>
						<tr>
							<th width="6%">
								<div class="radio i-checks">
									<input id="color-input-red" class="chat" type="checkbox" name="color-input-red" value="#f0544d" onchange="selectAll(this)" />
									<label for="color-input-red"></label >
                    </div>
                </th>
                <th width="6%">序号</th>
                <th width="18%">用户登录名</th>
                <th width="22%">姓名</th>
                <th width="22%">创建时间</th>
                <th width="12%">操作</th>
            </tr>

            </tbody>
        </table>
        <div class="result">
               <table class="tableitem">
                <tbody id="result">
                <c:forEach items="${page.list}" var="user" varStatus="status">	
               <tr>
                    <th width="6%">
                        <div class="radio i-checks">
                            <input id="i${status.count}" userid="${user.id}" class="chat" type="checkbox" name="userSelect" value="#f0544d" />
                            <label  for="i${status.count}"></label >
                        </div>
                    </th>
                    <th width="6%">${status.count}</th>
                    <th width="18%">${user.loginName}</th>
                    <th width="22%">${user.name}</th>
                    <th width="22%"><fmt:formatDate value="${user.createDate}" type="both" dateStyle="full"/></th>
                    	<shiro:hasPermission name="sys:user:edit"><th width="12%">
    				<a href="${ctx}/sys/user/form?id=${user.id}">编辑</a>
					<%-- <a href="javascript:;" onclick="delUser('${user.id}')" >删除</a> --%>
				</th></shiro:hasPermission>
                </tr>
                </c:forEach>
                </tbody>
               </table>
        </div>
    </div>
</div>
	<script>
		$(function () {
		$(".add").click(function () {
        window.location.href = "${ctx}/sys/user/form"
   				 })		
   				 
   		$(".delete").click(function () {
   			var ids = []
   			$.each($("input[name='userSelect']"), function() {
   					if($(this).prop("checked"))
					ids.push( $(this).attr("userid"))
				});
			if(ids.length<=0){
				alert('请至少选择一项')
				return;
			}
			if(confirm('确认删除这些用户吗？')){
   				$.each(ids, function(i,val) {
   					$.getJSON('${ctx}/sys/user/delete?id=' + val)
   				});
   				setTimeout(function () {
   					window.location.reload();
   				},300)
   				
   			}
   		})		 
		})
		
		function delUser (id) {
			if(confirm('确认删除该用户吗？')){
				$.getJSON('${ctx}/sys/user/delete?id=' + id)
				setTimeout(function () {
   					window.location.reload();
   				},300)
			}
		}
		
		function selectAll(sel) {
			if($(sel).is(':checked')){
				$.each($("input[name='userSelect']"), function() {
					$(this).prop("checked",true) 
				});
			}else{
				$.each($("input[name='userSelect']"), function() {
					$(this).prop("checked",false) 
				});
			}
		}
		
		
	</script>
</body>
</html>
