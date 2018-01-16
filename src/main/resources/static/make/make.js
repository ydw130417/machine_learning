/**
 * Created by ydw on 2018/1/12.
 */
var timuId;
layui.use(['form','upload'], function () {

    var form = layui.form;
    var upload=layui.upload;

    //监听提交
    form.on('submit(go)', function (data) {
        var id=data.field.id;
        console.log(id);
        $.ajax({
            method:"post",
            type: "post",
            url: "make/findId",
            data: {"id": id},
            success: function (data) {
                console.log(data);
                var  str=data;
                timuId = str.toString().split("/")[5].split(".")[0];
                console.log(timuId);
                timuId=timuId;
                $("#timuId").val(timuId);
                $("#timu").attr('src',data);
            }
        });
        return false;
    });

});