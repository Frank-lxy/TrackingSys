<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/14
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>老师评价详细信息</title>
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
    <table id="demo" lay-filter="test"></table>
    <div id="demoDiv" style="margin-top: -10px">
    </div>
</div>

<script type="text/html" id="toolbarDemo">
    <div align="center">
        <h2>培训学校评价</h2>
    </div>
</script>
<script>

    $(function () {
        var obj =$("<table  class=\"layui-table\" style=\"width:1025\"><tr><td width='100px'>评价<br>(包括主要优点及缺点)</td><td height='180px'>${requestScope.sassess.assess}</td></tr>")
        $("#demoDiv").append(obj)
    })

    layui.use(['table',"layer"], function(){
        var table = layui.table;
        var layer = layui.layer;
        var $ = layui.jquery;
        var head =[];//存放每一个期次的课程
        var classId=1;
        var studentId = ${requestScope.studentId}
        //获取老师所教期次课程
        $.ajax({
            url:"getAllCourseByClassId",
            type:"get",
            data:{
                classId:classId
            },
            success:function (data) {
                $.each(data,function (index,value) {
                    head.push( {field:value.courseId, title:value.courseName,align:"center"})
                })
                table.render({
                    elem: '#demo'
                    ,toolbar: '#toolbarDemo'
                    ,url: '/getDetailInfoById?studentId=' + studentId //数据接口
                    ,width:1025
                    ,page: false
                    ,cols: [[
                        {field: 'school', title: '培训学校',align:"center",rowspan:2,width:130}
                        ,{field: 'clazz', title: '班期',align:"center",rowspan:2}
                        ,{field: 'teacher', title: '评价人',align:"center",rowspan:2}
                        ,{field: 'graduate', title: '学校',align:"center",rowspan:2,hide:true}
                        ,{field: 'homeTown', title: '籍贯',align:"center",rowspan:2,hide:true}
                        ,{title: '培训期间测试成绩',align:"center",colspan:head.length}
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
