/**
 * Created by wzk111 on 2018/8/22.
 */
$(function () {
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
                var tableitem=$("<tr class='tableitem'> " +
                    "<td width='6%'> " +
                    "<div class='radio i-checks'> " +
                    "<input id='"+_serial+"' class='chat' type='checkbox' name='color-input-red' value='#f0544d' /> " +
                    "<label  for='"+_serial+"'></label ></div></td>" +
                    "<td width='8%'>"+_serial+"</td>" +
                    "<td width='10%'>"+result[i].worknum+"</td> " +
                    "<td width='10%'>"+result[i].name+"</td> " +
                    "<td width='7%'>"+result[i].department+"</td>" +
                    "<td width='12%'>"+result[i].type+"</td> " +
                    "<td width='16%'>"+result[i].entry_time+"</td> " +
                    "<td width='16%'>"+result[i].entry_time+"</td> " +
                    "<td class='blue' width='15%'><span>编辑用户</span><span>延期操作</span></td> " +
                    "</tr>")
                $("#result").append(tableitem)
                goPage(ii, jj);
                $(".pages").html(pageNumber)
                $(".data").html(result.length)
            })
        }});

    $('.add').click(function(){
        var index= $(this).index();
        $(".mod_popup").css("display","block")
    });
    $(".close,.cancel").click(function () {
        $(".popup").css("display","none")
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
