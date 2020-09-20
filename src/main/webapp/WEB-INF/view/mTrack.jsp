<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/9/17
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学员跟踪表</title>
    <link href="../../static/layui/css/layui.css" rel="stylesheet">
    <script src="../../static/layui/layui.js"></script>
    <style>
        .layui-table-tool-self{
            display: none;
        }
        .layui-table-tool-temp{
            padding: 0 ;
        }
    </style>
</head>
<body>
<div align="center">
    <table class="layui-hide" id="demo" lay-filter="test"></table>
</div>
<script type="text/html" id="toolbarDemo">
    <div style="float: left">
        <h2>金桥学员跟踪表</h2>
    </div>
    <div align="right">
        <div class="layui-input-inline">
            <input type="text" name="" required  placeholder="请输入学生姓名" id ="filter" class="layui-input" >
        </div>
        <div class="layui-input-inline">
            <button class="layui-btn layui-btn-sm" lay-event="query">查询</button>
        </div>
    </div>
</script>

<script>
    layui.use(['table','layer', 'laydate'], function(){
        var table = layui.table,
            layer = layui.layer,
            laydate = layui.laydate;
        var $ = layui.jquery;
        var managerId = ${requestScope.managerId};
        table.render({
            elem: '#demo'
            ,toolbar: '#toolbarDemo'
            , url: '/getMassessList?managerId='+ managerId //数据接口
            ,page: true //开启分页
            ,height: 'full-102'
            ,limit:8
            ,limits:[8,10,15,20]
            , cols: [[ //表头
                {type:'numbers',title:'序号', width:'5%', sort: true}
                , {field: 'maId', title: '评价编号',hide:true}
                , {field: 'studentId', title: '学生编号',align: 'center',hide:true}
                , {field: 'tState', title: '状态',align: 'center',hide:true}
                , {field: 'studentName', title: '学生姓名',align: 'center'}
                , {field: 'sex', title: '性别',align: 'center'}
                , {field: 'graduate', title: '学校',align: 'center'}
                , {field: 'nation', title: '籍贯',align: 'center'}
                ,{field: 'sassess', title: '学校评价',align:"center",templet: function (data) {
                        if (data.sassess == null){
                            return '<div style="color: red">未评分</div>'
                        }else{
                            return data.sassess
                        }
                    }}
                ,{field: 'massess1', title: '转正评价',align:"center",templet: function (data) {
                        if (data.massess1 == null){
                            return '<div style="color: red">未评分</div>'
                        }else{
                            return data.massess1
                        }
                    }}
                ,{field: 'massess2', title: '一年评价',align:"center",templet: function (data) {
                        if (data.massess2 == null){
                            return '<div style="color: red">未评分</div>'
                        }else{
                            return data.massess2
                        }
                    }}
                ,{field: 'massess3', title: '两年评价',align:"center",templet: function (data) {
                        if (data.massess3 == null){
                            return '<div style="color: red">未评分</div>'
                        }else{
                            return data.massess3
                        }
                    }}
                ,{field: 'massess4', title: '三年评价',align:"center",templet: function (data) {
                        if (data.massess4 == null){
                            return '<div style="color: red">未评分</div>'
                        }else{
                            return data.massess4
                        }
                    }}
            ]],
        });
        //监听行工具事件
        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'assess') {
                var studentId = data.studentId;
                layer.open({
                    type:2,//1：弹出隐藏div 2：弹出完整jsp页面
                    title:'评分',
                    shadeClose:true,//点击遮罩关闭弹窗
                    content:'/addMassessPage?studentId='+studentId,
                    area:['600px','500px'],
                    end:function () {
                        //刷新当前页
                        $(".layui-laypage-btn").click();
                    }
                });

            } else if (layEvent === 'detail') {
                var maId = data.maId;
                layer.open({
                    type:2,//1：弹出隐藏div 2：弹出完整jsp页面
                    title:'评分修改',
                    shadeClose:true,//点击遮罩关闭弹窗
                    content:'/getMassessById?maId=' + maId,
                    area:['600px','500px'],
                    end:function () {
                        //刷新当前页
                        $(".layui-laypage-btn").click();
                    }
                })
            }
        });
        //监听事件，监听lay-filter为test的工具栏
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            var  layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'query') {
                var filter = $("#filter").val();
                table.reload("demo", {//demo对应table的id
                    where: {
                        studentName: filter
                    },//where代表过滤条件
                    page: {
                        curr: 1
                    }
                })
                $("#filter").val(filter);
            }
        });

    });


</script>

</body>
</html>