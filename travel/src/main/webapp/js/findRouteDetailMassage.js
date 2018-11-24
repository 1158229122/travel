function findRoute( address) {
    $.get(address,{rid:rid},function (data) {
        /*<div class="pros_other" id = "seller">
                <p>经营商家  ：黑马国旅</p>
            <p>咨询电话 : 400-618-9090</p>
            <p>地址 ： 传智播客黑马程序员</p>
            </div>*/
        //设置商家信息
        var seller = data.seller;
        $("#seller").html(
            "<p>经营商家  ："+seller.sname+"</p>\n" +
            "<p>咨询电话 : "+seller.consphone+"</p>\n" +
            "<p>地址 ： "+seller.address+"</p> "
        )
        //设置标题和价格
        $("#title").html(""+data.rname+"");
        $("#price").html("￥"+data.price+"");
        $("#routeIntroduce").html(data.routeIntroduce);
        $("#rName").html(data.rname);
        $("#count").html("已收藏"+data.count+"次");
        //设置图片
        var photo = data.routeImgList;
        var append = '<a class="up_img up_img_disable"></a>';

        for(var i= 0 ;i<photo.length;i++){
            if(i >= 4){
                append += '<a title="" class="little_img" data-bigpic="'+photo.bigPic+'" style="display:none;">\n' +
                    '                        <img src="'+photo.smallPic+'">\n' +
                    '                    </a>';
            }else{
                append += '<a title="" class="little_img" data-bigpic="'+photo[i].bigPic+'">\n' +
                    '                        <img src="'+photo[i].smallPic+'">\n' +
                    '                    </a>';
            }

        }
        append+=' <a class="down_img down_img_disable" style="margin-bottom: 0;"></a>';
        $("#photo").html(append);
        goImg();
    },"json");
}