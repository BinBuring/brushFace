/**
 * Created by wzk111 on 2018/8/22.
 */
$(function () {
    $.ajax({
        url:"../json/dis_dev.json",
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
                    " <td width='14%'>"+result[i].serial+"</td> " +
                    " <td width='12%'>"+result[i].device_name+"</td> " +
                    " <td width='17%'>"+result[i].distinguish+"</td> " +
                    " <td width='12%'>"+result[i].version+"</td>" +
                    " <td width='17%'>"+result[i].entry_time+"</td>" +
                    " <td  class='blue' width='28%'><span class='state'>状态查询</span><span class='authorize'>人员授权</span><span>删除</span><span class='synch'>设备同步</span> " +
                    "</tr>")
                $("#result").append(tableitem)
                goPage(ii, jj);
                $(".pages").html(pageNumber)
                $(".data").html(result.length)
                $(".dev_num").html("当前设备数量（"+result.length+"）")
            })
        }});

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
                        "<td width='23%'><img src='"+result[i].head+"' alt=''></td> " +
                        "</tr>")
                     if(result[i].authoriz=="未授权"){
                         var register=$("<td width='23%'class='red'>"+result[i].authoriz+"</td>")
                     }else {
                         var register=$("<td width='23%'>"+result[i].authoriz+"</td>")
                     }
                    tableitem.append(register)
                    $("#aut_result").append(tableitem)
                    goPage(ii, jj);
                })
            }});
        $(".all_big").css("display","none")
        $(".aut_big").css("display","block")
    });

    $(document).on("click",'.synch',function(){
        $(".syn_popup").css("display","block")
    });
    $(document).on("click",'.state',function(){
        $(".sta_popup").css("display","block")
    });
    $(".close,.cancel").click(function () {
        $(".popup").css("display","none")
    })

    $(".add").click(function () {
        $(".all_big").css("display","none")
        $(".add_big").css("display","block")
    })
    $(".return").click(function () {
        $(".all_big").css("display","block")
        $(".add_big").css("display","none")
        $(".aut_big").css("display","none")
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
})
