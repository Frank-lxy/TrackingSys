<%--
  Created by IntelliJ IDEA.
  User: xzh
  Date: 2020/9/13
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增用户</title>
    <link rel="stylesheet" href="../../static/layui/css/layui.css">
    <script src="../../static/layui/layui.js"></script>
</head>

<body>
<div style="display: flex; justify-content: center">
    <div class="layui-form">
        <div class="layui-inline">
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="userName" required lay-verify="required" placeholder="请输入姓名"
                           autocomplete="off" class="layui-input" id="userName" auto-complete="new-password"
                    >
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                           autocomplete="off" class="layui-input" id="password" auto-complete="new-password">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>
                <div class="layui-input-block">
                    <input type="password" name="repwd" required lay-verify="required" placeholder="请再次输入密码"
                           autocomplete="off" class="layui-input" id="repwd" auto-complete="new-password">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">权限</label>
                <div class="layui-input-block">
                    <select name="city" lay-verify="required" id="Character" required>
                        <option value="" id="chater">请选择权限</option>
                        <option value="教师">教师</option>
                        <option value="经理">经理</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" id="addUser" lay-submit lay-filter="formDemo">添加</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['form', 'jquery'], function () {
        var $ = layui.$;
        var form = layui.form;
        var layer = layui.layer;
        var userName = "";
        var pwd = "";
        var repwd = "";
        var Character = ""
        $("#userName").blur(function () {
            userName = $("#userName").val();
            if (userName == "") {
                layer.msg("用户名不能为空");
            }
        })
        $("#repwd").blur(function () {
            repwd = $("#repwd").val();
            pwd = $("#password").val();
            if (repwd!=pwd) {
                layer.msg("两次密码输入不一致，请重新输入");
            }
        })
        $("#password").blur(function () {
            pwd = $("#password").val();
            var reg = /^[a-zA-Z0-9]{4,10}$/;
            if (pwd == "") {
                layer.msg("密码不能为空");
            } else {
                if (!reg.test(pwd)) {
                    layer.msg('密码必须大于4小于10', {icon: 5});
                }
            }
        });
        $("#addUser").click(function () {
            if (repwd != pwd ||$("#Character").val()=="" ) {
                layer.msg("输入有误，请检查")
            } else {
                $.ajax({
                    url: "/addNewUser",//要请求的后台资源
                    type: "post",//ajax请求类型
                    data: {
                        userName: $("#userName").val(),//多个值的话以逗号分隔开
                        password: $("#password").val(),
                        Character: $("#Character").val(),
                    },//向服务器发送的数据
                    dataType: "text",//服务器返回数据的类型
                    success: function (data) {
                        if (data) {
                            layer.msg('新增成功');
                            setTimeout('closeAdd()', 1000)
                        }
                    },
                    error: function (data) {

                    }
                });

            }
        });

    });
    var closeAdd = function () {
        window.parent.location.href = "userList"
    }

</script>
</body>
</html>