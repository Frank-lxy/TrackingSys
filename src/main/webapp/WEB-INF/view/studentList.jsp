<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/12
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学员基本信息管理</title>
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
        <h2>学员基本信息</h2>
    </div>
    <div align="right">
        <div class="layui-input-inline">
            <input type="text" id="filter" placeholder="请输入学员姓名" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <select name="departmentId" id="departmentId" lay-filter="receive" lay-search="">
                <option value="">请选择部门</option>
                <c:forEach var="departmentList" items="${sessionScope.departmentList}">
                    <option value="${departmentList.departmentId}" name="departmentId">${departmentList.departmentName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="layui-input-inline">
            <select name="jobId" id="jobId" lay-filter="receive" lay-search="">
                <option value="">请选择职务</option>
                <c:forEach var="jobList" items="${sessionScope.jobList}">
                    <option value="${jobList.jobId}" name="jobId">${jobList.jobName}</option>
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
    <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>&nbsp;
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
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
            ,url: '/getAllStudent' //数据接口
            ,height: 'full-102'
            ,page:true //开启分页
            ,limit:8   //每页显示几条数据
            ,limits:[8,10,15,20]
            ,cols: [[ //表头
                {type:'checkbox', width:'5%'}
                ,{type:'numbers',title:'序号', width:'7%', sort:true}
                ,{field: 'studentId', title: '学员编号', hide:true}
                ,{field: 'studentName', title: '姓名', width:'8%',align:"center"}
                ,{field: 'sex', title: '性别',align:"center", width:'7%', templet: '#sexTpl', sort:true}
                ,{field: 'graduate', title: '毕业院校',align:"center", width:'13%'}
                ,{field: 'major', title: '专业',align:"center", width:'13%', hide:true}
                ,{field: 'clazz', title: '班期',align:"center", templet:function (data) {
                        if (data.clazz == null){
                            return '未上课'
                        }else {
                            return data.clazz
                        }
                    }}
                ,{field: 'departmentName', title: '部门',align:"center", templet:function (data) {
                        if (data.departmentName == null){
                            return '未入职'
                        }else {
                            return data.departmentName
                        }
                    }}
                ,{field: 'jobName', title: '职位',align:"center", templet:function (data) {
                        if (data.jobName == null){
                            return '未入职'
                        }else {
                            return data.jobName
                        }
                    }}
                ,{title:'操作', toolbar:'#barDemo',align:'center', width:180}
            ]]
        });
        //监听事件，监听lay-filter为test的元素的工具栏
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'query':
                    var filter = $("#filter").val();
                    var departmentId = $("#departmentId option:selected").val();
                    var jobId = $("#jobId option:selected").val();
                    table.reload("demo",{
                        where:{
                            studentName:filter,
                            departmentId:departmentId,
                            jobId:jobId
                        }, //where对应过滤条件
                        page:{
                            curr: 1
                        }
                    });
                    $("#filter").val(filter);
                    $("#departmentId").val(departmentId);
                    $("#jobId").val(jobId);
                    break;
                case 'add':
                    layer.open({
                        type:2,//弹出完整jsp，type=1弹出底层div
                        title:"新增学员",
                        content:'getStudentAdd',
                        shadeClose:true,//点击遮罩，关闭弹框
                        area:['950px','470px']
                    });
                    break;
                case 'delete':
                    var checkStatus = table.checkStatus('demo'); //demo为table的ID，获取选中行
                    var data = checkStatus.data;   //获取选中行的数据
                    if (data.length < 1) {
                        layer.msg("请选择要删除的数据");
                    } else {
                        var count = 0;
                        for (var i = 0; i < data.length; i++) {
                            if (data[i].classId != null || data[i].departmentName != null){
                                count++;
                            }
                        }
                        if (count == 0){
                            layer.confirm("确定要删除吗",{icon:2,title:'删除'}, function () {
                                var studentIds = "";
                                for (var i = 0; i < data.length; i++) {
                                    studentIds += data[i].studentId + ",";
                                }
                                studentIds = studentIds.substring(0, studentIds.length - 1);     //截掉最后一个逗号
                                $.ajax({
                                    url: "deleteStudentBatch",
                                    data: {
                                        studentIds: studentIds
                                    },
                                    success: function (data) {
                                        layer.msg(data);
                                        table.reload("demo", function () {   /*重新加载表格*/
                                            url: "/getAllStudent"
                                        })
                                    },
                                    error: function () {
                                        layer.msg("执行失败");
                                    }
                                })
                            });
                        }else {
                            layer.msg("存在开始学习或入职学员，不能删除");
                        }
                    }
                    break;
            }
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                if (data.departmentName != null){
                    layer.msg("该学员已入职，不能删除");
                }else if (data.clazz != null){
                    layer.msg("该学员已开始学习，不能删除");
                }else {
                    layer.confirm('确定要删除吗？',{icon:2,title:'删除'} ,function(index){
                        var studentId = data.studentId;
                        $.ajax({
                            url:"deleteStudentById",
                            type:"post",
                            data:{
                                studentId:studentId
                            },
                            success:function (delMsg) {
                                layer.msg(delMsg);
                                //重新加载表格
                                table.reload("demo",function () {
                                    url:"getAllStudent"
                                })
                            },
                            error:function () {
                                layer.msg("执行失败")
                            }
                        })
                    });
                }
            } else if (obj.event === 'edit'){
                //获取要编辑的编号
                var studentId = data.studentId;
                //根据编号获取信息
                layer.open({
                    type: 2,//弹出完整jsp，type=1弹出底层div
                    title: "编辑学员信息",
                    content: "getStudentById?studentId=" + studentId,
                    shadeClose: true,//点击遮罩，关闭弹框
                    area: ['950px','510px'],
                    end:function () {
                        //刷新当前页
                        $(".layui-laypage-btn").click();
                    }
                });
            } else if (obj.event === 'search'){
                //获取要编辑的编号
                var studentId = data.studentId;
                //根据编号获取信息
                layer.open({
                    type: 2,//弹出完整jsp，type=1弹出底层div
                    title: "学员详细信息",
                    content: "getStudentDetailedById?studentId=" + studentId,
                    shadeClose: true,//点击遮罩，关闭弹框
                    area: ['930px','390px'],
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
