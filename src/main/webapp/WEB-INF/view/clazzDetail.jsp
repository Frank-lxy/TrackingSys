<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xzh
  Date: 2020/9/21
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看班期</title>
    <style>
        .layui-input-block {
            margin-left: 65px;
            min-height: 30px;
        }
        .layui-form-label {
            width: auto;
            min-width: 80px;
            padding: 9px 5px;
            margin-left: 10px;
        }
        .layui-form-item .layui-input-inline {
            margin-bottom: 20px;
        }
        .layui-form-select dl {
            max-height: 190px;
        }
        .layui-form-radio {
            line-height: 20px;
            margin: 3px 0 3px 0;
            padding-right: 0;
            padding-left: 20px;
        }
        .layui-btn {
            height: 28px;
            line-height: 28px;
        }
        img{
            height: 170px;
        }
        .showPhoto{
            border: solid 1px #eaeaea;
            margin: 15px 15px 10px 0;
            height: 170px;
            width: 110px;
        }
        .layui-laydate-main{
            height: 265px !important;
        }
    </style>
    <link rel="stylesheet" href="../../static/layui/css/layui.css">
    <script src="../../static/layui/layui.js"></script>
</head>
<body>
<form style="display: flex; justify-content: center">
    <div class="layui-form">
        <div class="layui-inline">
            <div class="layui-form-item" style="display: none">
                <label class="layui-form-label">班期编号</label>
                <div class="layui-input-inline">
                    <input type="text" name="classId" required lay-verify="required" value="${classId}" autocomplete="off"
                           class="layui-input" id="classId" >
                </div>
            </div>
            <div style="float: left">
                <label class="layui-form-label">班期</label>
                <div class="layui-input-inline">
                    <input type="text" name="clazz" required lay-verify="required" value="${clazz}" autocomplete="off"
                           class="layui-input" id="clazz" readonly>
                </div>
            </div>
            <div style="float: left">
                <label class="layui-form-label">授课教师</label>
                <div class="layui-input-inline">
                    <input type="text" name="teacher" required lay-verify="required" value="${teacher}" autocomplete="off"
                           class="layui-input" id="teacher" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">课程</label>
                <div class="layui-input-inline">
                    <c:set var="CN" value="${CN}"></c:set>
                    <c:set var="course" value="${course}"></c:set>
                    <c:forEach var="checkCourseName" items="${checkCourseName}">
                        <input type="checkbox" class="courseName" title="${checkCourseName}" id="courseName"
                               value="${checkCourseName}"  lay-filter="hope" type="checkbox"
                               name="courseName"checked  >
                    </c:forEach>
                </div>
            </div>

        </div>

    </div>
</form>
<script>
    layui.use(['form', 'jquery'], function () {
        var $ = layui.$;
        var form = layui.form;
        var layer = layui.layer;
        var arr = [];

    });
    var closeAdd = function () {
        /*var index = parent.layer.getFrameIndex(window.name);//获取当前iframe层得索引
        parent.layer.close(index);*/
        parent.location.reload();
    }
</script>
</body>
</html>
