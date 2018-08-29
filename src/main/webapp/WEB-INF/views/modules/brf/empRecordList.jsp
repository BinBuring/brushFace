<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctxStatic}/new-templates/css/index.css">
    <script src="${ctxStatic}/new-templates/js/em_rec.js"></script>
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
    	.scInput{
    		padding: 0!important;
    	}
    	#searchForm{
    		padding: 0!important;
    		
    	}
    	#result tr{
    		height: 40px!important;
    	}
    </style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/brf/empRecord/">记录列表</a></li>
		<shiro:hasPermission name="brf:empRecord:edit"><li><a href="${ctx}/brf/empRecord/form">记录添加</a></li></shiro:hasPermission>
	</ul>
<!--提示-->
<div class="tishi"></div>
<div class="workcontent">
    <div class="c_top">
    	<form:form id="searchForm" modelAttribute="empRecord" action="${ctx}/brf/empRecord/" method="post" class="breadcrumb form-search">
    		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <div class="operation fl">
            <!--<div class="add">添加</div>
            <div class="qr_code">生成二维码</div>
            <div class="delete">删除</div>-->
        </div>
        <div class="information fr">
            <label>工号：</label>
            <form:input path="empNo" htmlEscape="false" maxlength="16"  class="scInput" placeholder="请输入工号"/>
            <label>姓名：</label>
            <form:input path="empName" htmlEscape="false" maxlength="16"  class="scInput" placeholder="请输入姓名"/>
            <label>部门：</label>
            <form:input path="offName" htmlEscape="false" maxlength="255" class="scInput" placeholder="请输入部门"/>
            <label>开始时间：</label>
            <input name="beginShowtime" type="text" readonly="readonly" maxlength="20" class="scInput chang"
					value="<fmt:formatDate value="${empRecord.beginShowtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
            <label>结束时间：</label>
            <input name="endShowtime" type="text" readonly="readonly" maxlength="20" class="scInput chang"
					value="<fmt:formatDate value="${empRecord.endShowtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
            
            <input type="submit" class="query" value="查询">
        </div>
        </form:form>
    </div>
    <div class="c_bottom">
        <table class="tableList">
            <tbody><tr>
                <th width="10%">序号</th>
                <th width="10%">员工工号</th>
                <th width="12%">员工姓名</th>
                <th width="11%">员工部门</th>
                <th width="15%">员工识别图片</th>
                <th width="18%">识别时间</th>
                <th width="14%">识别设备</th>
                <th width="10%">识别模式</th>
            </tr>

            </tbody>
        </table>
        <div class="result">
            <table class="tableitem">
            <tbody id="result">
            	<c:forEach items="${page.list}" var="empRecord" varStatus="status">
            <tr>
           <th width="10%">${status.count}</th>
           <th width="10%"><a href="${ctx}/brf/empRecord/form?id=${empRecord.id}">
					${empRecord.emp.no}
				</a></th>
           <th width="12%">${empRecord.emp.name}</th>
           <th width="11%">${empRecord.offName}</th>
           <th width="15%"><img src="${empRecord.photourl}" style="width: 50px;height: 50px;"></th>
           <th width="18%"><fmt:formatDate value="${empRecord.showtime}" pattern="yyyy-MM-dd HH:mm:ss"/></th>
           <th width="14%">${empRecord.dev.aisle}</th>
           <th width="10%">${fns:getDictLabel(empRecord.recmode, "recmode", "")}</th>
           </tr>
           </c:forEach>
            </tbody></table>
            <div class="pagination">${page}</div>
        </div>
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
</html>