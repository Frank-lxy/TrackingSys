<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/9/13
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>经理评价</title>
    <link href="../../static/layui/css/layui.css" rel="stylesheet">
    <script src="../../static/layui/layui.js"></script>
</head>
<body>
<div style="display:flex;justify-content: center;margin-top:6%">
    <div class="layui-form" >
        <div class="layui-form-item" style="display: none">
            <label class="layui-form-label">学生编号</label>
            <div class="layui-input-inline">
                <input id="studentId" type="text" name="studentId" required  lay-verify="required" autocomplete="off" class="layui-input" value="${requestScope.student.studentId}">
            </div>
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline">
                <input id="tState" type="text" name="tState" required  lay-verify="required" autocomplete="off" class="layui-input" value="${requestScope.student.tState}">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input id="studentName" type="text" name="studentName" readonly class="layui-input" value="${requestScope.student.studentName}">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">整体评分</label>
            <div class="layui-input-inline">
                <input id="evaluate" type="text" name="evaluate" autocomplete="off" class="layui-input">
                <div id="evaluateDiv" style="padding:2px;height: 5px"></div>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">评价</label>
            <div class="layui-input-block">
                <textarea id="assess" name="assess" placeholder="请输入内容" class="layui-textarea" rows="5" cols="40"></textarea>
                <div id="assessDiv" style="padding:2px;height: 5px"></div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 40%">
                <button id="addMassess" class="layui-btn" lay-submit lay-filter="formDemo">提交</button>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['laydate','form'], function(){
        var $ = layui.jquery;
        //表单验证
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
        //表单验证
        $("#assess").blur(function () {
            $(this).prop("class","layui-textarea ");//每次触发时先清空一下red类选择器
            $("#assessDiv").html("");//清空div
            var assess = $(this).val();
            if (assess==''){
                $(this).prop("class","layui-textarea red");
                $("#assessDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>整体评分不能为空</span>")
            }
        });
        $("#addMassess").click(function () {
            if ($("#evaluate").val()!='' && $("#assess").val()!='' && !isNaN($("#evaluate").val()) && $("#evaluate").val() > 0 && $("#evaluate").val() <= 5 ){
                $.ajax({
                    url:"addMassess",
                    type:"post",
                    data:{
                        studentId:$("#studentId").val(),
                        evaluate:$("#evaluate").val(),
                        assess:$("#assess").val(),
                        state:1,
                        tState:$("#tState").val()
                    },
                    dataType:"text",//服务器响应数据的类型
                    success:function (data) {
                        if(data){
                            layer.msg(data);
                            setTimeout("close()",1000)
                        }
                    },
                    error:function () {
                        layer.msg("执行失败")
                        setTimeout("close()",1000)
                    }
                })
            }else {
                var evaluate = $("#evaluate").val();
                if (evaluate==''){
                    $("#evaluate").prop("class","layui-input red");
                    $("#evaluateDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>评价不能为空</span>")
                }else if (isNaN(evaluate)){
                    $(this).prop("class","layui-input red");
                    $("#evaluateDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>请填写数字</span>")
                }

                var assess = $("#assess").val();
                if (assess==''){
                    $("#assess").prop("class","layui-textarea red");
                    $("#assessDiv").html("<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 18px; color: red;\"></i> <span style='color: red'>整体评分不能为空</span>")
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