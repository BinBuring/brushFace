/**
 * Created by wzk111 on 2018/8/22.
 */
$(function () {
    $.ajax({
        url:"../json/department.json",
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
                var tableitem=$("<tr class='tableitem'> " +
                    "<td width='6%'>" +
                    "<div class='radio i-checks'> " +
                    "<input id='"+_serial+"' class='chat' type='checkbox' name='color-input-red' value='#f0544d'/> " +
                    "<label  for='"+_serial+"'></label > " +
                    "</div> </td> " +
                    "<td width='6%'>"+_serial+"</td> " +
                    "<td width='37%'>"+result[i].department+"</td> " +
                    "<td width='37%'>"+result[i].department+"</td> " +
                    "<td class='blue modify' width='14%'><span>修改部门名称</span></td></tr>")
                $("#result").append(tableitem)
                goPage(ii, jj);
                $(".pages").html(pageNumber)
                $(".data").html(result.length)
                var first_Li=$("<li>"+result[i].department+"</li>")
                $(".first").append(first_Li)
                var second_Li=$("<li>"+result[i].department+"</li>")
                $(".second").append(second_Li)
                var status_Li=$("<li>"+result[i].state+"</li>")
                $(".status").append(status_Li)
            })
        }});

    $(document).on("click",'.modify',function(){
        var index= $(this).index();
        $(".mod_popup").css("display","block")
    });

    $(".pc_ul p").click(function(){
        if($(this).next().height()==0){
            $(this).next().css({"height":"150px","border":"1px solid #d3d3d3"})
        }else{
            $(this).next().css({"height":"0px","border":"none"})
        }
    })

    $(".close,.cancel").click(function () {
        $(".popup").css("display","none")
    })



    $(document).on("click",'.choice li',function(){
        var i = $(this).index();//下标第一种写法
        $(this).addClass('yes').siblings().removeClass('yes');
        $(this).parent().prev().html($(this).html())
    });
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
