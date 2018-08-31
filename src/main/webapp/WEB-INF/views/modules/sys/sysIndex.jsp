<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${fns:getConfig('productName')}</title>
    <link rel="stylesheet" href="${ctxStatic}/new-templates/css/index.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="${ctxStatic}/new-templates/js/index.js"></script>
    <style>
    </style>
</head>
<body>
<div class="box">
    <!--提示-->
    <div class="tishi"></div>
    <!--头部-->
    <div class="top">
        <div class="logo fl">
            <img src="${ctxStatic}/new-templates/img/logo.jpg" alt="">
        </div>
        <ul class="nav fl">
            <li onclick="javascript:window.location.reload()" style="cursor: pointer;">
                <img src="${ctxStatic}/new-templates/img/icon_1.jpg" alt="">
                <p>首页</p>
            </li>
            <li style="cursor: pointer;">
                <img src="${ctxStatic}/new-templates/img/icon_2.jpg" alt="">
                <p>监控中心</p>
            </li>
            <li onclick="chageIfr(this)" data="${ctx}/sys/emp/list" style="cursor: pointer;">
                <img src="${ctxStatic}/new-templates/img/icon_3.jpg" alt="">
                <p>员工管理</p>
            </li>
            <li onclick="chageIfr(this)" data="${ctx}/sys/user/list" style="cursor: pointer;">
                <img src="${ctxStatic}/new-templates/img/icon_4.jpg" alt="">
                <p>用户管理</p>
            </li>
            <li style="cursor: pointer;">
                <img src="${ctxStatic}/new-templates/img/icon_5.jpg" alt="">
                <p>平台设置</p>
            </li>
        </ul>
        <div class="login fr">
             <a href="${ctx}/logout" title="退出登录"><p>退出</p></a><p>欢迎 ${fns:getUser().name} 登录</p>
            <a href="javascript:;" data="${ctx}/sys/user/modifyPwd" onclick="chageIfr(this);"><p>修改密码</p></a>
           <p class="tran">退出</p><p class="tran">退出</p><p class="tran">退出</p>
        </div>
    </div>
    <!--中心-->
    <div class="container">
        <!--侧栏-->
        <div class="sidebar fl">
            <div class="scroll">
                <div class="sidebar_item staff_only">
                   <p><img src="" alt=""><span>员工通道管理</span></p>
                    <div class="drop_down">
                        
                    </div>
                </div>
                <div class="sidebar_item monitoring" style="cursor:pointer">
                    <p><img src="${ctxStatic}/new-templates/img/icon_6.jpg" alt=""><span>监控中心</span></p>
                    <div class="drop_down">
                    </div>
                </div>
                <div class="sidebar_item staff_admin" style="cursor:pointer">
                    <p ><img   src="${ctxStatic}/new-templates/img/icon_7.jpg" alt=""><span>员工管理</span></p>
                    <div class="drop_down">
                        <div class="on"><a href="javascript:;" data="${ctx}/sys/emp/list" onclick="chageIfr(this);"  target="employee_list">员工列表</a></div>
                        <div><a href="javascript:;" data="${ctx}/brf/empRecord/list" onclick="chageIfr(this);" target="employee_list" >员工记录</a></div>
                        <div><a href="javascript:;" data="${ctx}/sys/emp/empYQlist" onclick="chageIfr(this);" target="employee_list">需延期员工列表</a></div>
                        <div><a href="javascript:;" data="${ctx}/sys/office/list" onclick="chageIfr(this);" target="employee_list">部门管理</a></div>
                    </div>
                </div>
                <div class="sidebar_item user_admin " style="cursor:pointer">
                    <p><img src="${ctxStatic}/new-templates/img/icon_8.jpg" alt=""><span>用户管理</span></p>
                    <div class="drop_down">
                        <div><a href="javascript:;" data="${ctx}/sys/user/list" onclick="chageIfr(this);" target="employee_list">用户列表</a></div>
                        <div><a href="javascript:;" data="${ctx}/sys/log" onclick="chageIfr(this,980);" target="employee_list">操作记录表</a></div>
                    </div>
                </div>
                <div class="sidebar_item platform_sett" style="cursor:pointer">
                    <p><img src="${ctxStatic}/new-templates/img/icon_9.jpg" alt=""><span>平台设置</span></p>
                    <div class="drop_down">
                        <div><a data="${ctx}/brf/device" onclick="chageIfr(this);" target="employee_list">设备管理</a></div>
                        <div><a href="javascript:;" data="${ctx}/sys/sysParam/list" onclick="chageIfr(this);">系统参数管理</a></div>
                    </div>
                </div>
                <div class="sidebar_item big_data" style="cursor:pointer">
                    <p><img src="${ctxStatic}/new-templates/img/icon_10.jpg" alt=""><span>大数据云平台展示</span></p>
                    <div class="drop_down">
                    </div>
                </div>
            </div>

        </div>
        <!--内容-->
        <div class="content">
            <iframe name="employee_list" id="mainFrame" width=100% frameborder=0  height=810 scrolling=auto src="${ctx}/sys/emp/list"></iframe>
        </div>
    </div>

</div>
	<script>
		function chageIfr(a,height) {
			if(!height)height = 810
			var url = $(a).attr('data')
			$("#mainFrame").attr("src",url).attr("height",height)
		}
	</script>
</body>
</html>