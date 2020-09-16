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
    <link href="/static/layui/css/layui.css" rel="stylesheet">
    <script src="/static/layui/layui.js"></script>
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
            <label class="layui-form-label">整体评分</label>
            <div class="layui-input-inline">
                <input id="evaluate" type="text" name="evaluate" required  lay-verify="required" autocomplete="off" class="layui-input" value="${requestScope.sassess.evaluate}">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">评价</label>
            <div class="layui-input-block">
                <textarea id="assess" name="assess" placeholder="请输入内容" class="layui-textarea" style="width: 360px">${requestScope.sassess.assess}</textarea>
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
        $("#editSassess").click(function () {
            if ($("#evaluate").val()!='' && $("#assess").val()!='' ){
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