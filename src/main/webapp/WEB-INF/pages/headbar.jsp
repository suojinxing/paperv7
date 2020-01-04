<%@page pageEncoding="UTF-8" %>
<style>
    .nav-right {
        width: 83%;
        height: 56px;
        float: right;
        /*
        overflow: hidden;
        */
    }

    .nav-right #self-info {
        height: 100%;
        line-height: 56px;
        cursor: pointer;
        border-radius: 10px;
    }

    .nav-right #self-info:hover {
        background: rgb(244, 246, 249);
    }

    .nav-right #self-info #head-img {
        width: 44px;
        width: 44px;
        border-radius: 22px;
    }

    .nav-right #messages {
        height: 100%;
        line-height: 56px;
        cursor: pointer;
        border-radius: 10px;
    }

    .nav-right #messages:hover {
        background: rgb(244, 246, 249);
    }

    .nav-right #msg {
        width: 35px;
        height: 35px;
    }

    /*上传头像*/
    #modify-head-img {
        width: 500px;
        height: 309px;
        background: rgba(240, 240, 240, 0.8);
        position: absolute;
        top: 50px;
        left: -250px;
        z-index: 4;
        box-shadow: -3px 3px 5px grey;
        border-radius: 5px;
    }

    #modify-head-img .btn {
        display: inline-block;
        width: 120px;
        height: 40px;
        text-align: center;
        margin-left: 25px;
    }

    #modify-head-img #file-input {
        display: inline-block;
        height: 40px;
        text-align: center;
        line-height: 40px;
        /*margin-left: 25px;*/
    }
</style>
<%--引入vue框架--%>
<script src="js/vue.js"></script>
<div id="app" class="nav-right row">
    <div class="col-8"></div>
    <div @mouseover="show" id='self-info' class="col-2">
        <img id='head-img' :src="imgSrc"/>
        <div id='modify-head-img'>
<%--            <form action="/file/pic/uploadHeadImg" method="post" enctype="multipart/form-data">--%>
                <input id="userId" type="hidden" value="${username }"/>
                <img :src="newImgSrc" width="200px" height="170px" alt="">
                <input class="btn-info" id="file-input" name="fileImage" type="file"/>
                <br/>
                <input @click="uploadImg" class="btn btn-info" value="提交"/>
                <button class="btn btn-warning" @click="hide">取消</button>
<%--            </form>--%>
        </div>
        <span>${username }</span>
    </div>
    <div id='messages' class="col-1">
        消息 <img id='msg' src='/img/messages.png'>
    </div>
</div>
<script>
    let app = new Vue({
        el: '#app',
        data: {
            imgSrc: '/img/headimg.png',
            newImgSrc: '/img/headimg.png'
        },
        methods: {
            show() {
                $("#modify-head-img").show();
            },
            hide() {
                $("#modify-head-img").hide();
            },
            uploadImg(){
                // # 创建formdata对象
                let formData = new FormData();
                formData.append('fileImage', $('#file-input')[0].files[0]);
                // # 给formData对象添加<input>标签,注意与input标签的ID一致

                let userId = $("#userId").val();

                let data = {
                    "fileImage":formData,
                    "userId":userId
                }
                console.log(data)
                $.ajax({
                    url : '/file/pic/uploadHeadImg',//这里写你的url
                    type : 'POST',
                    data : formData,
                    contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置
                    processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post)
                    dataType: 'json',//这里是返回类型，一般是json,text等
                    clearForm: true,//提交后是否清空表单数据
                    success: function(result) {   //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
                        alert(result.message);
                        $("#modify-head-img").hide();
                    },
                    error: function(data, status, e) {  //提交失败自动执行的处理函数。
                        console.error(e);
                    }
                })
            }
        }
    })
    $(function () {
        $("#modify-head-img").hide();
        //$("#head-img").attr("src","http://image.score/xusong.jpg");
    });
</script>