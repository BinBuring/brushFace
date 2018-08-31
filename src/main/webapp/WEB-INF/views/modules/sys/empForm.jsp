<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

	<head>
		<title>员工管理</title>
		<link rel="stylesheet" href="${ctxStatic}/new-templates/css/index.css">
		
		<meta name="decorator" content="default" />

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
			.select2-container .select2-choice{
				height: 42px!important;
				line-height: 42px!important;
				border: 1px solid #d2d2d2!important;
				padding-left: 0!important;
			}
		</style>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#no").focus();
				$("#inputForm").validate({
					rules: {
						loginName: {
							remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')
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
	</head>

	<body>
		<ul class="nav nav-tabs">
			<li>
				<a href="${ctx}/sys/emp/list">员工列表</a>
			</li>
			<li class="active">
				<a href="${ctx}/sys/emp/empform?id=${user.id}">员工${not empty user.id?'修改':'添加'}</a>
			</li>
		</ul><br/>
		<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/emp/empsave" method="post" class="form-horizontal">
			<form:hidden path="id" />
			<div class="big_bottom short">
				<div>
					<label>工号：</label>
					<form:input path="no" htmlEscape="false" maxlength="50" class="required scInput worknum"/>
					<label>公司：</label>
					<sys:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}" title="公司" url="/sys/office/treeData?type=1" cssClass="scInput required notView" />
					<input type="text" name="" id="companyView" maxlength="16" value="" class="scInput name" placeholder="文字长度为1-20个字符">
				</div>
				<div>
					<label>部门：</label>
					 <sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}" title="部门" url="/sys/office/treeData?type=2" cssClass="scInput required notView" notAllowSelectParent="true"/>
					<input type="text" name="" id="officeView" maxlength="16" value="" class="scInput name" placeholder="文字长度为1-20个字符">
					<label>员工类型：</label>
					<form:select path="userType" class="input-xlarge scInput">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('sys_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
				<div>
					<label>姓名：</label>
					<form:input path="name" htmlEscape="false" maxlength="50" class="required scInput name"/>
					<label>身份证号：</label>
					<form:input path="idCard" htmlEscape="false" maxlength="100" class="scInput idnum"/>
				</div>
				<div>
					<label>电话号：</label>
					<form:input path="phone" htmlEscape="false" maxlength="100" class="scInput phone"/>
					
					
					<!--<input type="text" maxlength="16" value="" class="scInput starttime" placeholder="请选择">-->
				</div>
				<div>
					<label>有效开始时间：</label>
					<input id="startDate" name="startDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate required scInput"
				value="<fmt:formatDate value="${user.startDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					<label>有效结束时间：</label>
					<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate required scInput"
					value="<fmt:formatDate value="${user.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				</div>
				<div>
					<label>备注：</label>
					<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="scInput chang"/>
				</div>
			</div>
			<div class="big_top">
				<div class="operation">
					<div>上传照片</div>
				</div>
			</div>
			<div class="upload_img">
				<div class="left fl">
					<c:if test="${user.photo == null}">
					<img id="ImgPr" src="${ctxStatic}/new-templates/img/back_03.jpg" alt="">
					</c:if>
					<c:if test="${user.photo != null}">
					<img id="ImgPr" src="${user.photo}" alt="">
					</c:if>
				</div>
				<div class="right " style="float: right;">
					
					<div class="upload" id="up" data="${ctx}/sys/emp/uploadImg" style="cursor:pointer">本地上传
						<form:hidden id="nameImage" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
					</div>
					<p class="tips">
						提示：<br> 请添加清晰正脸的生活照，
						<br> 照片质量影响识别效果。
					</p>
				</div>
				<div class="up_success fl">
					<p>
						授权成功的设备：Aitu cloud(离线) Aitu cloud(离线)人脸识别（在线）<br>授权中的设备：无
					</p>
				</div>
				<div class="button fl">
					<shiro:hasPermission name="sys:user:edit"><input id="btnSubmit" class="confirm fl save" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
					<input type="button" class="return fr" value="返回">
				</div>
			</div>
			</div>
		</form:form>
				<!--图片上传框-->

		<form id="imgupload" action="" enctype="multipart/form-data" method="post">
			<input id="upfile" type="file" name="myfiles" onchange="doupFile();" style="display: none" />
		</form>
		<script>
			$(function() {
				$(".fr").click(function () {
					window.location.href ="${ctx}/sys/emp/list"
				})
				$("#companyView").val($("#companyName").val())
				$("#officeView").val($("#officeName").val())
				$("#companyView").click(function() {
					$.jBox.open("iframe:/brushface/a/tag/treeselect?url=" + encodeURIComponent("/sys/office/treeData?type=1") + "&module=&checked=&extId=&isAll=", "选择公司", 300, 420, {
						ajaxData: {
							selectIds: $("#companyGetId").val()
						},
						buttons: {
							"确定": "ok",
							"关闭": true
						},
						submit: function(v, h, f) {
							if(v == "ok") {
								var tree = h.find("iframe")[0].contentWindow.tree; //h.find("iframe").contents();
								var ids = [],
									names = [],
									nodes = [];
								if("" == "true") {
									nodes = tree.getCheckedNodes(true);
								} else {
									nodes = tree.getSelectedNodes();
								}
								for(var i = 0; i < nodes.length; i++) { //
									ids.push(nodes[i].id);
									names.push(nodes[i].name); //
									break; // 如果为非复选框选择，则返回第一个选择  
								}
								$("#companyId").val(ids.join(",").replace(/u_/ig, ""));
								$("#companyName").val(names.join(","));
								$("#companyView").val($("#companyName").val())
							} //
							if(typeof companyGetTreeselectCallBack == 'function') {
								companyGetTreeselectCallBack(v, h, f);
							}
						},
						loaded: function(h) {
							$(".jbox-content", top.document).css("overflow-y", "hidden");
						}
					});

				})
				
				
	$("#officeView").click(function(){
		// 正常打开	
		var pid = $("#companyId").val();
		$.jBox.open("iframe:/brushface/a/tag/treeselect?url="+encodeURIComponent("/sys/office/treeData?type=2&pid="+pid)+"&module=&checked=&extId=&isAll=", "选择部门", 300, 420, {
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
		</script>
		
<script>
			
			$(function () {
				$("#up").click(function() {
				$("#upfile").click();
			});
			})
			//上传图片
		function doupFile() {
			$.ajaxSettings.async = true; 
			
			var formData = new FormData(document.getElementById("imgupload"));
			 var f = document.getElementById("upfile");  
			 var tag = 1;
			 $.each(f.files, function(i,val) {
			 	if(val.size > 400000){
			 		alert('文件不能大于400KB');
			 		f.value = '';
			 		f.outerHTML = f.outerHTML;
			 		tag = 0;
			 	}
			 });
			 if(tag!=1)return;
			var filepath = $("#upfile").val();
			var ext = GetFileExt(filepath);
			if(!(ext == ".jpg" || ext == ".png" || ext == ".jpeg" || ext == ".JPEG" || ext == ".gif" || ext == ".bmp" || ext == ".JPG" || ext == ".PNG")){
				alert('文件上传类型不正确');
				f.value = '';
			 	f.outerHTML = f.outerHTML;
				return;
			}
			$.ajax({
				type: "post",
				url: "${ctx}/sys/emp/uploadImg",
				dataType: "json",
				data: formData,
				cache: false,
				contentType: false,
				processData: false,
				success: function(result) {
					if(result.state == 1) {
						
						alert('图片上传成功')
						var data = result;
						$("#ImgPr").attr('src',data.url);
						$("#nameImage").val(data.url);
					} else {
						alert(result.message)
						return;
					}
				},
				error: function(result) {
					alert(result.message)
					f.value = '';
			 		f.outerHTML = f.outerHTML;
				}
			});
		}

		//取文件后缀名
		function GetFileExt(filepath) {
			if(filepath != "") {
				var pos = "." + filepath.replace(/.+\./, "");
				return pos;
			}
		}
			

		</script>
		
		
	</body>
</html>
