<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/12
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门列表</title>
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
        <h2>部门列表</h2>
    </div>
    <div align="right">
        <div class="layui-input-inline">
            <input type="text" id="filter" placeholder="请输入部门名称" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <button class="layui-btn layui-btn-sm" lay-event="query">查询</button>
            <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
        </div>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
            ,url: '/getAllDepartment' //数据接口
            ,height: 'full-25'
            ,page:true //开启分页
            ,limit:10   //每页显示几条数据
            ,limits:[10,15,20,25,30]
            ,cols: [[ //表头
                {type:'numbers',title:'序号', width:'15%', sort:true}
                ,{field: 'departmentId', title: '部门编号', hide:true}
                ,{field: 'departmentName', title: '部门名称',align:"center"}
                ,{title:'操作', toolbar:'#barDemo',align:'center', width:'25%'}
            ]]
        });
        //监听事件，监听lay-filter为test的元素的工具栏
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'query':
                    var filter = $("#filter").val();
                    table.reload("demo",{
                        where:{departmentName:filter}, //where对应过滤条件
                        page:{
                            curr: 1
                        }
                    });
                    $("#filter").val(filter);
                    break;
                case 'add':
                    layer.open({
                        type:2,//弹出完整jsp，type=1弹出底层div
                        title:"新增部门",
                        content:'getDepartmentAdd',
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
                    var departmentId = data.departmentId;
                    $.ajax({
                        url:"deleteDepartmentById",
                        type:"post",
                        data:{
                            departmentId:departmentId
                        },
                        success:function (delMsg) {
                            layer.msg(delMsg);
                            //重新加载表格
                            table.reload("demo",function () {
                                url:"getAllDepartment"
                            })
                        },
                        error:function () {
                            layer.msg("执行失败")
                        }
                    })
                });
            } else if (obj.event === 'edit'){
                //获取要编辑的编号
                var departmentId = data.departmentId;
                //根据编号获取信息
                layer.open({
                    type: 2,//弹出完整jsp，type=1弹出底层div
                    title: "编辑部门",
                    content: "getDepartmentById?departmentId=" + departmentId,
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
