<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="UTF-8">
		<title>用户管理</title>
		<link rel="stylesheet" href="${ctxStatic}/new-templates/css/index.css">
		<script src="${ctxStatic}/new-templates/js/jquery-3.3.1.min.js"></script>
		<script src="${ctxStatic}/new-templates/js/home.js"></script>
		<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>
		<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
		<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>

		<style>
			.blue a {
				color: #26b2f0;
				text-decoration: none;
			}
			
			.scInput {
				padding: 0!important;
			}
			#messageBox{
				display: none!important;
			}
			.pagination {
				margin: 8px 0;
			}
			
			.pagination ul {
				display: inline-block;
				margin-bottom: 0;
				margin-left: 0;
				-webkit-border-radius: 4px;
				-moz-border-radius: 4px;
				border-radius: 4px;
				-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
				-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
				box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
			}
			
			.pagination ul>li {
				display: inline;
				list-style: none;
				line-height: 20px;
			}
			
			.pagination ul>li>a {
				border-left-width: 1px;
				-webkit-border-bottom-left-radius: 4px;
				border-bottom-left-radius: 4px;
				-webkit-border-top-left-radius: 4px;
				border-top-left-radius: 4px;
				-moz-border-radius-bottomleft: 4px;
				-moz-border-radius-topleft: 4px;
				color: #999;
				cursor: default;
				background-color: transparent;
				float: left;
				padding: 4px 12px;
				line-height: 20px;
				text-decoration: none;
				background-color: #fff;
				border: 1px solid #ddd;
				border-left-width: 0;
				font-size: 13px;
			}
			.pagination ul>li>a:hover{
			   background-color: #26b2f0;
			   color:#fff;
			   cursor: pointer;
			}
			.controls a:hover{
			   background-color: #fff !important;
			   color:#999 !important;
			   cursor: default !important;
			}
			.active a{
				background-color: #26b2f0 !important;
			   color:#fff !important;
			}
			.controls a {
				border: none!important;
			}
			
			.pagination .controls input {
				border: 0;
				color: #999;
				width: 30px;
				padding: 0;
				margin: -3px 0 0 0;
				text-align: center;
			}
		</style>
	</head>

	<body>
		<!--提示-->
		<div class="tishi"></div>
		<div class="popup" id="qrcode_popup">
			<div class="pop_center">
				<div class="pc_top">
					<span class="fl">提示</span>
					<span class="fr close"><img src="${ctxStatic}/new-templates/img/close.png" alt=""></span>
				</div>
				<div class="pc_bottom">
					<p style="text-align: center"></p>
					<input type="button" id="query"  class="query fr" value="确定">
				</div>
			</div>
		</div>
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

		<div class="workcontent aut_big">
			<div class="big_top">
				<div class="operation fl">
					<div>人员授权</div>
				</div>
			</div>
			<div class="big_bottom short">
				<div>
					<label>工号：</label>
					<input type="text" maxlength="16" value="" class="scInput" placeholder="文字长度为1-20个字符">
					<label>部门：</label>
					<input type="text" maxlength="16" value="" class="scInput" placeholder="请选择">
				</div>
				<div>
					<label>姓名：</label>
					<input type="text" maxlength="16" value="" class="scInput" placeholder="文字长度为1-20个字符">
					<label>员工类型：</label>
					<input type="text" maxlength="16" value="" class="scInput" placeholder="请选择">
				</div>
				<div>
					<label>电话号：</label>
					<input type="text" maxlength="16" value="" class="scInput" placeholder="文字长度为1-20个字符">
				</div>
			</div>
			
