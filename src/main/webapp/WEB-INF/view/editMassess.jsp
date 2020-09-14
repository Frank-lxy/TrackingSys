<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/9/13
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改评价</title>
    <link href="../../static/layui/css/layui.css" rel="stylesheet">
    <script src="../../static/layui/layui.js"></script>
</head>
<body>
<div style="display:flex;justify-content: center;margin-top:6%">
    <div class="layui-form" >
        <div class="layui-form-item" style="display: none">
            <label class="layui-form-label">评价编号</label>
            <div class="layui-input-inline">
                <input id="maId" type="text" name="maId" required class="layui-input" value="${requestScope.massess.maId}">
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
                <input id="evaluate" type="text" name="evaluate" required  lay-verify="required" autocomplete="off" class="layui-input"value="${requestScope.massess.evaluate}">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">评价</label>
            <div class="layui-input-block">
                <textarea id="assess" name="assess" placeholder="请输入内容" class="layui-textarea" rows="5" cols="40">${requestScope.massess.assess}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 40%">
                <button id="editMassess" class="layui-btn" lay-submit lay-filter="formDemo">提交</button>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['laydate','form'], function(){
        var $ = layui.jquery;
        $("#editMassess").click(function () {
            $.ajax({
                url:"editMassess",
                type:"post",
                data:{
                    evaluate:$("#evaluate").val(),
                    assess:$("#assess").val(),
                    maId:$("#maId").val()
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