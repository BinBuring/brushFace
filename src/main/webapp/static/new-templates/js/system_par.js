
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
                    " <td width='10%'>"+_serial+"</td> " +
                    " <td class='pra_type' width='30%'>"+result[i].device_name+"</td> " +
                    " <td class='pra_con' width='20%'>"+result[i].version+"</td>" +
                    " <td width='30%'>"+result[i].entry_time+"</td>" +
                    " <td  class='blue modify' width='10%'><span>修改</span> " +
                    "</tr>")
                $("#result").append(tableitem)
            })
        }});
    $(document).on("click",'.modify',function(){
        var index= $(this).index();
        $(".mod_popup").css("display","block")
    });
    $(".close").click(function () {
        $(".popup").css("display","none")
    })



})
