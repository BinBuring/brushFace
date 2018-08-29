<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctxStatic}/new-templates/css/index.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="${ctxStatic}/new-templates/js/department.js"></script>
    <meta name="decorator" content="default" />
<%-- <%@include file="/WEB-INF/views/include/treetable.jsp"%> --%>
	<style>
		#searchForm{
			padding: 0!important;
		}
		.pc_concent{
			padding: 0!important;
		}
		#result tr {
				height: 40px;
			}
	</style>
<script type="text/javascript">
	$(function () {
		if($("#name").val())
		{
		$("#name").val('')
			$("#searchForm").submit();
		}
		
	})
	function page(n,s){
		if(n) $("#pageNo").val(n);
		if(s) $("#pageSize").val(s);
		$("#searchForm").attr("action","${ctx}/sys/office/list");
		$("#searchForm").submit();
    	return false;
    }
</script>
</head>
<body>
<!--提示-->
<div class="tishi"></div>
<!--修改部门名称-->
<div class="mod_popup popup">
    <div class="pop_center" style="height: 350px">
        <div class="pc_top">
            <span class="fl">修改部门名称</span>
            <span class="fr close"><img src="${ctxStatic}/new-templates/img/close.png" alt=""></span>
        </div>
        <div class="pc_bottom">
            <div>
                <span class="name" >一级部门</span>
                <ul class="pc_ul choice">
                    <p>全部</p>
                    <div class="department first">
                        <li class="yes">全部</li>
                    </div>
                </ul>
            </div>
            <div>
                <span class="name">二级部门</span>
                <ul class="pc_ul choice">
                    <p>全部</p>
                    <div class="department second">
                        <li class="yes">全部</li>
                    </div>
                </ul>
            </div>
           <input type="button" class="query fr" value="确定">
        </div>
    </div>
</div>
<!--添加二级部门-->
<div class="addsec_popup popup">
    <div class="pop_center" style="height: 350px">
        <div class="pc_top">
            <span class="fl">添加二级部门</span>
            <span class="fr close"><img src="${ctxStatic}/new-templates/img/close.png" alt=""></span>
        </div>
        <div class="pc_bottom">
            <div>
                <span class="name">一级部门</span>
                <ul class="pc_ul choice">
                    <p>全部</p>
                    <div class="department first">
                        <li class="yes">全部</li>
                    </div>
                </ul>
            </div>
            <div>
                <label class="name">二级部门</label>
                <input type="text" maxlength="16" value="" class="pc_concent" placeholder="请输入文字1-20个字">
            </div>
            <input type="button" class="query fr" value="确定">
        </div>
    </div>
</div>
<!--添加一级部门-->

<div class="addfir_popup popup">
	
    <div class="pop_center">
        <div class="pc_top" >
            <span class="fl">添加一级部门</span>
            <span class="fr close"><img src="${ctxStatic}/new-templates/img/close.png" alt=""></span>
        </div>
        <div class="pc_bottom">
            <div>
                <label class="name">一级部门</label>
                <input type="text" maxlength="16" value="" class="pc_concent" placeholder="请输入文字1-20个字" name="name">
            </div>
        </div>
        
        </form>
    </div>
</div>
<div class="workcontent">
	
    <div class="c_top">
        <div class="operation fl">
            <div class="add add_first" data="${ctx}/sys/office/form?parent.id=${office.id}">添加一级部门</div>
            <div class="add add_sec" data="${ctx}/sys/office/form?parent.id=${office.id}">添加二级部门</div>
            <div class="delete">删除</div>
        </div>
        <form:form id="searchForm" modelAttribute="office"
		action="${ctx}/sys/office/list" method="post"
		class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}"
			callback="page();" />
        <div class="information fr">
            <label>部门名称：</label>
            <form:input path="name" htmlEscape="false" maxlength="50" class="scInput chang" placeholder="请输入部门名称" style="height: 32px;padding: 0;"/>
            <input type="button" class="query" value="查询" type="submit"  onclick="return page();">
        </div>
        </form:form>
    </div>
    <div class="c_bottom">
        <table class="tableList">
            <tbody><tr>
                <th width="6%">
                    <div class="radio i-checks">
                    <input id="1" class="chat" type="checkbox" name="color-input-red" value="#f0544d" onchange="selectAll(this)" />
                    <label  for="1"></label >
                    </div>
                </th>
                <th width="6%">序号</th>
                <th width="37%">一级部门</th>
                <th width="37%">二级部门</th>
                <th width="14%">操作</th>
            </tr>

            </tbody>
        </table>
        <div class="result">
               <table class="tableitem">
                <tbody id="result">
                <c:forEach items="${page.list}" varStatus="status" var="office">	
                    <tr>
            <td width="6%">
            <div class="radio i-checks">
            <input id="o${office.id}" class="chat" data="${office.id}" type="checkbox" name="offSelect" value="#f0544d" />
            <label  for="o${office.id}"></label >
            </div>
            </td>
            <td width="6%">${status.count}</td>
            <td width="37%">${office.parent.name}</td>
            <td width="37%">${office.name}</td>
            <shiro:hasPermission name="sys:office:edit">
						<td width="14%"><a href="${ctx}/sys/office/form?id=${office.id}">修改</a>
							<a	href="javascript:;" onclick="delOffice('${office.id}')">删除</a> 
						</td>
					</shiro:hasPermission>
                    </tr>
            </c:forEach>
                </tbody>
               </table>
               <div class="pagination">${page}</div>
        </div>
    </div>
</div>
	<script>
		function selectAll(sel) {
			if($(sel).is(':checked')){
				$.each($("input[name='offSelect']"), function() {
					$(this).prop("checked",true) 
				});
			}else{
				$.each($("input[name='offSelect']"), function() {
					$(this).prop("checked",false) 
				});
			}
		}
		
		function delOffice (id) {
			if(confirm('要删除该机构及所有子机构项吗？')){
				$.getJSON('${ctx}/sys/office/delete?id=' + id)
				setTimeout(function () {
   					window.location.reload();
   				},300)
			}
		}
		
		$(function () {
			$(".delete").click(function () {
   			var ids = []
   			$.each($("input[name='offSelect']"), function() {
   					if($(this).prop("checked"))
					ids.push( $(this).attr("data"))
				});
			if(ids.length<=0){
				alert('请至少选择一项')
				return;
			}
			if(confirm('要删除该机构及所有子机构项吗？')){
   				$.each(ids, function(i,val) {
   					$.getJSON('${ctx}/sys/office/delete?id=' + val)
   				});
   				setTimeout(function () {
   					window.location.reload();
   				},300)
   				
   			}
   		})	
   		
   		$(".add_first").click(function () {
   			var url = $(this).attr('data')
   			window.location.href = url + "&type=first"
   		})
   		$(".add_sec").click(function () {
   			var url = $(this).attr('data')
   			window.location.href = url + "&type=sec"
   		})
   		
		})
	</script>
</body>
</html>