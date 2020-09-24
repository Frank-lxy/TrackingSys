<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/12
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增评价项</title>
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
                <label class="layui-form-label">评价项名称：</label>
                <div class="layui-input-inline">
                    <input type="text" name="assessItemName" id="assessItemName" required  lay-verify="required" lay-reqtext="评价项名称不能为空" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-input-inline" style="margin-left: 40%;margin-bottom: 0px">
                    <button id="addAssessItem" class="layui-btn" lay-submit lay-filter="demo1">提交</button>
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
        //定义一个是否存在的变量
        var isExit = false;

        //评分项文本框失去焦点时验证名称是否已存在
        $("#assessItemName").blur(function () {
            $.ajax({
                url:"getAssessItemByName",
                type:"post",
                data:{
                    assessItemName:$("#assessItemName").val()
                },
                dataType:"text",
                success:function (data) {
                    //若已存在，给出提示
                    if (data == 'n'){
                        isExit = false;
                    }else {
                        layer.msg("该评价项名称已存在");
                        isExit = true;
                    }
                },
                error:function (data) {
                    layer.msg("执行失败");
                }

            });
        });

        //点击提交按钮，新增评分项
        $("#addAssessItem").click(function () {
            //若评分项名称不存在且输入不为空，新增评分项
            if ($("#assessItemName").val() != '' && isExit == false){
                $.ajax({
                    url:"addAssessItem",
                    type:"post",
                    data:{
                        assessItemName:$("#assessItemName").val()
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
