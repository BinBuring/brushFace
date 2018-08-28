<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	.width{width: 100px;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/sys/user/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			  //复选框全选与反选
			 $(".chat").click(function () {
		        var groupCheckbox=$("input[name='check']");
		       getIds();
		       /*  var ids = "";
			    for(i=0;i<groupCheckbox.length;i++){
			        if(groupCheckbox[i].checked){
			            var val =groupCheckbox[i].value;
			           ids += val+",";
			        }
			    }
			    $("#guids").val(ids); */
		      })

		      //全部选中
		      $("#all").click(function() { 
					if (this.checked){  
				        $("input[name='check']:checkbox").each(function(){ 
				              $(this).attr("checked", true);  
				        });
				    } else {   
				        $("input[name='check']:checkbox").each(function() {   
				              $(this).attr("checked", false);  
				        });
				    } 
					getIds();
				});

		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/emp/list");
			$("#searchForm").submit();
	    	return false;
	    }
	  	function getIds(){
	  	  //获取被选中的id
			var ids=[];
			$("input[name='check']:checked").each(function(){ 
	              ids.push($(this).attr("value"));  
	        });
			
			var delIds=ids.join(","); 
			 $("#devids").val(delIds);
			 $("#devids").val(delIds);
			return delIds;
	  	}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">人员授权</li>
	</ul>
	<sys:message content="${message}"/>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th>
				<div class="radio i-checks">
					<input id="all" class="chat" type="checkbox"
						name="check" value="" /> <label
						for="color-input-red"></label>
				</div>
			</th>
			<th class="sort-column name">终端序列号</th>
			<th>设备名称</th>
			<th>识别设备</th>
			<th>版本号</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" varStatus="status"  var="dev">
			<tr>
				<th>
					<div class="radio i-checks">
						<input id="color-input-red" class="chat" type="checkbox"
							name="check" value="${dev.id }" /> <label
							for="color-input-red"></label>
					</div>
				</th>
				<td>${dev.seq}</td>
				<td>${dev.name}</td>
				<td>${dev.aisle}</td>
				<td>${dev.ver}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
<form:form id="inputForm" modelAttribute="device" action="${ctx}/brf/devUser/saveDev" method="post" class="form-horizontal">
	<input type="text" value="${user.id }" name = "userId">
	<input type="text" value="" id="devids" name = "devids">
	<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="submit"value="保 存" />&nbsp;
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
	</div>
</form:form>
</body>
</html>