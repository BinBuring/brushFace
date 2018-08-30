/**
 * Created by wzk111 on 2018/8/27.
 */
$(function () {
    var a=0;
    var b=0;
    //点击图片上传
    $('#upload').change(function(){
        // 获取FileList的第一个元素
        b=1;
        console.log(b);
        var f = document.getElementById('upload').files[0];
        var src = window.URL.createObjectURL(f);
        document.getElementById('ImgPr').src = src;
        if(b==1){
            $(".re_upload").css({"background":"#2a69b6","color":"#fff"})
        }
    })

    //重新上传
    $('#re_upload').change(function(){
        // 获取FileList的第一个元素
        var f = document.getElementById('re_upload').files[0];
        var src = window.URL.createObjectURL(f);
        document.getElementById('ImgPr').src = src;
    })

    //确认上传
    $(".confirm_upload").click(function () {
        $(".popup2").css("display","block")
    })
    
    var uploading = false;
    //上传成功或失败
    $(".confirm").click(function () {
        if(a==0){
            $(".popup2").css("display","none")
            $(".popup").css("display","block")
            if(uploading){
		        alert("文件正在上传中，请稍候");
		        return false;
		    }
            //获取项目路径
            var strFullPath=window.document.location.href;
            var strPath=window.document.location.pathname;
            var pos=strFullPath.indexOf(strPath);
            var prePath=strFullPath.substring(0,pos);
            var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
            var webPath=prePath+postPath;

            var formData = new FormData();
        	formData.append("file",$("#upload")[0].files[0]);
        	//var type = $('#file_type').val()
            //var user = $('#file_user').val()
            formData.append("type","jpg");
            var no = $("#no").val();
            formData.append("user",no);
		    $.ajax({
		        url: webPath+"/a/brf/empRecord/upload",
		        type: 'POST',
		        cache: false,
		        data: formData,
		        processData: false,
		        contentType: false,
		        dataType:"json",
		        beforeSend: function(){
		            uploading = true;
		        },
		        success : function(data) {
		            if (data.code == 1) {
		                $("#logo").attr("src", data.msg);
		            } else {
		                alert(data.msg);
		            }
		            uploading = false;
		    	}});
		        
        }else{
            $(".popup2").css("display","none")
            $(".pc_top").html("上传失败，请重新上传")
            $(".popup").css("display","block")
        }
    })

    $(".yes").click(function () {
        $("#upload").css("display","none")
        $(".popup").css("display","none")
    })
    $(".cancel").click(function () {
        $("#re_upload").css("display","block")
        $(".popup2").css("display","none")
    })
})

