<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/16
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <link href="../../static/layui/css/layui.css" rel="stylesheet">
    <script src="../../static/layui/layui.js"></script>
    <style>
        .red{
            border: 1px red solid;
        }
    </style>
</head>
<body>
<blockquote class="layui-elem-quote">修改密码</blockquote>
<div style="margin: 50px 0 0 256px;" >
    <div style="width: 450px;background-color: white;padding: 20px 0 60px 80px;border-radius: 5px">
        <div class="layui-form-item" style="display: none">
            <label class="layui-form-label">用户编号</label>
            <div class="layui-input-inline">
                <input id="userId" type="text" name="userId" required  lay-verify="required" autocomplete="off" class="layui-input" value="${sessionScope.user.userId}">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input id="userName" type="text" name="userName" autocomplete="off" class="layui-input" value="${sessionScope.user.userName}" readonly>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red">*</span> 旧密码</label>
            <div class="layui-input-inline">
                <input id="oldPwd" type="password" name="pwd" autocomplete="off" class="layui-input">
                <div id="oldPwdDiv" style="padding:2px;height: 5px"></div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red">*</span> 密码</label>
            <div class="layui-input-inline">
                <input id="pwd" type="password" name="pwd" autocomplete="off" class="layui-input">
                <div id="pwdDiv" style="padding:2px;height: 5px"></div>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label"><span style="color: red">*</span> 确认密码</label>
            <div class="layui-input-inline">
                <input id="rePwd" type="password" name="rePwd" autocomplete="off" class="layui-input">
                <div id="rePwdDiv" style="padding:2px;height: 5px"></div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 180px">
                <button id="editPwd" class="layui-btn" lay-submit lay-filter="formDemo" >提交</button>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['laydate','form'], function(){
        var $ = layui.jquery
        ,oldPassword = ${sessionScope.user.password}
        //表单验证
        //验证输入的旧密码是否正确
        $("#oldPwd").blur(function () {
            $(this).prop("class","layui-input");//每次触发时先清空一下red类选择器
            $("#oldPwdDiv").html("");//清空div
            var oldPwd = $(this).val();
            if (oldPwd==''){
                $(this).prop("class","layui-input red");
                $("#oldPwdDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>请输入旧密码</span>")
            }else if (oldPwd != oldPassword){
                $(this).prop("class","layui-input red");
                $("#oldPwdDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>旧密码输入错误</span>")
            }
        })
        //验证输入的新密码是否符合规范
        $("#pwd").blur(function () {
            $(this).prop("class","layui-input");//每次触发时先清空一下red类选择器
            $("#pwdDiv").html("");//清空div

            var pwd = $(this).val();
            if (pwd=='' || pwd.length < 6){
                $(this).prop("class","layui-input red");
                $("#pwdDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>密码不能为空且最少为6位</span>")
            }else if(pwd == $("#rePwd").val()){
                $("#rePwd").prop("class","layui-input ");//每次触发时先清空一下red类选择器
                $("#rePwdDiv").html("");//清空div
            }
        });
        //验证确认密码是否符合规范，及是否和第一次输入的密码一致
        $("#rePwd").blur(function () {
            $(this).prop("class","layui-input ");//每次触发时先清空一下red类选择器
            $("#rePwdDiv").html("");//清空div
            var rePwd = $(this).val();
            if (rePwd=='' || rePwd.length < 6){
                $(this).prop("class","layui-input red");
                $("#rePwdDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>密码不能为空且最少为6位</span>")
            }else if (rePwd != $("#pwd").val()){
                $(this).prop("class","layui-input red");
                $("#rePwdDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>两次密码输入不一致</span>")
            }
        });
        //提交的时候，各个输入框的内容是否符合规范
        $("#editPwd").click(function () {
            if ($("#pwd").val()!='' && $("#rePwd").val()!=''&& $("#oldPwd").val() != '' && $("#pwd").val() == $("#rePwd").val() && oldPassword == $("#oldPwd").val()){
                $.ajax({
                    url:"editPwdById",
                    type:"post",
                    data:{
                        userId:$("#userId").val(),
                        password:$("#pwd").val()
                    },
                    dataType:"text",//服务器响应数据的类型
                    success:function (data) {
                        if(data){
                            layer.msg(data);
                            setTimeout("close()",2000)
                        }
                    },
                    error:function () {
                        layer.msg("执行失败")
                        setTimeout("close()",2000)
                    }
                })
            }else {
                // layer.msg("请正确填写信息")
                var pwd = $("#pwd").val();
                if (pwd=='' || pwd.length < 6){
                    $("#pwd").prop("class","layui-input red");
                    $("#pwdDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>密码不能为空且最少为6位</span>")
                }else if (pwd != $("#rePwd").val()){
                    $("#pwd").prop("class","layui-input red");
                    $("#pwdDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>两次密码输入不一致</span>")
                }

                var rePwd = $("#rePwd").val();
                if (rePwd=='' || rePwd.length < 6){
                    $("#rePwd").prop("class","layui-input red");
                    $("#rePwdDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>密码不能为空且最少为6位</span>")
                }else if (rePwd != $("#pwd").val()){
                    $("#rePwd").prop("class","layui-input red");
                    $("#rePwdDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>两次密码输入不一致</span>")
                }

                var oldPwd = $("#oldPwd").val();
                if (oldPwd==''){
                    $("#oldPwd").prop("class","layui-input red");
                    $("#oldPwdDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>请输入旧密码</span>")
                }else if (oldPwd != oldPassword){
                    $("#oldPwd").prop("class","layui-input red");
                    $("#oldPwdDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>旧密码输入错误</span>")
                }
            }

        })
    });
    //关闭当前层
    var close = function () {
        parent.location.reload();
    }
</script>
</body>
</html>
