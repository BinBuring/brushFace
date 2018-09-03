/**
 * Created by wzk111 on 2018/8/22.
 */
$(function () {
    var count = 0;
    var a=0;
    $.ajax({
        url:"../json/result.json",
        success:function(res){
            var data=eval(res);
            var result=data.result;
            $.each(result,function (i) {
                var _serial=i+1;
                if(result.length%12==0){
                    pageNumber=parseInt(result.length/12);
                }else{
                    pageNumber=parseInt(result.length/12+1);
                }
                //var Li=$("<li>"+_serial+"</li>")
                //$("#page").before(Li)
                var tableitem=$("<tr class='tableitem'>" +
                    "<td width='6%'> " +
                    "<div class='radio i-checks'> " +
                    "<input id='"+_serial+"' class='chat' type='checkbox' name='color-input-red' value='#f0544d' /> " +
                    "<label  for='"+_serial+"'></label > </div> " +
                    "</td> <td width='6%'>"+_serial+"</td> " +
                    "<td width='8%'>"+result[i].worknum+"</td> " +
                    "<td width='8%'>"+result[i].name+"</td> " +
                    "<td width='10%'>"+result[i].department+"</td>" +
                    "<td width='15%'>"+result[i].entry_time+"</td> " +
                    "<td width='8%'>"+result[i].type+"</td> " +
                    "<td width='8%'>"+result[i].state+"</td>" +
                    "<td width='11%'><img src='"+result[i].head+"' alt=''></td>" +
                    "<td width='11%'>"+result[i].authoriz+"</td>" +
                    "<td width='10%' class='blue'><span class='edit' name='"+i+"'>编辑</span><span class='authorize'>授权</span><span>删除</span></td>" +
                    "</tr>")
                $("#result").append(tableitem)
                goPage(ii, jj);
                $(".pages").html(pageNumber)
                $(".data").html(result.length)
                var first_Li=$("<li>"+result[i].department+"</li>")
                $(".first").append(first_Li)
                var second_Li=$("<li>"+result[i].department+"</li>")
                $(".second").append(second_Li)

            })
            
        }});
    $(".wait_audit").click(function () {
        $("#result").html("")
        $(".state").html("审核状态")
        $.ajax({
            url:"../json/result.json",
            success:function(res){
                var data=eval(res);
                var result=data.result;
                $.each(result,function (i) {
                    var _serial=i+1;
                    if(result.length%12==0){
                        pageNumber=parseInt(result.length/12);
                    }else{
                        pageNumber=parseInt(result.length/12+1);
                    }
                    //var Li=$("<li>"+_serial+"</li>")
                    //$("#page").before(Li)
                    if(result[i].serial!=1){
                        var tableitem=$("<tr class='tableitem'>" +
                            "<td width='6%'> " +
                            "<div class='radio i-checks'> " +
                            "<input id='"+_serial+"' class='chat' type='checkbox' name='color-input-red' value='#f0544d' /> " +
                            "<label  for='"+_serial+"'></label > </div> " +
                            "</td> <td width='6%'>"+_serial+"</td> " +
                            "<td width='8%'>"+result[i].worknum+"</td> " +
                            "<td width='8%'>"+result[i].name+"</td> " +
                            "<td width='10%'>"+result[i].department+"</td>" +
                            "<td width='15%'>"+result[i].entry_time+"</td> " +
                            "<td width='8%'>"+result[i].type+"</td> " +
                            "<td class='red' width='8%'>"+result[i].state+"</td>" +
                            "<td width='11%'><img src='"+result[i].head+"' alt=''></td>" +
                            "<td width='11%'>"+result[i].authoriz+"</td>" +
                            "<td width='10%' class='blue'><span>授权</span><span class='examine'>审核</span><span>删除</span></td>" +
                            "</tr>")
                        $("#result").append(tableitem)
                        goPage(ii, jj);
                    }

                })
            }});
    })

    $(".not_active").click(function () {
        $("#result").html("")
        $.ajax({
            url:"../json/result.json",
            success:function(res){
                var data=eval(res);
                var result=data.result;
                $.each(result,function (i) {
                    var _serial=i+1;
                    if(result.length%12==0){
                        pageNumber=parseInt(result.length/12);
                    }else{
                        pageNumber=parseInt(result.length/12+1);
                    }
                    //var Li=$("<li>"+_serial+"</li>")
                    //$("#page").before(Li)
                    if(result[i].state!="正常状态"){
                        var tableitem=$("<tr class='tableitem'>" +
                            "<td width='6%'> " +
                            "<div class='radio i-checks'> " +
                            "<input id='"+_serial+"' class='chat' type='checkbox' name='color-input-red' value='#f0544d' /> " +
                            "<label  for='"+_serial+"'></label > </div> " +
                            "</td> <td width='6%'>"+_serial+"</td> " +
                            "<td width='8%'>"+result[i].worknum+"</td> " +
                            "<td width='8%'>"+result[i].name+"</td> " +
                            "<td width='10%'>"+result[i].department+"</td>" +
                            "<td width='15%'>"+result[i].entry_time+"</td> " +
                            "<td width='8%'>"+result[i].type+"</td> " +
                            "<td width='8%'>"+result[i].state+"</td>" +
                            "<td width='11%'><img src='"+result[i].head+"' alt=''></td>" +
                            "<td width='11%'>"+result[i].authoriz+"</td>" +
                            "<td width='10%' class='blue'><span class='edit' name='"+i+"'>编辑</span><span class='authorize'>授权</span><span>删除</span></td>" +
                            "</tr>")
                        $("#result").append(tableitem)
                        goPage(ii, jj);
                    }

                })
            }});
    })

    /*$(".delete").click(function (){
        for(var i=0;i<$('#result tr').length;i++){
            if($('#result tr').eq(i).css('display')=="table-row"){
                //当选中的长度等于checkbox的长度的时候,就让控制全选反选的checkbox设置为选中,否则就为未选中
                if($(".chat").eq(i+1).is(':checked')){
                    count++;
                    //选中的操作
                }if( count == 0 ){
                    $(".pc_bottom p").html("对不起，请选择要删除的项目")
                    $(".qrcode_popup").css("display","block")
                }
                
                
            }
        }

    })*/

    $(document).on("click",'.edit',function(){
            var a=$(this).attr("name");
        $(".all_big").css("display","none")
        $(".add_big").css("display","block")

    })

    $(document).on("click",'.chat',function(){
        if($(this).prop("checked")==true){
            count++;
        }else{
            count=0;
        }

    })

    $(document).on("click",'.authorize',function(){
        $.ajax({
            url:"../json/result.json",
            success:function(res){
                var data=eval(res);
                var result=data.result;
                $.each(result,function (i) {
                    var _serial=i+1;
                    if(result.length%12==0){
                        pageNumber=parseInt(result.length/12);
                    }else{
                        pageNumber=parseInt(result.length/12+1);
                    }
                    var tableitem=$("<tr class='tableitem'> " +
                        "<td width='8%'>" +
                        "<div class='radio i-checks'> " +
                        "<input id='"+_serial+"' class='chat' type='checkbox' name='color-input-red' value='#f0544d'/> " +
                        "<label  for='"+_serial+"'></label > " +
                        "</div> </td>" +
                        "<td width='23%'>"+result[i].name+"</td> " +
                        "<td width='23%'>"+result[i].worknum+"</td> " +
                        "<td width='23%'><img src='"+result[i].head+"' alt=''></td>" +
                        "<td width='23%'>"+result[i].authoriz+"</td> " +
                        "</tr>")
                    $("#aut_result").append(tableitem)
                    goPage(ii, jj);
                })
            }});
        $(".all_big").css("display","none")
        $(".aut_big").css("display","block")
    });


    $(".save").click(function () {
        $(".pc_bottom p").html("对不起，未检测到人脸请重新上传")
        $(".qrcode_popup").css("display","block")
    })

    $(".confirm").click(function () {
        $(".pc_bottom p").html("授权成功")
        $(".qrcode_popup").css("display","block")
    })

    $(".query").click(function () {
        $(".popup").css("display","none")
    })

   
    $(".return").click(function () {
        $(".all_big").css("display","block")
        $(".add_big").css("display","none")
    })

    $(".close").click(function () {
        $(".popup").css("display","none")
    })

    $(document).on("click",'.examine',function(){
        $(".pc_bottom p").html("审核成功")
        $(".qrcode_popup").css("display","block")
    });

    $(".choice p").click(function(){
        if($(this).parent().height()==30){
            $(this).parent().css("height","190px")
        }else{
            $(this).parent().css("height","30px")
        }
    })

    $(".choice2 p").click(function(){
        if($(this).parent().height()==30){
            $(this).parent().css("height","120px")
        }else{
            $(this).parent().css("height","30px")
        }
    })

    $(document).on("click",'#first li',function(){
        var i = $(this).index();//下标第一种写法
        $(this).addClass('yes').siblings().removeClass('yes');
        $(this).parent().prev().css("color","#00a1e2")
        $(this).parent().prev().html($(this).html())
    });

    $(document).on("click",'#second li',function(){
        if($("#first p").html()=="全部"){
            $(".pc_bottom p").html("请选择一级部门后再选择二级部门")
            $(".qrcode_popup").css("display","block")
        }else{
            var i = $(this).index();//下标第一种写法
            $(this).addClass('yes').siblings().removeClass('yes');
            $(this).parent().prev().css("color","#00a1e2")
            $(this).parent().prev().html($(this).html())
        }

    });

    $('.choice2 li').click(function(){
        var i = $(this).index();//下标第一种写法
        $(this).addClass('yes').siblings().removeClass('yes');
        $(this).parent().children().eq(0).html($(this).html())
        $(this).parent().children().eq(0).css("color","#00a1e2")
    });

    $("#color-input-red").click(function () {
        for(var i=0;i<$('#result tr').length;i++){
            if($('#result tr').eq(i).css('display')=="table-row"){
                var $subs = $(".chat").eq(i+1);
                //当选中的长度等于checkbox的长度的时候,就让控制全选反选的checkbox设置为选中,否则就为未选中
                $subs.prop("checked", $(this).prop('checked'));
            }
        }
    })

    var pageNum = 1;
    var pageNumber=1;
    var ii = 0;
    var jj = 12;//每页显示五条数据
    function goPage(i, j) {
        $('#result tr').css('display','none');
        for (i; i < j ; i++) {
            $('#result tr').eq(i).css('display','table-row');
        }
    }
//跳页
    $('.goPage').click(function () {
        //cbHeight();
        pageNumber= $(".pages").html();
        pageNum = ($('.inputPage').val()) * 1;
        if (pageNum <= pageNumber && pageNum > 0) {
            ii = (pageNum - 1) * 12;
            jj = pageNum * 12;
            goPage(ii, jj);
        }
        else {
            $(".tishi").text("请输入正确的页码！");
            $(".tishi").fadeIn(500).delay(600).fadeOut(500);
        }
    })

    $('#upload').change(function(){
        // 获取FileList的第一个元素
        var f = document.getElementById('upload').files[0];
        var src = window.URL.createObjectURL(f);
        document.getElementById('ImgPr').src = src;
        $(".up_success").css("display","block")

    })
})
