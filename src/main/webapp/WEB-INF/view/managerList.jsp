<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xzh
  Date: 2020/9/17
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>经理基本信息管理</title>
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
        .lui{
            background-color: #f8f8f8;'
        }
    </style>
</head>
<body>
<div align="center" style="margin: 0px 10px">
    <table id="demo" lay-filter="test"></table>
</div>
<script type="text/html" id="toolbarDemo">
    <div align="left" style="float: left">
        <h2>经理基本信息</h2>
    </div>
    <div align="right">
        <div class="layui-input-inline">
            <input type="text" id="managerName" placeholder="请输入经理姓名" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <select name="departmentId" id="departmentName" lay-filter="receive" lay-search="">
                <option value="">请选择部门</option>
                <c:forEach var="departmentName" items="${department}">
                    <option value="${departmentName}" name="departmentName">${departmentName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="layui-input-inline">
            <button class="layui-btn layui-btn-sm" lay-event="query">查询</button>
            <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
            <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
        </div>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="search">查看</a>&nbsp;
    <a class="layui-btn  layui-btn-xs" lay-event="edit">修改</a>&nbsp;
</script>
<script type="text/html" id="sexTpl">
    {{#  if(d.sex === '女'){ }}
    <span style="color: #F581B1;">{{ d.sex }}</span>
    {{#  } else { }}
    <span style="color: #1E9FFF;">{{ d.sex }}</span>
    {{#  } }}
</script>
<script>
    layui.use(['table','layer','laypage','form'], function(){
        var table = layui.table
            ,layer = layui.layer
            ,laypage = layui.laypage
            ,form = layui.form;
        var $ = layui.jquery;
        //第一个实例
        table.render({
            elem: '#demo'
            ,toolbar: '#toolbarDemo'//添加工具栏
            ,url: '/getAllManager' //数据接口
            ,height: 'full-60'
            ,page:true //开启分页
            ,limit:8   //每页显示几条数据
            ,limits:[8,10,15,20]
            ,cols: [[ //表头
                {type:'checkbox', width:'5%'}
                ,{type:'numbers',title:'序号', width:'15%', sort:true}
                ,{field: 'managerId', title: '经理编号', hide:true}
                ,{field: 'managerName', title: '姓名', width:'15%',align:"center"}
                ,{field: 'phoneNumber', title: '手机号', width:'15%',align:"center"}
                ,{field: 'sex', title: '性别',align:"center", width:'15%', templet: '#sexTpl', sort:true}
                ,{field: 'departmentName', title: '部门',align:"center"}
                ,{title:'操作', toolbar:'#barDemo',align:'center', width:180, style:'background-color: #f8f8f8;'}
            ]]
        });
        //监听事件，监听lay-filter为test的元素的工具栏
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'query':
                    var managerName = $("#managerName").val();
                    var departmentName = $("#departmentName option:selected").val();
                    table.reload("demo",{
                        url:'getManagers'
                        ,where:{
                            managerName:managerName,
                            departmentName:departmentName,

                        }, //where对应过滤条件
                        page:{
                            curr: 1
                        }
                    });
                    $("#managerName").val(managerName);
                    $("#departmentName").val(departmentName);
                    break;
                case 'add':
                    layer.open({
                        type:2,//弹出完整jsp，type=1弹出底层div
                        title:"新增经理",
                        content:'addManager',
                        shadeClose:true,//点击遮罩，关闭弹框
                        area:['650px','450px']
                    });
                    break;
                case 'delete':
                    var checkStatus = table.checkStatus('demo');
                    //demo为选中行的id
                    var data=checkStatus.data;
                    if (data.length<1){
                        layer.msg("请选择要删除的数据")
                    } else{
                        layer.confirm('真的删除行么',{icon:2,title:'删除'}, function(index) {
                            var managerIds = "";
                            for (var i = 0; i < data.length; i++) {
                                managerIds += data[i].managerId + ",";
                            }
                            managerIds = managerIds.substring(0, managerIds.length - 1);
                            $.ajax({
                                url:"delManagerById"
                                ,type:"post"
                                ,data:{
                                    managerIds:managerIds
                                }
                                ,dataType:"text"
                                ,success:function (data) {
                                    if (data){
                                        layer.msg("删除成功");
                                        window.location.reload();
                                    }else {
                                        layer.msg("删除失败")
                                    }
                                },
                                error: function () {
                                    layer.msg("执行失败")
                                }
                            })
                            //向服务端发送删除指令
                        });
                    }

                    break;
            }
        });
        //监听行工具事件
        table.on('tool(test)', function(obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                //获取要编辑的编号
                var managerId = data.managerId;
                //根据编号获取信息
                layer.open({
                    type: 2,//弹出完整jsp，type=1弹出底层div
                    title: "编辑经理信息",
                    content: "editManager?managerId=" + managerId,
                    shadeClose: true,//点击遮罩，关闭弹框
                    area:['650px','450px'],
                    end: function () {
                        //刷新当前页
                        $(".layui-laypage-btn").click();
                    }
                });
            } else if (obj.event === 'search') {
                //获取要编辑的编号
                var managerId = data.managerId;
                //根据编号获取信息
                layer.open({
                    type: 2,//弹出完整jsp，type=1弹出底层div
                    title: "经理详细信息",
                    content: "managerDetail?managerId=" + managerId,
                    shadeClose: true,//点击遮罩，关闭弹框
                    area:['650px','440px'],
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