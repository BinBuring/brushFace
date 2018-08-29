<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="decorator" content="default"/>
    <link rel="stylesheet" href="${ctxStatic}/new-templates/css/index.css">
    <script src="${ctxStatic}/new-templates/js/jquery-3.3.1.min.js"></script>
    <script src="${ctxStatic}/new-templates/js/dis_dev.js"></script>
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
<body>
<!--提示-->
<div class="tishi"></div>
<div class="syn_popup popup">
    <div class="pop_center">
        <div class="pc_top">
            <span class="fl">提示</span>
            <span class="fr close"><img src="${ctxStatic}/new-templates/img/close.png" alt=""></span>
        </div>
        <div class="pc_bottom">
            <p>同步会将云端已有的人员、照片信息同步到设备上，设备与云端数据保持一致。是否进行同步?</p>
            <input type="button" class="cancel fr" value="取消">
            <input type="button" class="query fr" value="确定">
        </div>
    </div>
</div>
<div class="sta_popup popup">
    <div class="pop_center" style="height: 422px">
        <div class="pc_top">
            <span class="fl">提示</span>
            <span class="fr close"><img src="${ctxStatic}/new-templates/img/close.png" alt=""></span>
        </div>
        <div class="pc_bottom">
            <div>
                <label class="name">设备状态:</label>
                <input type="text" maxlength="16" value="" class="pc_concent duan" placeholder="已同步">
                <input type="button" class="disable fr" value="禁用">
            </div>
            <div>
                <label class="name">网络状态</label>
                <input type="text" maxlength="16" value="" class="pc_concent" placeholder="在线">
            </div>
            <div>
                <label class="name">&nbsp&nbsp版本号:</label>
                <input type="text" maxlength="16" value="" class="pc_concent" placeholder="4.0311">
            </div>
            <input type="button" class="cancel fr" value="取消">
            <input type="button" class="query fr" value="确定">
        </div>
    </div>
</div>

<div class="workcontent add_big">
    <div class="big_top">
        <div class="operation fl">
            <div>添加设备</div>
        </div>
    </div>
    <div class="big_bottom">
        <div>
            <label>终端序列号：</label>
            <input type="text" maxlength="16" value="" class="scInput chang" placeholder="请输入设备序列号">
            <label>设备名称：</label>
            <input type="text" maxlength="16" value="" class="scInput chang" placeholder="请输入设备序列号">
        </div>
        <div>
            <label>识别设备：</label>
            <input type="text" maxlength="16" value="" class="scInput chang" placeholder="请输入识别设备">
            <label>版本号：</label>
            <input type="text" maxlength="16" value="" class="scInput chang" placeholder="请输入版本号">
        </div>
        <div class="button">
            <input type="button" class="confirm fl" value="确认">
            <input type="button" class="return fr" value="返回">
        </div>
       </div>
</div>
<div class="workcontent aut_big">
    <div class="big_top">
        <div class="operation fl">
            <div>人员授权</div>
        </div>
    </div>
    <div class="c_bottom">
        <table class="tableList">
            <tbody><tr>
                <th width="8%">
                    <div class="radio i-checks">
                        <input id="color-input-red" class="chat" type="checkbox" name="color-input-red" value="#f0544d" />
                        <label  for="color-input-red" class="choice"></label >
                    </div>
                </th>
                <th width="23%">姓名</th>
                <th width="23%">Guid</th>
                <th width="23%">注册照片</th>
                <th width="23%">照片注册状态</th>
            </tr>
            </tbody>
        </table>
        <div class="result">
            <table class="tableitem">
                <tbody id="aut_result">
          <!--      <tr>
                    <td width="8%">
                        <div class="radio i-checks">
                            <input id="color-input-red" class="chat" type="checkbox" name="color-input-red" value="#f0544d" />
                            <label  for="color-input-red" class="choice"></label >
                        </div>
                    </td>
                    <td width="23%">姓名</td>
                    <td width="23%">Guid</td>
                    <td width="23%">注册照片</td>
                    <td width="23%">照片注册状态</td>
                </tr>-->
                </tbody></table>
        </div>
        <div class="button">
            <input type="button" class="confirm fl" value="授权">
            <input type="button" class="return fr" value="返回">
        </div>
    </div>
</div>


<div class="workcontent all_big">
	<form:form id="searchForm" modelAttribute="device" action="${ctx}/brf/device/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <div class="c_top">
        <div class="operation fl">
            <div class="add">添加设备</div>
            <label class="dev_num" ></label>
        </div>
        <div class="information fr">
            <label>设备序列号：</label>
            <form:input path="seq" htmlEscape="false" maxlength="255" class="scInput chang" placeholder="请输入设备序列号"/>
            <input type="submit" class="query" value="查询">
        </div>
    </div>
    </form:form>
    <div class="c_bottom">
        <table class="tableList">
            <tbody><tr>
                <th width="14%">终端序列号</th>
                <th width="12%">设备名称</th>
                <th width="17%">识别设备</th>
                <th width="12%">版本号</th>
                <th width="17%">创建时间</th>
                <shiro:hasPermission name="brf:device:edit"><th width="28%">操作</th></shiro:hasPermission>
            </tr>
            </tbody>
        </table>
        <div class="result">
            <table class="tableitem">
            <tbody id="result">
            <tr>
            	<c:forEach items="${page.list}" var="device">
                <th width="14%">
                	<a href="${ctx}/brf/device/form?id=${device.id}">
					${device.seq}
					</a>
                </th>
                <th width="12%">${device.name}</th>
                <th width="17%">${device.aisle}</th>
                <th width="12%">${device.ver}</th>
                <th width="17%"><fmt:formatDate value="${device.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></th>
               <shiro:hasPermission name="brf:device:edit"><th width="28%">
    				<a href="javaScript:;">状态查询</a>
    				<a href="${ctx}/brf/device/authorization?id=${device.id}">人员授权</a>
					<a  href="javascript:;" onclick="delDev('${device.id}')">删除</a>
					<a href="javaScript:;">设备同步</a>
				</th></shiro:hasPermission>
           </tr>
           </c:forEach>
            </tbody></table>
        </div>
        <div class="pagination">${page}</div>
    </div>
</div>
<script>
	$(function () {
		$(".add").click(function () {
			window.location.href= "${ctx}/brf/device/form"
		})
	})
	
	function delDev (id) {
			if(confirm('要删除该设备吗？')){
				$.getJSON('${ctx}/brf/device/delete?id=' + id)
				setTimeout(function () {
   					window.location.reload();
   				},300)
			}
		}
	
</script>
</body>
</html>