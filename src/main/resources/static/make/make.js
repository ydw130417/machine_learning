/**
 * Created by ydw on 2018/1/12.
 */
var timuId;
layui.use(['form','upload'], function () {

    var form = layui.form;
    var upload=layui.upload;

    //监听提交
    form.on('submit(go)', function (data) {
        var words=data.field.words;
        console.log(words);
        $.ajax({
            method:"post",
            type: "post",
            url: "make/findTimu",
            data: {"words": words},
            success: function (data) {
                console.log(data);
                var  str=data;
                var timuId = str.toString().split("/")[5].split(".")[0];
                console.log(timuId);
                timuId=timuId;
                $("#timu").attr('src',data);
            }
        })
        return false;
    });


});