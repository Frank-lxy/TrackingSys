<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: manager
  Date: 2020/9/18
  Time: 8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>经理评价详细信息</title>
    <link href="../../static/layui/css/layui.css" rel="stylesheet">
    <script src="../../static/layui/layui.js"></script>
    <script src="../../static/js/jquery-3.3.1.js"></script>
    <style>
        .layui-table-tool-self{
            display: none;
        }
        .layui-table-tool-temp{
            padding: 0 0 0 0;
        }
        .layui-table-view .layui-form-checkbox {
            margin-top: 7px;
        }

        .layui-table-cell { height:auto; overflow:visible; text-overflow:inherit; white-space:normal; }
    </style>
</head>
<body>
<div align="center">
    <h1>金桥学员成长跟踪表
        <a href="getStudentTracking"><button id="" class="layui-btn" style="float: right">返回</button></a>
    </h1>
</div>
<div align="center">
    <table id="demo1" lay-filter="test"></table>
    <div id="demoDiv1" style="margin-top: -10px">
    </div>
    <table id="demo2" lay-filter="test"></table>
    <div id="demoDiv2" style="margin-top: -10px">
    </div>
    <table id="demo3" lay-filter="test"></table>
    <div id="demoDiv3" style="margin-top: -10px">
    </div>
    <table id="demo4" lay-filter="test"></table>
    <div id="demoDiv4" style="margin-top: -10px">
    </div>
</div>

<script type="text/html" id="toolbarDemo1">
    <div align="center">
        <h2>转正工作评价</h2>
    </div>
</script>
<script type="text/html" id="toolbarDemo2">
    <div align="center">
        <h2>一年工作评价</h2>
    </div>
</script>
<script type="text/html" id="toolbarDemo3">
    <div align="center">
        <h2>两年工作评价</h2>
    </div>
</script>
<script type="text/html" id="toolbarDemo4">
    <div align="center">
        <h2>三年工作评价</h2>
    </div>
