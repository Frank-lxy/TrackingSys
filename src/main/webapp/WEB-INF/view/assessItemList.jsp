<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/12
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>评价项管理</title>
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
        <h2>评价项列表</h2>
    </div>
    <div align="right">
        <div class="layui-input-inline">
            <input type="text" id="filter" placeholder="请输入评价项名称" autocomplete="off" class="layui-input">
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
            ,url: '/getAllAssessItem' //数据接口
            ,height: 'full-25'
            ,page:true //开启分页
            ,limit:10   //每页显示几条数据
            ,limits:[10,15,20,25,30]
            ,cols: [[ //表头
                {type:'numbers',title:'序号', width:'15%', sort:true}
                ,{field: 'assessItemId', title: '评价项id', hide:true}
                ,{field: 'assessItemName', title: '评价项名称',align:"center"}
                ,{title:'操作', toolbar:'#barDemo',align:'center', width:'25%'}
            ]]
        });
        //监听事件，监听lay-filter为test的元素的工具栏
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'query'://点击查询
                    //获取过滤条件
                    var filter = $("#filter").val();
                    table.reload("demo",{
                        where:{//where对应过滤条件
                            assessItemName:filter
                        },
                        page:{
                            curr: 1
                        }
                    });
                    //保留过滤条件
                    $("#filter").val(filter);
                    break;
                case 'add'://点击新增
                    layer.open({
                        type:2, //弹出完整jsp，type=1弹出底层div
                        title:"新增评价项",  //弹窗标题
                        content:'getAssessItemAdd',
                        shadeClose:true,    //点击遮罩，关闭弹框
                        area:['400px','210px']  //弹窗大小
                    });
                    break;
            }
        });

        //监听表格的行工具事件
        table.on('tool(test)', function(obj){
            //获取行数据
            var data = obj.data;
            if(obj.event === 'del'){//点击删除
                layer.confirm('确定要删除吗？',{icon:2,title:'删除'} ,function(index){
                    //获取该行的评分项id
                    var assessItemId = data.assessItemId;
                    $.ajax({
                        url:"deleteAssessItemById",
                        type:"post",
                        data:{
                            assessItemId:assessItemId
                        },
                        success:function (delMsg) {
                            layer.msg(delMsg);
                            //删除完成重新加载表格
                            table.reload("demo",function () {
                                url:"getAllAssessItem"
                            })
                        },
                        error:function () {
                            layer.msg("执行失败")
                        }
                    })
                });
            } else if (obj.event === 'edit'){//点击编辑
                //获取要编辑的评分项编号
                var assessItemId = data.assessItemId;
                //根据编号获取信息
                layer.open({
                    type: 2,//弹出完整jsp，type=1弹出底层div
                    title: "编辑评价项",
                    content: "getAssessItemById?assessItemId=" + assessItemId,
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
