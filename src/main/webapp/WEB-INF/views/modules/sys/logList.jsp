<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctxStatic}/new-templates/css/index.css">
    <meta name="decorator" content="default"/>
	<script type="text/javascript">
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
		#result tr {
				height: 25px;
			}
		#searchForm{
			padding: 0!important;
		}
	</style>
</head>
<body>
<!--提示-->
<div class="tishi"></div>
<div class="workcontent">
    <div class="c_top">
    	<form:form id="searchForm" action="${ctx}/sys/log/" method="post" class="breadcrumb form-search">
    		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <div class="operation fl">
            <!--<div class="add">添加</div>
            <div class="qr_code">生成二维码</div>
            <div class="delete">删除</div>-->
        </div>
        <div class="information fr">
            <label>操作员ID：</label>
            <input id="createBy.id" name="createBy.id" type="text" maxlength="50" class="scInput chang" value="${log.createBy.id}"  placeholder="请输入操作员ID"/>
            <label>操作类型：</label>
            <!--<input type="text" maxlength="16" value="" class="scInput" placeholder="全部">-->
            <ul class="scInput fl choice">
                <p>全部</p>
                <li class="yes">全部</li>
                <li>添加员工</li>
                <li>修改员工</li>
                <li>删除员工</li>
            </ul>
            <label>操作内容：</label>
            <input id="title" name="title" type="text" maxlength="50"  value="${log.title}" class="scInput chang" placeholder="请输入操作内容"/>
            <label>开始时间：</label>
            <input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="scInput chang"
				value="<fmt:formatDate value="${log.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
            <label>结束时间：</label>
            <input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="scInput chang"
				value="<fmt:formatDate value="${log.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
            <input type="submit" class="query" value="查询">
        </div>
    </div>
    </form:form>
    <div class="c_bottom">
        <table class="tableList">
            <tbody><tr>
                <th width="10%">序号</th>
                <th width="14%">操作员</th>
                <th width="14%">操作类型</th>
                <th width="14%">操作者IP</th>
                <th width="18%">创建时间</th>
                <th width="30%">操作内容</th>
            </tr>

            </tbody>
        </table>
        <div class="result">
            <table class="tableitem">
            <tbody id="result">
            	<c:forEach items="${page.list}" var="log" varStatus="status">
            <tr>
            	<th width="10%">${status.count}</th>
                <th width="14%">${log.createBy.name}</th>
                <th width="14%">${log.method}</th>
                <th width="14%">${log.remoteAddr}</th>
                <th width="18%"><fmt:formatDate value="${log.createDate}" type="both"/></th>
                <th width="30%">${log.title}</th>
           </tr>
            </c:forEach>
            </tbody></table>
        </div>
        <div class="pagination">${page}</div>
    </div>
</div>

</body>
</html>