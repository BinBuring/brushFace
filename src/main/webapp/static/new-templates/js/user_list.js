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
                    "<td width='6%'> <div class='radio i-checks'> <input id='"+_serial+"' class='chat' type='checkbox' name='color-input-red' value='#f0544d' /> <label  for='"+_serial+"'></label > </div> </td>" +
                    " <td width='6%'>"+_serial+"</td>" +
                    " <td width='18%'>"+result[i].name+"</td> " +
                    "<td width='22%'>"+result[i].department+"</td>" +
                    " <td width='22%'>"+result[i].entry_time+"</td></tr>")
                if(result[i].serial==1){
                    var operation=$("<td class='blue' width='12%'><span>重置密码</span></td>")
                }else{
                    var operation=$("<td class='blue' width='12%'><span class='reset'>重置密码</span><span class='modify'>修改</span></td>")
                }
                tableitem.append(operation)
                $("#result").append(tableitem)
            })
        }});

    $(document).on("click",'.reset',function(){
        $(".pc_bottom p").html("您是否要重置密码")
        if(true){
            $(".pc_bottom p").html("恭喜您，重置密码为1234")
        }
        $(".qrcode_popup").css("display","block")
    })

    $(".close").click(function () {
        $(".popup").css("display","none")
    })

    $(document).on("click",'.modify',function(){
        $(".all_big").css("display","none")
        $(".mod_big").css("display","block")
    });
    $(".add").click(function () {
        $(".all_big").css("display","none")
        $(".add_big").css("display","block")
    })
    $(".return").click(function () {
        $(".all_big").css("display","block")
        $(".mod_big").css("display","none")
        $(".add_big").css("display","none")
    })
})
