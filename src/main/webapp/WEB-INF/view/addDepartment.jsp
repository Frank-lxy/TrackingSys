<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/12
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增部门</title>
    <link rel="stylesheet" href="../../static/layui/css/layui.css">
    <script src="../../static/layui/layui.js"></script>
    <style>
        .layui-form-label {
            width: auto;
            padding: 9px 10px;
            margin-left: 5px;
        }
        .layui-form-item .layui-input-inline {
            margin-bottom: 30px;
            margin-left: 105px;
        }
        .layui-form-label {
            margin-left: 0px;
        }
    </style>
</head>
<body>
<div style="margin: 30px 0px 0px 0px">
    <div style="display: flex;justify-content: center">
        <div class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">部门名称：</label>
                <div class="layui-input-inline">
                    <input type="text" name="departmentName" id="departmentName" required  lay-verify="required" lay-reqtext="部门名称不能为空" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-input-inline" style="margin-left: 40%;margin-bottom: 0px">
                    <button id="addDepartment" class="layui-btn" lay-submit lay-filter="demo1">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['layer','form','upload'],function () {
        var layer = layui.layer
            ,form = layui.form
            ,upload = layui.upload;
        $ = layui.jquery;
        $("#departmentName").blur(function () {
            $.ajax({
                url:"getDepartmentByName",
                type:"post",
                data:{
                    departmentName:$("#departmentName").val()
                },
                dataType:"text",
                success:function (data) {
                    if (data == 'y'){
                        layer.msg("该部门名称已存在");
                    }
                },
                error:function (data) {
                    layer.msg("执行失败");
                }

            });
        });
        $("#addDepartment").click(function () {
            if ($("#departmentName").val() != ''){
                $.ajax({
                    url:"addDepartment",
                    type:"post",
                    data:{
                        departmentName:$("#departmentName").val()
                    },
                    dataType:"text",
                    success:function (data) {
                        layer.msg(data);
                        setTimeout('closeAdd()',1000);
                    },
                    error:function (data) {
                        layer.msg("执行失败");
                        setTimeout('closeAdd()',1000);
                    }
                });
            }
        });
    });
    var closeAdd = function () {
        parent.location.reload();//刷新父页面
    }
</script>
</body>
</html>
