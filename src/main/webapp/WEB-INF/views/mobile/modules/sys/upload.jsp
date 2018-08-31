<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎登陆员工人脸注册系统</title>
    <meta name="viewport" content="width=750",user-scalable="no"/>
    <link rel="stylesheet" href="${ctxStatic}/brf/css/login.css">
    <link rel="stylesheet" href="${ctxStatic}/brf/css/upload.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="${ctxStatic}/brf/js/upload.js"></script>
</head>
<body>

<form id="upload_form" enctype='multipart/form-data'>
<div class="box">
	<div class="top">
        <p>欢迎登录 <br>员工人脸注册后台系统</p>
    </div>
    <div class="center_box">
	<div class="center">
            <div class="input worknum">
                <label>工号：</label>
                <input type="text" value="" id="no" name="no" class="scInput chang" placeholder="请输入工号">
            </div>
            <div class="input department">
                <label>部门：</label>
                <input type="text" value="" class="scInput chang" placeholder="请输入部门/单位">
            </div>
            
        </div>
      </div>  
    <div class="image">
        <img id="ImgPr" src="${ctxStatic}/brf/img/back_03.jpg" alt="">
        <div class="upload"> <input id="upload" type="file"></div>
    </div>
    <div class="tips">注：请上传清晰正脸的生活照</div>
      <div class="angle">调整角度</div>
    <div class="confirm_upload">确认上传</div>
    <!-- <div class="re_upload">重新上传<input id="re_upload" type="file"></div> -->
    <div class="popup2">
        <div class="pop_center2">
            <div class="pc_top">
                <p class="p1">提示</p>
                <p class="p2">【识别照片】上传后，将不可修改</p>
            </div>
            <div class="pc_bottom">
                <p class="fl cancel">取消</p>
                <p class="fr confirm">确认</p>
            </div>
        </div>
    </div>
    <div class="popup">
        <div class="pop_center">
            <div class="pc_top">上传成功，管理员正在审核中</div>
            <div class="pc_bottom yes">确定</div>
        </div>
    </div>


</div>
</form>

</body>
</html>