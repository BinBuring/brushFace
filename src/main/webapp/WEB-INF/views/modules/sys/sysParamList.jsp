<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<style>
		#searchForm{
			padding: 0!important;
		}
		#result tr {
				height: 40px;
			}
	</style>
    <link rel="stylesheet" href="${ctxStatic}/new-templates/css/index.css">
    <script src="${ctxStatic}/new-templates/js/system_par.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/sysParam/">参数列表</a></li>
		<li><a href="${ctx}/sys/sysParam/form">参数添加</a></li>
	</ul>
<!--提示-->

<div class="tishi"></div>
<div class="mod_popup popup">
    <div class="pop_center">
        <div class="pc_top">
            <span class="fl">修改系统参数管理</span>
            <span class="fr close"><img src="${ctxStatic}/new-templates/img/close.png" alt=""></span>
        </div>
        <div class="pc_bottom">
            <label class="name">系统IP地址</label>
            <input type="text" maxlength="16" value="" class="pc_concent" placeholder="58:60:122:11">
            <input type="button" class="query fr" value="确定">
        </div>
    </div>
</div>

<div class="workcontent">
	<form:form id="searchForm" modelAttribute="sysParam" action="${ctx}/sys/sysParam/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <div class="c_top">
        <!--<div class="operation fl">-->
            <!--<div class="add">添加设备</div>-->
            <!--<label class="dev_num" ></label>-->
        <!--</div>-->
        <!--<div class="information fr">-->
            <!--<label>设备序列号：</label>-->
            <!--<input type="text" maxlength="16" value="" class="scInput chang" placeholder="请输入设备序列号">-->
            <!--<input type="button" class="query" value="查询">-->
        <!--</div>-->
    </div>
    </form:form>
	<sys:message content="${message}"/>
    <div class="c_bottom">
        <table class="tableList">
            <tbody><tr>
                <th width="10%">序号</th>
                <th width="30%">参数类型</th>
                <th width="20%">参数内容</th>
                <th width="30%">修改时间</th>
                <th width="10%">操作</th>
            </tr>
            </tbody>
        </table>
        <div class="result">
            <table class="tableitem">
            <tbody id="result">
            	<c:forEach items="${page.list}" var="sysParam" varStatus="status">
            <tr>
              <th width="10%">${status.count}</th>
                <th width="30%"><a href="${ctx}/sys/sysParam/form?id=${sysParam.id}">
					${sysParam.type}
				</a></th>
                <th width="20%">${sysParam.content}</th>
                <th width="30%"><fmt:formatDate value="${sysParam.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></th>
                <th width="10%">
                	<a href="${ctx}/sys/sysParam/form?id=${sysParam.id}">修改</a>
                	<a href="javascript:;" onclick="delObj('${sysParam.id}')">删除</a>
                </th>
           </tr>
           </c:forEach>
            </tbody></table>
            <div class="pagination">${page}</div>
        </div>
<!--        <div class="paging">
            <div class="all fl">共<span class="data"></span>条数据，总计 <span class="pages"></span>页</div>
            <div class="page_number fr">
                <ul class="fl">
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
<script>
	function delObj (id) {
			if(confirm('要删除该项吗？')){
				$.getJSON('${ctx}/sys/sysParam/delete?id=' + id)
				setTimeout(function () {
   					window.location.reload();
   				},300)
			}
		}
</script>
</body>
</html>
