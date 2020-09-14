<%@ page import="com.jxd.model.Student" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/13
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学员详细信息</title>
    <link rel="stylesheet" href="../../static/layui/css/layui.css">
    <script src="../../static/layui/layui.js"></script>
    <style>
        td{
            height: 50px;
        }
        .layui-table img {
            max-width: 170px;
        }
        img{
            width: 160px;
        }
    </style>
</head>
<body>
    <div class="layui-form" style="padding: 10px 25px">
        <table class="layui-table" lay-even="">
            <colgroup>
                <col width="100">
                <col width="130">
                <col width="80">
                <col width="150">
                <col width="110">
                <col width="130">
                <col>
            </colgroup>
            <tbody>
            <tr>
                <td>姓名</td>
                <td>${student.studentName}</td>
                <td>性别</td>
                <td>${student.sex}</td>
                <td>民族</td>
                <td>${student.nation}</td>
                <td rowspan="5">
                    <%
                        Student student = (Student) request.getAttribute("student");
                        if (student.getPhoto() != " " && student.getPhoto() != null){
                    %>
                    <img src="${student.photo}">
                    <%
                        }else {
                    %>
                    学员照片
                    <%
                        }
                    %>
                </td>
            </tr>
            <tr>
                <td>出生年月</td>
                <td>${student.birthday}</td>
                <td>籍贯</td>
                <td>${student.homeTown}</td>
                <td>婚否</td>
                <td>${student.marriage}</td>
            </tr>
            <tr>
                <td>联系电话</td>
                <td colspan="2">${student.phone}</td>
                <td>身份证号码</td>
                <td colspan="2">${student.identityNum}</td>
            </tr>
            <tr>
                <td>毕业学校</td>
                <td colspan="2">${student.graduate}</td>
                <td>专业</td>
                <td colspan="2">${student.major}</td>
            </tr>
            <tr>
                <td>入职日期</td>
                <td>${student.hiredate}</td>
                <td>班期</td>
                <td>${clazz.clazz}</td>
                <td>部门名称</td>
                <td>${department.departmentName}</td>
            </tr>
            <tr>
                <td>备注</td>
                <td colspan="6">${student.remarks}</td>
            </tr>
            </tbody>
        </table>
    </div>

    <script>
        layui.use(['table','layer','laypage'], function(){
            var table = layui.table
                ,layer = layui.layer
                ,laypage = layui.laypage;
            var $ = layui.jquery;
        });
    </script>
</body>
</html>
