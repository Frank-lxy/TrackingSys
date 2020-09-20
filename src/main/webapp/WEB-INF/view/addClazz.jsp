<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xzh
  Date: 2020/9/14
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选课管理</title>
    <style>
        .layui-table-tool-self {
            display: none;
        }
    </style>
    <link rel="stylesheet" href="../../static/layui/css/layui.css">
    <script src="../../static/layui/layui.js"></script>
</head>
<body>
<form style="display: flex; justify-content: center">
    <div class="layui-form">
        <div class="layui-inline">
            <div class="layui-form-item">
                <label class="layui-form-label">班期</label>
                <div class="layui-input-block">
                    <input type="text" name="clazz" required lay-verify="required" value="${clazz}" autocomplete="off"
                           class="layui-input" id="clazz" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">授课教师</label>
                <div class="layui-input-block">
                    <select name="teacherName" lay-verify="required" id="teacherName" required>
                        <option value="">请选择授课教师</option>
                        <c:forEach var="teacherName" items="${teacherName}">
                            <option value="${teacherName}">${teacherName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">课程</label>
                <div class="layui-input-block">
                    <c:forEach var="courseName" items="${courseName}">
                        <input type="checkbox" class="courseName" title="${courseName}" id="courseName"
                               value="${courseName}"  lay-filter="hope" type="checkbox"
                               name="courseName" >
                    </c:forEach>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" id="addNewClazz" lay-submit lay-filter="formDemo">提交</button>
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
        form.on('checkbox(hope)',function(result){
            console.debug(result);
            var len=$(".courseName:checked").length;
            if(len>6){
                $(result.elem).next().attr("class","layui-unselect layui-form-checkbox");
                $(result.elem).prop("checked",false);
                layer.msg('最多只能选6项！',{icon:5});
                return false;
            }
        });
        form.on('submit(formDemo)', function(){
        // $("#addNewClazz").click(function(){
            //将页面全部复选框选中的值拼接到一个数组中
            $('input[type=checkbox]:checked').each(function() {
                arr.push($(this).val());
            });

            //数组
            if (arr == "") {
                layer.msg("请选择课程")
                return false;
            } else if ($("#teacherName").val() == "") {
                layer.msg("请选择教师")
                return false;
            } else {
                var courseName=arr.join(",");
                $.ajax({
                    url: "/addNewClazz",//要请求的后台资源
                    type: "post",//ajax请求类型
                    data: {
                        clazz: $("#clazz").val(),//多个值的话以逗号分隔开
                        teacherName: $("#teacherName").val(),
                        courseName: courseName,
                    },//向服务器发送的数据
                    dataType: "text",//服务器返回数据的类型
                    success: function (data) {
                        if (data) {
                            layer.msg('新增成功');
                            parent.location.href="clazzList";
                        }
                    },
                    error: function (data) {

                    }
                });
                window.parent.location.href="clazzList";
            }

        });
    });
    var closeAdd = function () {
        /*var index = parent.layer.getFrameIndex(window.name);//获取当前iframe层得索引
        parent.layer.close(index);*/
        parent.location.reload();
    }
</script>
</body>
</html>
