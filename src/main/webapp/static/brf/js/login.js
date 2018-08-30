/**
 * Created by wzk111 on 2018/8/27.
 */
$(function () {
    var a=0
    $(".department input").focus(function () {
        if($(".worknum input").val()==""){
            $(".worknum input").addClass("red")
        }
    })
    $(".login").click(function () {
        if(a==0){
            window.location="upload.html"
        }else{
            $(".department input").val("")
            $(".department input").attr("placeholder","请输入正确的部门/单位")
            $(".department input").addClass("red")
            $(".worknum input").val("")
            $(".worknum input").attr("placeholder","请输入正确的工号")
            $(".worknum input").addClass("red")
        }
    })

})