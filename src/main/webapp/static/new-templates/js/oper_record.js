/**
 * Created by wzk111 on 2018/8/22.
 */
$(function () {
    $.ajax({
        url:"../json/em_rec.json",
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
                    " <td width='10%'>"+_serial+"</td> " +
                    " <td width='14%'>"+result[i].worknum+"</td> " +
                    " <td width='14%'>"+result[i].name+"</td>" +
                    " <td width='18%'>"+result[i].department+"</td>" +
                    " <td width='44%'>"+result[i].department+"</td>" +
                    " </tr>")
                $("#result").append(tableitem)
                goPage(ii, jj);
                $(".pages").html(pageNumber)
                $(".data").html(result.length)
            })
        }});

    $(".choice p").click(function(){
        if($(this).parent().height()==30){
            $(this).parent().css("height","auto")
        }else{
            $(this).parent().css("height","30px")
        }
    })

    $('.choice>li').click(function() {
        var i = $(this).index();//下标第一种写法
        $(this).addClass('yes').siblings().removeClass('yes');
        $(".choice p").html($(this).html())
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
