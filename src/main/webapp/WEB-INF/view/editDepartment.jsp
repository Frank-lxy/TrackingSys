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
    <title>编辑部门</title>
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
                <label class="layui-form-label">部门编号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="departmentId" id="departmentId" value="${department.departmentId}" class="layui-input" readonly>
                </div>
                <label class="layui-form-label">部门名称：</label>
                <div class="layui-input-inline">
                    <input type="text" name="departmentName" id="departmentName" value="${department.departmentName}" required  lay-verify="required" lay-reqtext="课程名称不能为空" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-input-inline" style="margin-left: 40%;margin-bottom: 0px">
                    <button id="editDepartment" class="layui-btn" lay-submit lay-filter="demo1">提交</button>
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
        //定义一个判断名称是否已存在的变量
        var isExit = false;

        //失去焦点时，判断部门名称是否已存在
        $("#departmentName").blur(function () {
            $.ajax({
                url:"getDepartmentByName",
                type:"post",
                data:{
                    departmentName:$("#departmentName").val()
                },
                dataType:"text",
                success:function (data) {
                    if (data != 'n' && data != $("#departmentId").val()){
                        //layer.msg("该部门名称已存在");
                        isExit = true;
                    }else {
                        isExit = false;
                    }
                },
                error:function (data) {
                    layer.msg("执行失败");
                }

            });
        });

        //点击提交按钮提交编辑数据
        $("#editDepartment").click(function () {
            if ($("#departmentName").val() != '' && isExit == false){
                $.ajax({
                    url:"editDepartment",
                    type:"post",
                    data:{
                        departmentId:$("#departmentId").val(),
                        departmentName:$("#departmentName").val()
                    },
                    dataType:"text",
                    success:function (data) {
                        layer.msg(data);
                        setTimeout('closeEdit()',1000);
                    },
                    error:function (data) {
                        layer.msg("执行失败");
                        setTimeout('closeEdit()',1000);
                    }
                });
            }else if (isExit){//若已存在，给出提示
                layer.msg("该部门名称已存在，不能重复");
            }
        });
    });
    var closeEdit = function () {
        var index = parent.layer.getFrameIndex(window.name);//获取当前iframe层得索引
        parent.layer.close(index);
    }
</script>
</body>
</html>
