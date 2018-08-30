<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登陆</title>
    <link rel="stylesheet" href="${ctxStatic}/new-templates/css/login.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<style>
		#messageBox{
			font-size: 14px;
		}
		.hide{
			display: none;
			
		}
		
	</style>
    
</head>
<body>
	
   <div class="box">
   		
       <div class="content">
       	<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
           <div class="input_list">
               <div class="name list">
                   <img src="${ctxStatic}/new-templates/img/name.png" alt="" class="icon">
                   <input type="text" placeholder="登录名" name="username">
               </div>
               <div class="pass list">
                   <img src="${ctxStatic}/new-templates/img/pass.png" alt="" class="icon">
                   <input type="password" placeholder="密码" name="password">
               </div>
               <div class="code list">
                   <img src="${ctxStatic}/new-templates/img/code.png" alt="" class="icon">
                   <input type="text" placeholder="验证码" id="code">
                   <!--<img src="${ctxStatic}/new-templates/img/code_03.jpg" alt="">-->
                   <div id="drawcode" class="codeimg" >
                       <span>获取验证码</span>
                       <canvas width="130" height="60" id="c1">
                           您的浏览器不支持canvas，请换个浏览器试试~
                       </canvas>
                   </div>
               </div>
               <div class="logo">登录</div>
               <div class="remember">
                   <input id="color-input-red" class="chat-button-location-radio-input" type="checkbox" name="rememberMe"  ${rememberMe ? 'checked' : ''} />
                   <label  for="color-input-red"></label >
                   <span>记住密码</span>
                   
               </div>
				<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}">
					<label id="loginError" class="error">${message}</label>
					</div>
           </div>
           </form>
       </div>
   </div>
</body>

<script>
	var code = '';
    var pool="abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLIMNOPQRSTUVWSYZ1234567890";
    //1.新建一个函数产生随机数
    function rn(min,max){
        return  parseInt(Math.random()*(max-min)+min);
        
    }
    //2.新建一个函数产生随机颜色
    function rc(min,max){
        var r=rn(min,max);
        var g=rn(min,max);
        var b=rn(min,max);
        return 'rgb' + "("+ r +"," + g +"," + b + ")" ;
    }
    //3.填充背景颜色,颜色要浅一点
    function drawcode(){
        var w=130;
        var h=60;
        var ctx=c1.getContext("2d");

        var gradient=ctx.createLinearGradient(rn(0,30),rn(0,30),rn(100,130),rn(30,40));
        gradient.addColorStop(0,rc(180,230));
        gradient.addColorStop(0.5,rc(180,230));
        gradient.addColorStop(1,rc(180,230));

        ctx.fillStyle=gradient;
        ctx.fillRect(0,0,w,h);
         code = ''
        //4.随机产生字符串
        for(var i=0;i<4;i++){
            var c=pool[rn(0,pool.length)];//随机的字
            code +=c;
            var fs=rn(24,50);//字体的大小
            var deg=rn(-30,30);//字体的旋转角度
            // 设置文字阴影的颜色为黑色，透明度为20%
            ctx.shadowColor =rc(180,230);
            // 将阴影向右移动15px，向上移动10px
            ctx.shadowOffsetX = 5;
            ctx.shadowOffsetY = 5;
            // 轻微模糊阴影
            ctx.shadowBlur = 5;
            ctx.font=fs+'px Simhei';
            ctx.textBaseline="top";
            ctx.strokeStyle=rc(0,100);
            ctx.fillStyle=rc(80,150);
            ctx.save();
            ctx.translate(30*i+15,15);
            ctx.rotate(deg*Math.PI/180);
            ctx.strokeText(c,0,-5,110);
            ctx.fillText(c,0,-5,110);
            ctx.restore();
        }
        //5.随机产生5条干扰线,干扰线的颜色要浅一点
        for(var i=0;i<10;i++){
            ctx.lineWidth = rn(0,2);
            ctx.beginPath();
            ctx.moveTo(rn(0,w),rn(0,h));
            ctx.lineTo(rn(0,w),rn(0,h));
            ctx.strokeStyle=rc(180,230);
            ctx.closePath();
            ctx.stroke();
        }
        //6.随机产生40个干扰的小点
        for(var i=0;i<50;i++){
            ctx.beginPath();
            ctx.arc(rn(0,w),rn(0,h),1,0,2*Math.PI);
            ctx.closePath();
            ctx.fillStyle=rc(150,200);
            ctx.fill();
        }
    }
    var Drawcode=document.getElementById("drawcode");
    Drawcode.onclick= function () {
        this.children[0].style.display="none"
        drawcode();
    }
     $(function () {
     	$("#drawcode").click();
   			 $(".logo").click(function () {
   			 	var insertCode = $("#code").val();
   			 	if(!insertCode || insertCode.toUpperCase() != code.toUpperCase()){
   			 		$("#messageBox").show();
   			 		$("#loginError").text('验证码不正确');
   			 		$("#drawcode").click();
   			 		$("#code").val('');
   			 		return;
   			 	}
    	$("#loginForm").submit()
    })
   })
</script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
					validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				} 
			});
		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
	</script>
</html>