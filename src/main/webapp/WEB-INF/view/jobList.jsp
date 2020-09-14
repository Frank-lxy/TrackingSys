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
    <title>职务管理</title>
    <link rel="stylesheet" href="../../static/layui/css/layui.css">
    <script src="../../static/layui/layui.js"></script>
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
    </style>
</head>
<body>
<div align="center" style="margin: 0px 10px">
    <table id="demo" lay-filter="test"></table>
</div>
<script type="text/html" id="toolbarDemo">
    <div align="left" style="float: left">
        <h2>职务列表</h2>
    </div>
    <div align="right">
        <div class="layui-input-inline">
            <input type="text" id="filter" placeholder="请输入职务名称" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <button class="layui-btn layui-btn-sm" lay-event="query">查询</button>
            <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
        </div>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    layui.use(['table','layer','laypage'], function(){
        var table = layui.table
            ,layer = layui.layer
            ,laypage = layui.laypage;
        var $ = layui.jquery;
        //第一个实例
        table.render({
            elem: '#demo'
            ,toolbar: '#toolbarDemo'//添加工具栏
            ,url: '/getAllJob' //数据接口
            ,height: 'full-102'
            ,page:true //开启分页
            ,limit:8   //每页显示几条数据
            ,limits:[8,10,15,20]
            ,cols: [[ //表头
                {type:'numbers',title:'序号', width:'15%', sort:true}
                ,{field: 'jobId', title: '职务编号', hide:true}
                ,{field: 'jobName', title: '职务名称',align:"center"}
                ,{title:'操作', toolbar:'#barDemo',align:'center', width:'25%'}
            ]]
        });
        //监听事件，监听lay-filter为test的元素的工具栏
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'query':
                    var filter = $("#filter").val();
                    table.reload("demo",{
                        where:{jobName:filter}, //where对应过滤条件
                        page:{
                            curr: 1
                        }
                    });
                    break;
                case 'add':
                    layer.open({
                        type:2,//弹出完整jsp，type=1弹出底层div
                        title:"新增职务",
                        content:'getJobAdd',
                        shadeClose:true,//点击遮罩，关闭弹框
                        area:['400px','210px']
                    });
                    break;
            }
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('确定要删除吗？',{icon:2,title:'删除'} ,function(index){
                    var jobId = data.jobId;
                    $.ajax({
                        url:"deleteJobById",
                        type:"post",
                        data:{
                            jobId:jobId
                        },
                        success:function (delMsg) {
                            layer.msg(delMsg);
                            //重新加载表格
                            table.reload("demo",function () {
                                url:"getAllJob"
                            })
                        },
                        error:function () {
                            layer.msg("执行失败")
                        }
                    })
                });
            } else if (obj.event === 'edit'){
                //获取要编辑的编号
                var jobId = data.jobId;
                //根据编号获取信息
                layer.open({
                    type: 2,//弹出完整jsp，type=1弹出底层div
                    title: "编辑课程",
                    content: "getJobById?jobId=" + jobId,
                    shadeClose: true,//点击遮罩，关闭弹框
                    area: ['400px','280px'],
                    end:function () {
                        //刷新当前页
                        $(".layui-laypage-btn").click();
                    }
                });
            }
        });
    });
</script>
</body>
</html>
