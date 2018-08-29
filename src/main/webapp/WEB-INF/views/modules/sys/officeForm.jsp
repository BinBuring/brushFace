<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>部门管理</title>
	 
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/new-templates/css/index.css">
	<style>
			.scInput {
				padding: 0!important;
			}
			.error{
				text-align: left!important;
			}
			#companyButton,#officeButton{
				height: 31px!important;
				line-height: 31px!important;
				display: none!important;
			}
			
			.notView {
				display: none!important;
			}
		</style>
		<script>
		$(function () {
			$("#officeView").val($("#officeName").val())
			var urlParam = getRequest();
			if(urlParam && urlParam.type == "first"){
				$("#first").hide();
				$("#grade").val(1)
			}else{
				$("#grade").val(2)
			}
			$("#officeView").click(function(){
		// 正常打开	
		$.jBox.open("iframe:/brushface/a/tag/treeselect?url="+encodeURIComponent("/sys/office/treeData?type=2")+"&module=&checked=&extId=&isAll=", "选择部门", 300, 420, {
			ajaxData:{selectIds: $("#officeId").val()},buttons:{"确定":"ok", "关闭":true}, submit:function(v, h, f){
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
					$("#officeView").val($("#officeName").val());
				}//
				if(typeof officeTreeselectCallBack == 'function'){
					officeTreeselectCallBack(v, h, f);
				}
			}, loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}
		});
	});
		})
		
		function getRequest() {
			var url = window.location.search;
			var theRequest = null;
			if(url.indexOf("?") != -1) {
				theRequest = new Object;
				var str = url.substr(1);
				strs = str.split("&");
				for(var i = 0; i < strs.length; i++) {
					theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
				}
			}
			return theRequest;
		}
	</script>
</head>
<body>
	<div class="workcontent add_big" style="display: block;">
			<div class="big_top">
				<div class="operation fl">
					<div>添加一级部门</div>
				</div>
			</div>
				<form:form id="inputForm" modelAttribute="office" action="${ctx}/sys/office/save" method="post" class="form-horizontal">
					<form:hidden path="id"/>
						<sys:message content="${message}"/>
				    <div class="big_bottom short">            
					<div id="first">
						<label>上级部门：</label>
						<sys:treeselect id="office" name="parent.id" value="${office.parent.id}" labelName="parent.name" labelValue="${office.parent.name}"
						title="部门" url="/sys/office/treeData" extId="${office.id}" cssClass="scInput required notView" allowClear="${office.currentUser.admin}"/>
						<input type="text" name="" id="officeView" maxlength="16" value="" class="scInput name" placeholder="文字长度为1-20个字符">						
					</div>
					<div >
						<label>部门名称：</label>
						<form:input path="name" htmlEscape="false" maxlength="50"  class="scInput " placeholder="请输入文字1-20个字" />
											
					</div>
					<div id="type">
						<div class="controls">
						<input type="hidden" value="1" name='grade' id="grade" />
					</div>	
					
					<div class="button" style="text-align: left;">
						<shiro:hasPermission name="sys:office:edit"><input type="submit" class="confirm fl" value="确认"></shiro:hasPermission>
						<input type="button" class="return fr" onclick="history.go(-1)" value="返回">
					</div>
					</div>
				</div>
			</form:form>
			
		</div>
</div>
</body>
	
</html>