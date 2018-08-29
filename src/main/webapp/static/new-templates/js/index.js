/**
 * Created by wzk111 on 2018/8/22.
 */
$(function () {
    $('.sidebar_item>p').click(function() {
        var i = $(this).parent().index();//下标第一种写法
        //$(this).addClass('select').siblings().removeClass('select');
        $('.drop_down').not($('.drop_down').eq(i)).slideUp();
        $('.drop_down').eq(i).slideToggle();
    });

    $('.drop_down>div').click(function() {
        var i = $(this).index();//下标第一种写法
        $(this).addClass('on').siblings().removeClass('on');
    });

//    $.ajax({
//        url:"../json/result.json",
//        success:function(res){
//            var data=eval(res);
//            var result=data.result;
//            $.each(result,function (i) {
//                var _serial=i+1;
//                if(result.length%12==0){
//                    pageNumber=parseInt(result.length/12);
//                }else{
//                    pageNumber=parseInt(result.length/12+1);
//                }
//                console.log(pageNumber);
//                //var Li=$("<li>"+_serial+"</li>")
//                //$("#page").before(Li)
//                var tableitem=$("<table class='tableitem'><tbody><tr> " +
//                    "<th width='6%'> " +
//                "<div class='radio i-checks'> " +
//                    "<input id='"+_serial+"' class='chat' type='checkbox' name='color-input-red' value='#f0544d' /> " +
//                "<label  for='"+_serial+"'></label > </div> " +
//                    "</th> <th width='6%'>"+_serial+"</th> " +
//                    "<th width='8%'>"+result[i].worknum+"</th> " +
//                    "<th width='7%'>"+result[i].name+"</th> " +
//                    "<th width='7%'><p>"+result[i].department+"</p></th>" +
//                    "<th width='10%'><img src='"+result[i].head+"' alt=''></th>" +
//                    "<th width='12%'>"+result[i].entry_time+"</th> " +
//                    "<th width='7%'>"+result[i].type+"</th> " +
//                    "<th width='7%'>"+result[i].state+"</th>" +
//                    " <th width='10%' class='blue'>"+result[i].information+"</th>" +
//                    "<th width='10%' class='blue'>"+result[i].record+"</th>" +
//                    "<th class='blue'><span>查看</span><span>修改</span></th>" +
//                    "</tr> </tbody></table>")
//                $(".result").append(tableitem)
//                goPage(ii, jj);
//                $(".pages").html(pageNumber)
//                $(".data").html(result.length)
//            })
//        }});
//
//    var pageNum = 1;
//    var pageNumber=1;
//    var ii = 0;
//    var jj = 12;//每页显示五条数据
//    function goPage(i, j) {
//        $('.result table').css('display','none');
//        for (i; i < j ; i++) {
//            $('.result table').eq(i).css('display','table');
//        }
//    }
////跳页
//    $('.goPage').click(function () {
//        //cbHeight();
//        pageNumber= $(".pages").html();
//        pageNum = ($('.inputPage').val()) * 1;
//        if (pageNum <= pageNumber && pageNum > 0) {
//            ii = (pageNum - 1) * 12;
//            jj = pageNum * 12;
//            goPage(ii, jj);
//        }
//        else {
//            $(".tishi").text("请输入正确的页码！");
//            $(".tishi").fadeIn(500).delay(600).fadeOut(500);
//        }
//    })
})
