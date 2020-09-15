<%@ page import="com.jxd.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/25
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>金桥学员跟踪系统</title>
    <link rel="stylesheet" href="../../static/layui/css/layui.css">
    <script src="../../static/layui/layui.js"></script>
    <style>
        iframe{
            width: 100%;
            height: 90%;
            border: medium none;
        }
        layui-body {
            position: fixed;
            top: 60px;
            bottom: 6px;
        }
        .layui-layout-admin .layui-logo {
            width: 230px;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <%--logo显示--%>
        <div class="layui-logo" style="font-family: 方正粗黑宋简体">
            <%
                User user = (User)session.getAttribute("user");
                if (user.getRole() == 1){
            %>
            金桥学员跟踪系统(管理员)
            <%
                }
            %>
            <%
                if (user.getRole() == 2){
            %>
            金桥学员跟踪系统(教师)
            <%
                }
            %>
            <%
                if (user.getRole() == 3){
            %>
            金桥学员跟踪系统(项目经理)
            <%
                }
            %>
        </div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <i class="layui-icon layui-icon-username" style="font-size: 20px; color: #eff2fb;"></i>
                    ${sessionScope.user.userName}
                </a>
            </li>
            <li class="layui-nav-item"><a href="logout">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <%--管理员菜单--%>
        <%
            if (user.getRole() == 1){
        %>
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;" target="mainFrame">学员管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="" target="mainFrame">学员跟踪表</a></dd>
                        <dd><a href="getStudentList" target="mainFrame">基本信息</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="studentList" target="mainFrame">用户管理</a>
                </li>
                <li class="layui-nav-item">
                    <a href="getCourseList" target="mainFrame">课程管理</a>
                </li>
                <li class="layui-nav-item">
                    <a href="getAssessItemList" target="mainFrame">评价项管理</a>
                </li>
                <li class="layui-nav-item">
                    <a href="userList.jsp" target="mainFrame">班期管理</a>
                </li>
                <li class="layui-nav-item">
                    <a href="getDepartmentList" target="mainFrame">部门管理</a>
                </li>
                <li class="layui-nav-item">
                    <a href="getJobList" target="mainFrame">职务管理</a>
                </li>
            </ul>
        </div>
        <%
            }
        %>
            <%--教师菜单--%>
        <%
            if (user.getRole() == 2 ){
        %>
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="newsList.jsp" target="mainFrame">学员跟踪表</a>
                </li>
                <li class="layui-nav-item">
                    <a href="studentList" target="mainFrame">学员信息</a>
                </li>
                <li class="layui-nav-item">
                    <a href="userList.jsp" target="mainFrame">学员成绩</a>
                </li>
                <li class="layui-nav-item">
                    <a href="userList.jsp" target="mainFrame">学员评价</a>
                </li>
                <li class="layui-nav-item">
                    <a href="userList.jsp" target="mainFrame">修改密码</a>
                </li>
            </ul>
        </div>
        <%
            }
        %>
            <%--项目经理菜单--%>
        <%
            if (user.getRole() == 3 ){
        %>
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="newsList.jsp" target="mainFrame">学员跟踪表</a>
                </li>
                <li class="layui-nav-item">
                    <a href="studentList" target="mainFrame">学员信息</a>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;" target="mainFrame">学员评分</a>
                    <dl class="layui-nav-child">
                        <dd><a href="massessList" target="mainFrame">转正评分</a></dd>
                        <dd><a href="massessList1" target="mainFrame">一年评分</a></dd>
                        <dd><a href="massessList2" target="mainFrame">两年评分</a></dd>
                        <dd><a href="massessList3" target="mainFrame">三年评分</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;" target="mainFrame">学员评价</a>
                    <dl class="layui-nav-child">
                        <dd><a href="massessList" target="mainFrame">转正评价</a></dd>
                        <dd><a href="massessList1" target="mainFrame">一年评价</a></dd>
                        <dd><a href="massessList2" target="mainFrame">两年评价</a></dd>
                        <dd><a href="massessList3" target="mainFrame">三年评价</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="userList.jsp" target="mainFrame">修改密码</a>
                </li>
            </ul>
        </div>
        <%
            }
        %>

    </div>

    <div class="layui-body" style="height: 99%">
        <!-- 内容主体区域 -->
        <div style="padding: 0px">
            <%
                if (user.getRole() == 1){
            %>
            <iframe name="mainFrame" src="getStudentList"></iframe>
            <%
                }
            %>
            <%
                if (user.getRole() == 2){
            %>
            <iframe name="mainFrame" src="getScoreList"></iframe>
            <%
                }
            %>
            <%
                if (user.getRole() == 3){
            %>
            <iframe name="mainFrame" src="getStudentList"></iframe>
            <%
                }
            %>
        </div>
    </div>
</div>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
    });
</script>
</body>
</html>
