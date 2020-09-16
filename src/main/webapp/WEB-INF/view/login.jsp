<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/10
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="../../static/layui/css/layui.css">
    <script src="../../static/layui/layui.js"></script>
    <style>
        body {
            line-height: 24px;
            font: 14px Helvetica Neue,Helvetica,PingFang SC,Tahoma,Arial,sans-serif;
            font-style: normal;
            font-variant-ligatures: normal;
            font-variant-caps: normal;
            font-variant-numeric: normal;
            font-variant-east-asian: normal;
            font-weight: normal;
            font-stretch: normal;
            font-size: 14px;
            line-height: 24px;
            font-family: "Helvetica Neue", Helvetica, "PingFang SC", Tahoma, Arial, sans-serif;
        }
        a{
            line-height: 80px;
            text-decoration: none;
            font-size: 14px;
            color: black;
        }
        .top-div{
            /* style="height: 70px;background: #eff5f9;border-bottom: #e2e2e2 1px solid" */
            height: 70px;
            background-color: #eff5f9;
            border-bottom: #e2e2e2 1px solid;
        }
        .layui-layout-right{
            width: 170px;
            height: 70px;
        }
        .layui-input-block {
            margin-left: 5px;
        }
        img{
            margin: 25% 0 0 25%;
            width: 100%;
        }
        .card-style{
            width: 350px;
            height: 350px;
            margin-top: 10%;
            margin-left: 25%;
            border-radius: 10px;
            background-color: #f1f7fb;
        }
        .layui-this{
            background-color: #f1f7fb;
        }
        .layui-input-inline {
            margin-left: 15px;
            margin-right: 15px;
        }
        .layui-layout-admin .layui-footer {
            left: 0px;
            background-color: #f3f9fd;
        }
        .layui-tab-card>.layui-tab-title {
            background-color: #eff2fb;
        }
        .layui-btn {
            background-color: #3c99be;
        }
        .layui-form-checked[lay-skin=primary] i {
            border-color: #66b5d6 !important;
            background-color: #66b5d6;
            color: #fff;
        }
        .layui-badge-rim, .layui-colla-content, .layui-colla-item, .layui-collapse, .layui-elem-field, .layui-form-pane .layui-form-item[pane], .layui-form-pane .layui-form-label, .layui-input, .layui-layedit, .layui-layedit-tool, .layui-quote-nm, .layui-select, .layui-tab-bar, .layui-tab-card, .layui-tab-title, .layui-tab-title .layui-this:after, .layui-textarea {
            border-color: #d7d7d7;
        }
        .layui-input, .layui-select, .layui-textarea {
            height: 38px;
            line-height: 1.3;
            line-height: 38px\9;
            border-width: 0px;
            border-style: none;
            border-radius: 2px;
            border-bottom: 1px solid #c0c0c0;
        }
        .logo {
            position: absolute;
            left: 5px;
            top: 3px;
            width: 400px;
            color: #3c99be;
            font-size: 30px;
            font-family: 'Trebuchet MS', Helvetica, sans-serif;
            font-style: italic;
            line-height: 60px;
            margin-left: 5%;
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="top-div">
        <div class="logo">
            <span>金桥学员跟踪系统</span>
        </div>
    </div>
    <div class="layui-row" align="center">
        <div class="layui-col-xs5">
            <img src="../../static/img/bg3.jpg">
        </div>
        <div class="layui-col-xs6">
            <div class="layui-tab-card card-style">
                <div align="center" style="font-size: large;height: 50px;border-bottom: #d7d7d7 1px solid">
                    <div style="line-height: 50px;color: #318cb0">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</div>
                </div>
                <div id="div1" style="background-color: white;height: 297px">
                    <div style="display: flex;justify-content: center">
                        <form class="layui-form" action="login" method="post">
                            <div class="layui-form-item" style="margin-top: 35px">
                                <div style="float:left;">
                                    <i class="layui-icon layui-icon-username" style="font-size: 25px; color: #a3a6b0;line-height: 35px"></i>
                                </div>
                                <div class="layui-input-inline">
                                    <input type="text" name="userName" value="${userName}" required lay-verify="required" lay-reqtext="用户名不能为空" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item" style="margin-top: 40px">
                                <div style="float:left;">
                                    <i class="layui-icon layui-icon-password" style="font-size: 25px; color: #a3a6b0;line-height: 35px"></i>
                                </div>
                                <div class="layui-input-inline">
                                    <input type="password" name="password" value="${password}" required lay-verify="required" lay-reqtext="密码不能为空" placeholder="请输入密码" autocomplete="off" class="layui-input">
                            </div>
                            </div>
                            <div class="layui-form-item" style="margin-left: 20px">
                                <div class="layui-input-block" style="margin-top: 20px;height: 10px">
                                    <input type="checkbox" name="remember" value="y" lay-skin="primary" title="记住密码">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-input-block" style="margin-top: 20px">
                                    <button class="layui-btn" lay-submit lay-filter="formDemo">登录</button>
                                    <button type="reset" class="layui-btn" style="margin-left: 30px">重置</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-footer" align="center">
        Copyright © 1996-2020 SINA Corporation, All Rights Reserved
    </div>
</div>
</body>
<script>
    layui.use(['element','layer','form'], function(){
        var layer = layui.layer
            ,form = layui.form;
        var $ = layer.jquery;
        <c:if test="${not empty loginMsg}">
            <c:if test="${!loginMsg}">
                layer.msg('${loginMsg}');
            </c:if>
        </c:if>
    });
</script>
</html>