</script>
<script>

    $(function () {
        var obj =$("<table  class=\"layui-table\" style=\"width:1025\"><tr><td width='100px'>评价<br>(包括主要优点及缺点)</td><td height='180px'>${requestScope.massess1.assess}</td></tr>")
        $("#demoDiv1").append(obj)
    });
    $(function () {
        var obj =$("<table  class=\"layui-table\" style=\"width:1025\"><tr><td width='100px'>评价<br>(包括主要优点及缺点)</td><td height='180px'>${requestScope.massess2.assess}</td></tr>")
        $("#demoDiv2").append(obj)
    });
    $(function () {
        var obj =$("<table  class=\"layui-table\" style=\"width:1025\"><tr><td width='100px'>评价<br>(包括主要优点及缺点)</td><td height='180px'>${requestScope.massess3.assess}</td></tr>")
        $("#demoDiv3").append(obj)
    });
    $(function () {
        var obj =$("<table  class=\"layui-table\" style=\"width:1025\"><tr><td width='100px'>评价<br>(包括主要优点及缺点)</td><td height='180px'>${requestScope.massess4.assess}</td></tr>")
        $("#demoDiv4").append(obj)
    });
    layui.use(['table',"layer"], function(){
        var table = layui.table;
        var layer = layui.layer;
        var $ = layui.jquery;
        var tState = 1;
        var studentId = ${requestScope.studentId}
        //存放每一个期次的课程
        var departmentId=1;
        //获取老师所教期次课程
        var head =[];
        $.ajax({
            url:"getAllAssessItemByDeptId",
            type:"get",
            data:{
                departmentId:departmentId
            },
            success:function (data) {
                $.each(data,function (index,value) {
                    head.push( {field:value.assessItemId, title:value.assessItemName,align:"center"})
                })
                table.render({
                    elem: '#demo1'
                    ,toolbar: '#toolbarDemo1'
                    ,url: '/getDetailById?studentId=' + studentId + "&tState=" + tState //数据接口
                    ,width:1025
                    ,page: false //分页
                    ,cols: [[ //表头
                        {field: 'project', title: '项目',align:"center",rowspan:2,width:130}
                        ,{field: 'department', title: '员工部门',align:"center",rowspan:2}
                        ,{field: 'job', title: '员工职务',align:"center",rowspan:2}
                        ,{field: 'manager', title: '评价人',align:"center",rowspan:2}
                        ,{title: '评价分项',align:"center",colspan:head.length}
                        ,{field: 'evaluate', title: '整体评价分数',align:"center",rowspan:2,templet: function (data) {
                                if (data.evaluate == null || data.evaluate == 0){
                                    return ''
                                }else{
                                    return data.evaluate
                                }
                            }}
                    ],head]
                });
            },
            error:function () {
                layer.msg("执行失败")
            }
        })
    });
    layui.use(['table',"layer"], function(){
        var table = layui.table;
        var layer = layui.layer;
        var $ = layui.jquery;
        var tState = 2;
        var studentId = ${requestScope.studentId}
        //存放每一个期次的课程
        var departmentId=1;
        //获取老师所教期次课程
        var head =[];
        $.ajax({
            url:"getAllAssessItemByDeptId",
            type:"get",
            data:{
                departmentId:departmentId
            },
            success:function (data) {
                $.each(data,function (index,value) {
                    head.push( {field:value.assessItemId, title:value.assessItemName,align:"center"})
                })
                table.render({
                    elem: '#demo2'
                    ,toolbar: '#toolbarDemo2'
                    ,url: '/getDetailById?studentId=' + studentId + "&tState=" + tState //数据接口
                    ,width:1025
                    ,page: false //分页
                    ,cols: [[ //表头
                        {field: 'project', title: '项目',align:"center",rowspan:2,width:130}
                        ,{field: 'department', title: '员工部门',align:"center",rowspan:2}
                        ,{field: 'job', title: '员工职务',align:"center",rowspan:2}
                        ,{field: 'manager', title: '评价人',align:"center",rowspan:2}
                        ,{title: '评价分项',align:"center",colspan:head.length}
                        ,{field: 'evaluate', title: '整体评价分数',align:"center",rowspan:2,templet: function (data) {
                                if (data.evaluate == null || data.evaluate == 0){
                                    return ''
                                }else{
                                    return data.evaluate
                                }
                            }}
                    ],head]
                });
            },
            error:function () {
                layer.msg("执行失败")
            }
        })
    });
    layui.use(['table',"layer"], function(){
        var table = layui.table;
        var layer = layui.layer;
        var $ = layui.jquery;
        var tState = 3;
        var studentId = ${requestScope.studentId}
        //存放每一个期次的课程
        var departmentId=1;
        //获取老师所教期次课程
        var head =[];
        $.ajax({
            url:"getAllAssessItemByDeptId",
            type:"get",
            data:{
                departmentId:departmentId
            },
            success:function (data) {
                $.each(data,function (index,value) {
                    head.push( {field:value.assessItemId, title:value.assessItemName,align:"center"})
                })
                table.render({
                    elem: '#demo3'
                    ,toolbar: '#toolbarDemo3'
                    ,url: '/getDetailById?studentId=' + studentId + "&tState=" + tState //数据接口
                    ,width:1025
                    ,page: false //分页
                    ,cols: [[ //表头
                        {field: 'project', title: '项目',align:"center",rowspan:2,width:130}
                        ,{field: 'department', title: '员工部门',align:"center",rowspan:2}
                        ,{field: 'job', title: '员工职务',align:"center",rowspan:2}
                        ,{field: 'manager', title: '评价人',align:"center",rowspan:2}
                        ,{title: '评价分项',align:"center",colspan:head.length}
                        ,{field: 'evaluate', title: '整体评价分数',align:"center",rowspan:2,templet: function (data) {
                                if (data.evaluate == null || data.evaluate == 0){
                                    return ''
                                }else{
                                    return data.evaluate
                                }
                            }}
                    ],head]
                });
            },
            error:function () {
                layer.msg("执行失败")
            }
        })
    });
    layui.use(['table',"layer"], function(){
        var table = layui.table;
        var layer = layui.layer;
        var $ = layui.jquery;
        var tState = 4;
        var studentId = ${requestScope.studentId}
        //存放每一个期次的课程
        var departmentId=1;
        //获取老师所教期次课程
        var head =[];
        $.ajax({
            url:"getAllAssessItemByDeptId",
            type:"get",
            data:{
                departmentId:departmentId
            },
            success:function (data) {
                $.each(data,function (index,value) {
                    head.push( {field:value.assessItemId, title:value.assessItemName,align:"center"})
                })
                table.render({
                    elem: '#demo4'
                    ,toolbar: '#toolbarDemo4'
                    ,url: '/getDetailById?studentId=' + studentId + "&tState=" + tState //数据接口
                    ,width:1025
                    ,page: false //分页
                    ,cols: [[ //表头
                        {field: 'project', title: '项目',align:"center",rowspan:2,width:130}
                        ,{field: 'department', title: '员工部门',align:"center",rowspan:2}
                        ,{field: 'job', title: '员工职务',align:"center",rowspan:2}
                        ,{field: 'manager', title: '评价人',align:"center",rowspan:2}
                        ,{title: '评价分项',align:"center",colspan:head.length}
                        ,{field: 'evaluate', title: '整体评价分数',align:"center",rowspan:2,templet: function (data) {
                                if (data.evaluate == null || data.evaluate == 0){
                                    return ''
                                }else{
                                    return data.evaluate
                                }
                            }}
                    ],head]
                });
            },
            error:function () {
                layer.msg("执行失败")
            }
        })
    });
</script>
</body>
</html>