<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xzh
  Date: 2020/9/12
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>班期管理</title>
    <script src="../../static/layui/layui.js"></script>
    <link href="../../static/layui/css/layui.css"rel="stylesheet">
    <style>
        .layui-table-tool-self{
            display: none;
        }
        .layui-table-tool-temp{
            padding: 0px;
        }
        .laytable-cell-checkbox{
            padding: 5px;
            text-align: center;
        }
        .lui{
            background-color: #f8f8f8;'
        }
    </style>
</head>
<body>
<div align="center" style="margin: 0px 10px">
    <table id="demo" lay-filter="test"></table>
</div>
<script type="text/html" id="barDemo">
    <a class="layui-btn  layui-btn-xs" lay-event="edit">修改</a>&nbsp;
</script>
<script type="text/html" id="toolbarDemo">
    <div align="right">
        <div class="layui-input-inline">
            <select name="clazz" lay-verify="" id="clazz">
                <option value="">请选择班期</option>
                <c:forEach var="clazz" items="${clazz}">
                    <option value="${clazz}">${clazz}</option>
                </c:forEach>
            </select>
        </div>
        <div class="layui-input-inline">
            <input type="text" placeholder="请输入教师名" class="layui-input" id="teacherName">
        </div>
        <div class="layui-input-inline">
            <button class="layui-btn layui-btn-sm"  lay-event="query">查询</button>
            <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
        </div>
    </div>
</script>
<script>
    layui.use(['table','layer','jquery'], function(){
        var table = layui.table;
        var layer=layui.layer;
        var $=layui.jquery;
        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 'full-32'
            ,limit:8
            ,limits:[8,10,15,20]
            ,toolbar: '#toolbarDemo'//添加工具栏
            ,url: '/getAllClazz' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {type:'numbers',title:'序号', width:'15%', sort:true}
                ,{field: 'courseId', title: '课程号', hide:true}
                ,{field: 'clazz', title: '班期',align:"center"}
                ,{field: 'teacherName', title: '教师姓名',align:"center"}
                ,{title:'操作', toolbar:'#barDemo',align:'center', width:180, style:'background-color: #f8f8f8;'}
            ]]
        });
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'query':
                    var clazz = $("#clazz").val();
                    var teacherName = $("#teacherName").val();
                    //table重载
                    table.reload('demo',{
                        url: '/getClazz' //数据接口
                        ,where:{
                            clazz:clazz
                            ,teacherName:teacherName
                        }
                        ,page: {
                            curr: 1 //重新从第 1 页开始
                        }
                    });
                    $("#clazz").val(clazz);
                    $("#teacherName").val(teacherName);
                    break;
                case 'add':
                    layer.open({
                        type:2,
                        title:'新增班期',
                        content:'addClazz',
                        shadeClose:true,//点击遮罩关闭弹窗
                        area:['400px','430px'],
                    });
                    break;

            };
        });
        table.on('tool(test)', function(obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                //获取要编辑的编号
                var classId = data.classId;
                //根据编号获取信息
                layer.open({
                    type: 2,//弹出完整jsp，type=1弹出底层div
                    title: "编辑班期信息",
                    content: "editClazz?classId=" + classId,
                    shadeClose: true,//点击遮罩，关闭弹框
                    area:['400px','430px'],
                    end: function () {
                        //刷新当前页
                        $(".layui-laypage-btn").click();
                    }
                });
            }
        })
    });
</script>
</body>
</html>