</div>
 <div class="workcontent all_big">
               <div class="c_top">
                   <div class="operation fl">
                       <div class="add" style="cursor: pointer;">添加</div>
                       <div class="qr_code" style="cursor: pointer;">生成二维码</div>
                       <div class="delete" style="cursor: pointer;">删除</div>
                   </div>
                   <form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/emp/emplist" method="post" class="breadcrumb form-search ">
                   	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
                   <div class="information fr" >
                       <label>工号：</label>
									<form:input path="no" htmlEscape="false" maxlength="50" class="scInput" placeholder="请输入工号" />
									<label>姓名：</label>
									<form:input path="name" htmlEscape="false" maxlength="50" class="scInput" placeholder="请输入姓名" />
									<form:input path="status" htmlEscape="false" maxlength="50" class="input-medium width" />
									<%-- <label>审核状态：</label>
									<ul class="scInput fl choice2">
										<!--<p>全部</p>
										<li class="yes">全部</li>
										<li class="wait_audit">待审核</li>
										<li>审核通过</li>-->
									</ul> --%>
									<label>员工状态：</label>
									<form:select path="status" class="scInput fl choice2" >
										<form:option value="" label="请选择" />
										<form:options items="${fns:getDictList('emp_status')}" itemLabel="label" itemValue="value" htmlEscape="false" />
									</form:select>
									<label>一级部门：</label>
									<input id="companyName" name="company.name" readonly="readonly" type="text" value="" data-msg-required="" class="scInput" style="">
									<input name="company.id" id="companyId" type="hidden" />
									<label>二级部门：</label>
									<input name="office.id" id="officeId" type="hidden" />
									<input id="officeName" name="office.name" readonly="readonly" type="text" value="" data-msg-required="" class="scInput" style="">

									</ul>
									<input id="btnSubmit" class="query" type="submit" value="查询" onclick="return page();" />
								</div>
			</form:form>
			</div>
			<sys:message content="${message}"/>
			<div class="c_bottom">
				<table class="tableList">
					<tbody>
						<tr>
							<th width="6%">
								<div class="radio i-checks">
									<input id="color-input-red" class="chat" type="checkbox" name="color-input-red" value="#f0544d" />
									<label for="color-input-red" class="choice"></label >
                               </div>
                           </th>
                           <th width="6%">序号</th>
                           <th width="8%">工号</th>
                           <th width="8%">姓名</th>
                           <th width="10%">部门</th>
                           <th width="15%">录入时间</th>
                           <th width="8%">类型</th>
                           <th width="8%" class="state">员工状态</th>
                           <th width="7%">注册照片</th>
                           <th width="11%">照片授权状态</th>
                           <th width="14%">操作</th>
                       </tr>

                       </tbody>
                   </table>
                   <div class="result">
                       <table class="tableitem">
                           <tbody id="result">
                           	<c:forEach items="${page.list}" varStatus="status"   var="user">
                           <tr>
                               <td width="6%">
                                   <div class="radio i-checks">
                                       <input id="u${user.id}" data="${user.id}" class="chat" type="checkbox" name="check" value="#f0544d" />
                                       <label  for="u${user.id}"></label >
                                   </div>
                               </td>
                               <td width="6%">${status.count}</td>
                               <td width="8%">${user.no}</td>
                               <td width="8%">${user.name}</td>
                               <td width="10%">${user.company.name}&nbsp;/&nbsp;${user.office.name}</td>
                               <td width="15%"><fmt:formatDate value="${user.createDate}" type="both" /></td><%-- dateStyle="full" --%>
                               <td width="8%">${fns:getDictLabel(user.userType, "sys_user_type", "")}</td>
                               <td width="8%">${fns:getDictLabel(user.status, "emp_status", "")}</td>
                               <td width="7%" class="blue">
                               <c:choose>
                               	<c:when test="${user.photo != null and user.photo != ''}">
                               		<img style="width: 50px;height: 50px;" src="${user.photo}"/>
                               	</c:when>
                               	<c:otherwise>
	                               <img style="width: 50px;height: 50px;" src="${ctxStatic}/brf/img/moren.jpg"/>
                               	</c:otherwise>
                               </c:choose>
                               </td>
                               <td width="11%" class="blue">${fns:getDictLabel(user.authPhone, "photo_status", "")}</td>
                               <shiro:hasPermission name="sys:user:edit"><td class="blue" width="14%">
                               		<a href="${ctx}/sys/emp/empDetail?id=${user.id}">查看</a>
				    				<a href="${ctx}/sys/emp/empform?id=${user.id}">修改</a>
				    				<a href="${ctx}/sys/emp/authorization?id=${user.id}">授权</a>
				    				<c:if test="${user.status eq '0'}">
				    					<a href="${ctx}/sys/emp/audit?id=${user.id}">审核</a>
				    				</c:if>
									<a href="javascript:;" onclick="delUser('${user.id}')">删除</a>
									</td>
								</shiro:hasPermission>
                           </tr>
                           </c:forEach>
                           </tbody></table>
                   </div>
                   <div class="pagination">${page}</div>
                   <!--<div class="paging">
                       <div class="all fl">共<span class="data"></span>条数据，总计 <span class="pages"></span>页</div>
                       <div class="page_number fr">
                           <ul class="fl">
                               <li class="left"><img src="${ctxStatic}/new-templates/img/left_03.jpg" alt=""></li>
                               <li class="on">1</li>
                               <li>2</li>
                               <li>3</li>
                               <li>4</li>
                               <li>5</li>
                               <li>. . .</li>
                               <li id="page"><img src="${ctxStatic}/new-templates/img/more_03.jpg" alt=""></li>
                           </ul>
                           <p class="fl">跳转到</p>
                           <ul class="fl">
                               <li><input type="number" class="inputPage"></li>
                               <li class="goPage">GO</li>
                           </ul>
                       </div>
                   </div>-->
               </div>
           </div>

