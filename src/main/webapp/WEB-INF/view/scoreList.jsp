<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/13
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生评价</title>
    <link href="static/layui/css/layui.css" rel="stylesheet">
    <script src="static/layui/layui.js"></script>
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

    </style>
</head>
<body>
<div align="center"><table id="demo" lay-filter="test"></table></div>

<script type="text/html" id="toolbarDemo">
    <div align="left" style="float: left">
        <h2>成绩列表</h2>
    </div>
    <div align="right">
        <div class="layui-input-inline">
            <input type="text" id="studentName" name="studentName" placeholder="请输入姓名" class="layui-input">
        </div>

        <div class="layui-input-inline">
            <select name="classId" id="classId">
                <c:forEach var="clazz" items="${sessionScope.classes}">
                    <option value="${clazz.classId}">${clazz.clazz}</option>
                </c:forEach>
            </select>
        </div>
        <button class="layui-btn layui-btn-sm" lay-event="query">查询</button>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="update">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
</script>
<script>
    layui.use(['table',"layer"], function(){
        var table = layui.table;
        var layer = layui.layer;
        var $ = layui.jquery;
        var head =[];//存放每一个期次的课程
        var classId=1;
        //获取老师所教期次课程
        $.ajax({
            url:"getAllCourseByClassId",
            type:"get",
            data:{
                classId:classId
            },
            success:function (data) {

                if(data){
                    $.each(data,function (index,value) {
                        head.push( {field:value.courseId, title: value.courseName,align:"center"})
                        console.log( value.courseName)
                    })

                    table.render({
                        elem: '#demo'
                        ,toolbar: '#toolbarDemo'
                        ,height: 'full-90'
                        ,url: '/getAllScoreInfo?classId='+classId //数据接口
                        ,page: true //分页
                        ,limit: 5//每页显示几条数据
                        ,limits: [5,10,15,20]
                        ,cols: [[ //表头
                            {type:'numbers',title:'序号',rowspan:2}
                            ,{field: 'studentId', title: '学生编号',align:"center",hide:true,rowspan:2}
                            ,{field: 'studentName', title: '姓名',align:"center",rowspan:2}
                            ,{field: 'sex', title: '性别',align:"center",hide:true,rowspan:2}
                            ,{field: 'graduate', title: '学校',align:"center",rowspan:2}
                            ,{field: 'homeTown', title: '籍贯',align:"center",rowspan:2}
                            ,{title: '培训期间测试成绩',align:"center",colspan:head.length}
                            ,{fixed: 'right', title:'操作',align:'center', toolbar: '#barDemo',rowspan:2}
                        ],head]
                    });
                }
            },
            error:function () {
                layer.msg("执行失败")
            }
        })




        //监听事件监听lai-filter为test的元素的工具栏
        table.on('toolbar(test)', function(obj){//obj只按钮
            switch(obj.event){
                case 'query':
                    var studentName=$("#studentName").val();
                    var classId=$("#classId").val()
                    table.reload("demo",{//demo对应table的id
                        where:{
                            studentName:studentName,
                            classId:classId
                        },//where代表过滤条件
                        page:{
                            curr:1
                        }
                    })
                    break;
            };
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'assess'){
                var studentId = data.studentId;
                var classId = data.classId;
                var studentName = data.studentName;
                layer.open({
                    type:2,//1：弹出隐藏div 2：弹出完整jsp页面
                    title:'评分',
                    shadeClose:true,//点击遮罩关闭弹窗
                    content:'/sassess?studentId=' + studentId + '&classId='+ classId + '&studentName='+ studentName ,
                    area:['600px','500px'],
                    end:function () {
                        //刷新当前页
                        $(".layui-laypage-btn").click();
                    }
                })
            } else if(layEvent === 'update'){
                var saId = data.saId;
                var studentName = data.studentName;
                layer.open({
                    type:2,//1：弹出隐藏div 2：弹出完整jsp页面
                    title:'学生修改',
                    shadeClose:true,//点击遮罩关闭弹窗
                    content:'/getSassessById?saId=' + saId + '&studentName='+ studentName,
                    area:['600px','500px'],
                    end:function () {
                        //刷新当前页
                        $(".layui-laypage-btn").click();
                    }
                })
            }
        });
    });
</script>
</body>
</html>
