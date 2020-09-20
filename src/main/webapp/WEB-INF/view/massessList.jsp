<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/9/12
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>经理评价页面</title>
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
<div align="center" style="margin: 0px 10px">
    <table class="layui-hide" id="demo" lay-filter="test"></table>
</div>
<script type="text/html" id="toolbarDemo">
    <div style="float: left">
        <h2>评分列表</h2>
    </div>
    <div align="right">
        <div class="layui-input-inline">
            <input type="text" name="" required  placeholder="请输入学生姓名"  autocomplete="off" id ="filter" class="layui-input" >
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
        var tState = ${requestScope.tState};
        table.render({
            elem: '#demo'
            ,toolbar: '#toolbarDemo'
            , url: '/getAllMassess?tState='+tState //数据接口
            ,page: true //开启分页
            ,height: 'full-32'
            ,limit:8
            ,limits:[8,10,15,20]
            , cols: [[ //表头
                {type:'numbers',title:'序号', width:'5%', sort: true}
                , {field: 'maId', title: '评价编号',hide:true}
                , {field: 'studentId', title: '学生编号',align: 'center',hide:true}
                , {field: 'tState', title: '状态',align: 'center',hide:true}
                , {field: 'studentName', title: '学生姓名',align: 'center'}
                , {field: 'departmentName', title: '部门名称',align: 'center'}
                , {field: 'jobName', title: '职务',align: 'center'}
                , {field: 'hiredate', title: '入职时间',align: 'center'}
                ,{field: 'evaluate', title: '整体分数',align:"center",templet: function (data) {
                        if (data.evaluate == null){
                            return '<div style="color: red">待评分</div>'
                        }else{
                            return data.evaluate
                        }
                    }}
                ,{field: 'assess', title: '评价',align:"center",templet: function (data) {
                        if (data.assess == null){
                            return '<div  style="color: red">待评价</div>'
                        }else{
                            return data.assess
                        }
                    }}
                , {fixed:'right',field: 'state', title: '操作', width: '20%' ,align: 'center',
                     templet: function (data) {
                        if (data.state == 1){
                            return '<a class="layui-btn layui-btn-xs layui-btn-disabled">已评价</a>' +
                                    '<a class="layui-btn layui-btn-xs" lay-event="detail">修改</a>'
                        }else{
                            return '<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="assess" id="assess">评&nbsp;&nbsp;&nbsp;价</a>' +
                                    '<a class="layui-btn layui-btn-xs layui-btn-disabled">修改</a>'
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
                    title:'经理评分',
                    shadeClose:true,//点击遮罩关闭弹窗
                    content:'/addMassessPage?studentId='+studentId,
                    area:['500px','400px'],
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
                    area:['500px','400px'],
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

