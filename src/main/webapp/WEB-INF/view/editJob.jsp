<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/12
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑职务</title>
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
            margin-left: 95px;
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
                <label class="layui-form-label">职务编号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="jobId" id="jobId" value="${job.jobId}" class="layui-input" readonly>
                </div>
                <label class="layui-form-label">职务名称：</label>
                <div class="layui-input-inline">
                    <input type="text" name="jobName" id="jobName" value="${job.jobName}" required  lay-verify="required" lay-reqtext="职务名称不能为空" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-input-inline" style="margin-left: 40%;margin-bottom: 0px">
                    <button id="addJob" class="layui-btn" lay-submit lay-filter="demo1">提交</button>
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

        //失去焦点时，判断职务名称是否已存在
        $("#jobName").blur(function () {
            $.ajax({
                url:"getJobByName",
                type:"post",
                data:{
                    jobName:$("#jobName").val()
                },
                dataType:"text",
                success:function (data) {
                    if (data != 'n' && data != $("#jobId").val()){
                        //layer.msg("该职务名称已存在");
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
        $("#addJob").click(function () {
            if ($("#jobName").val() != '' && isExit == false){
                $.ajax({
                    url:"editJob",
                    type:"post",
                    data:{
                        jobId:$("#jobId").val(),
                        jobName:$("#jobName").val()
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
                layer.msg("该职务名称已存在，不能重复");
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