</body>
	<script>
		function delUser (id) {
			if(confirm('确认删除该用户吗？')){
				$.getJSON('${ctx}/sys/emp/empdelete?id=' + id)
				window.location.reload();
			}
		}
		
	    $("#query").click(function () {
	        $(".popup").css("display","none");
	        window.location.href = '${ctx}/brf/empRecord/empEWM';
	    })
		$(".delete").click(function () {
   			var ids = []
   			$.each($("input[name='check']"), function() {
   					if($(this).prop("checked"))
					ids.push( $(this).attr("data"))
				});
			if(ids.length<=0){
				$(".pc_bottom p").html("对不起，请选择要删除的项目")
                $(".qrcode_popup").css("display","block")
				return;
			}
			if(confirm('确认要删除所选数据吗？')){
   				$.each(ids, function(i,val) {
   					$.getJSON('${ctx}/sys/user/delete?id=' + val)
   				});
   				setTimeout(function () {
   					window.location.reload();
   				},300)
   				
   			}
   		})	
		
		$(function () {
			if($("#messageBox").text()){
				$(".pc_bottom p").html($("#messageBox").text());
                $(".qrcode_popup").css("display","block");
               // window.location.reload();
			}
			$("#status").remove();
			 $(".add").click(function () {
        window.location.href = "${ctx}/sys/emp/empform"
   				 })
			
			$("#companyName").click(function () {
				
		// 正常打开	
		$.jBox.open("iframe:/brushface/a/tag/treeselect?url="+encodeURIComponent("/sys/office/treeData?type=1")+"&module=&checked=&extId=&isAll=", "选择公司", 300, 420, {
			ajaxData:{selectIds: $("#companyId").val()},buttons:{"确定":"ok", "清除":"clear", "关闭":true}, submit:function(v, h, f){
				if (v=="ok"){
					var tree = h.find("iframe")[0].contentWindow.tree;//h.find("iframe").contents();
					var ids = [], names = [], nodes = [];
					if ("" == "true"){
						nodes = tree.getCheckedNodes(true);
					}else{
						nodes = tree.getSelectedNodes();
					}
					for(var i=0; i<nodes.length; i++) {//
						ids.push(nodes[i].id);
						names.push(nodes[i].name);//
						break; // 如果为非复选框选择，则返回第一个选择  
					}
					$("#companyId").val(ids.join(",").replace(/u_/ig,""));
					$("#companyName").val(names.join(","));
				}//
				else if (v=="clear"){
					$("#companyId").val("");
					$("#companyName").val("");
                }//
				if(typeof companyTreeselectCallBack == 'function'){
					companyTreeselectCallBack(v, h, f);
				}
			}, loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}
	});

			})
			
			$("#officeName").click(function () {
				
		// 正常打开	
		$.jBox.open("iframe:/brushface/a/tag/treeselect?url="+encodeURIComponent("/sys/office/treeData?type=2")+"&module=&checked=&extId=&isAll=", "选择部门", 300, 420, {
			ajaxData:{selectIds: $("#officeId").val()},buttons:{"确定":"ok", "清除":"clear", "关闭":true}, submit:function(v, h, f){
				if (v=="ok"){
					var tree = h.find("iframe")[0].contentWindow.tree;//h.find("iframe").contents();
					var ids = [], names = [], nodes = [];
					if ("" == "true"){
						nodes = tree.getCheckedNodes(true);
					}else{
						nodes = tree.getSelectedNodes();
					}
					for(var i=0; i<nodes.length; i++) {//
						if (nodes[i].isParent){
							top.$.jBox.tip("不能选择父节点（"+nodes[i].name+"）请重新选择。");
							return false;
						}//
						ids.push(nodes[i].id);
						names.push(nodes[i].name);//
						break; // 如果为非复选框选择，则返回第一个选择  
					}
					$("#officeId").val(ids.join(",").replace(/u_/ig,""));
					$("#officeName").val(names.join(","));
				}//
				else if (v=="clear"){
					$("#officeId").val("");
					$("#officeName").val("");
                }//
				if(typeof officeTreeselectCallBack == 'function'){
					officeTreeselectCallBack(v, h, f);
				}
			}, loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}
	});

			})
		})
		
			function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/emp/list");
			$("#searchForm").submit();
	    	return false;
	    }
			
			
	</script>
</html>
