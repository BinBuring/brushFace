<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctxStatic}/new-templates/css/index.css">
</head>
<body>
<div class="box">
    <div class="qr_box">
        <div class="qr_center">
            <img class="ewm" id="imgId" src="${ctxStatic}/new-templates/img/erweima.png" alt="">
            <div class="download">
                <p onclick="downloadIamge('.ewm','erweima.png')" style="cursor: pointer;" class="p1">下载</p>
                <p class="p1 fanhui"><a href="${ctx}/sys/emp/list"  style="color: white;text-decoration:none">返回</a></p>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
function downloadIamge(selector, name) {  
    // 通过选择器获取img元素，  
    var img = document.querySelector(selector)  
    // 将图片的src属性作为URL地址  
    var url = img.src  
    var a = document.createElement('a')  
    var event = new MouseEvent('click')  
      
    a.download = name || '下载图片名称'  
    a.href = url  
      
    a.dispatchEvent(event)  
}
</script>
</body>
</html>