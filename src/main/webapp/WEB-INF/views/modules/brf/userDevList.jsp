<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<link rel="stylesheet" href="${ctxStatic}/new-templates/css/index.css">
	<meta name="decorator" content="default"/>
	<style type="text/css">
	.width{width: 100px;}
	#aut_result tr {
				height: 40px!important;
			}
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
	              ids.push($(this).attr("data"));  
	        });
			var delIds=ids.join(","); 
			 $("#devids").val(delIds);
			 $("#devids").val(delIds);
			return delIds;
	  	}

	</script>
</head>
<body>
	<sys:message content="${message}"/>
	<div class="c_bottom" style="position: relative;top:0;display: block;">
				<p>选择需要授权的设备</p>
				<table class="tableList">
					<tbody>
						<tr>
							<th width="8%">
								<div class="radio i-checks">
									<input id="all" class="chat" type="checkbox" name="color-input-red" value="#f0544d" />
									<label for="all" class="choice"></label >
                    </div>
                </th>
                <th width="23%">终端序列号</th>
                <th width="23%">设备名称</th>
                <th width="23%">识别设备</th>
                <th width="23%">版本号</th>
            </tr>
            </tbody>
        </table>
        <div class="result">
            <table class="tableitem">
                <tbody id="aut_result">
                <c:forEach items="${page.list}" varStatus="status"  var="dev">	
                <tr>
                          <td width="8%">
                              <div class="radio i-checks">
                                  <input id="d${dev.id}" class="chat" data="${dev.id}"  type="checkbox" name="check" value="#f0544d" />
                                  <label  for="d${dev.id}" class="choice" ></label >
                              </div>
                          </td>
                          <td width="23%">${dev.seq}</td>
                          <td width="23%">${dev.name}</td>
                          <td width="23%">${dev.aisle}</td>
                          <td width="23%">${dev.ver}</td>
                      </tr>
                </c:forEach>      
                </tbody></table>
                <div class="pagination">${page}</div>
        </div>
        <form:form id="inputForm" modelAttribute="device" action="${ctx}/brf/devUser/saveDev" method="post" class="form-horizontal">
        <input type="hidden" value="${user.id}" name = "userId" >
		<input type="hidden" value="" id="devids" name = "devids">
        <div class="button">
            <input type="submit" class="confirm fl" value="授权">
            <input type="button" class="return fr" value="返回" onclick="history.go(-1)">
        </div>
        </form:form>
    </div>
	
	
</body>	
</head>
</html>
