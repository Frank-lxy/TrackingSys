<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/12
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改评价</title>
    <link href="../../static/layui/css/layui.css" rel="stylesheet">
    <script src="../../static/layui/layui.js"></script>
    <style>
        .red{
            border: 1px red solid;
        }
    </style>
</head>
<body>
<div style="display:flex;justify-content: center;margin-top: 60px">
    <div class="layui-form" >
        <div class="layui-form-item" style="display: none">
            <label class="layui-form-label">评价编号</label>
            <div class="layui-input-inline">
                <input id="saId" type="text" name="saId" required  lay-verify="required" autocomplete="off" class="layui-input" value="${requestScope.sassess.saId}">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">学生姓名</label>
            <div class="layui-input-inline">
                <input id="studentName" type="text" name="studentName" required  lay-verify="required" autocomplete="off" class="layui-input" value="${requestScope.studentName}">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red">*</span> 整体评分</label>
            <div class="layui-input-inline">
                <input id="evaluate" type="text" name="evaluate" autocomplete="off" class="layui-input" value="${requestScope.sassess.evaluate}">
                <div id="evaluateDiv" style="padding:2px;height: 5px"></div>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label"><span style="color: red">*</span> 评价</label>
            <div class="layui-input-block">
                <textarea id="assess" name="assess" class="layui-textarea" style="width: 360px">${requestScope.sassess.assess}</textarea>
                <div id="assessDiv" style="padding:2px;height: 5px"></div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 180px">
                <button id="editSassess" class="layui-btn" lay-submit lay-filter="formDemo" >提交</button>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['laydate','form'], function(){
        var $ = layui.jquery;
        $("#evaluate").blur(function () {
            $(this).prop("class","layui-input");//每次触发时先清空一下red类选择器
            $("#evaluateDiv").html("");//清空div
            var evaluate = $(this).val();
            if (evaluate==''){
                $(this).prop("class","layui-input red");
                $("#evaluateDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>整体评分不能为空</span>")
            }else if (isNaN(evaluate) || evaluate < 0 || evaluate >5){
                $(this).prop("class","layui-input red");
                $("#evaluateDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>请填写数字(0-5)</span>")
            }
        });
        $("#assess").blur(function () {
            $(this).prop("class","layui-textarea ");//每次触发时先清空一下red类选择器
            $("#assessDiv").html("");//清空div
            var assess = $(this).val();
            if (assess==''){
                $(this).prop("class","layui-textarea red");
                $("#assessDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>评价不能为空</span>")
            }
        });
        $("#editSassess").click(function () {
            if ($("#evaluate").val()!='' && $("#assess").val()!='' && !isNaN($("#evaluate").val()) && $("#evaluate").val() > 0 && $("#evaluate").val() <= 5 ){
                $.ajax({
                    url:"editSassess",
                    type:"post",
                    data:{
                        saId:$("#saId").val(),
                        evaluate:$("#evaluate").val(),
                        assess:$("#assess").val()
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
                var evaluate = $("#evaluate").val();
                if (evaluate==''){
                    $("#evaluate").prop("class","layui-input red");
                    $("#evaluateDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>整体评分不能为空</span>")
                }else if (isNaN(evaluate)|| evaluate < 0 || evaluate >5){
                    $("#evaluate").prop("class","layui-input red");
                    $("#evaluateDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>请填写数字(0-5)</span>")
                }

                var assess = $("#assess").val();
                if (assess==''){
                    $("#assess").prop("class","layui-textarea red");
                    $("#assessDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>评价不能为空</span>")
                }

            }
        })
    });
    //关闭当前层
    var close = function () {
        var index = parent.layer.getFrameIndex(window.name);//获取当前iframe层得索引
        parent.layer.close(index);
    }
</script>
</body>
</html>