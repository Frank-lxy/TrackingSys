<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h2>评价列表</h2>
    </div>
    <div align="right">
        <div class="layui-input-inline">
            <input type="text" id="studentName" name="studentName" placeholder="请输入姓名" class="layui-input">
        </div>

        <div class="layui-input-inline">
            <select name="classId" id="classId">
                <option value="">请选择期次</option>
                <c:forEach var="clazz" items="${sessionScope.clazzes}">
                    <option value="${clazz.classId}">${clazz.clazz}</option>
                </c:forEach>
            </select>
        </div>
        <button class="layui-btn layui-btn-sm" lay-event="query">查询</button>
    </div>
</script>
<%--<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="score">评分</a>
    <a class="layui-btn layui-btn-xs" lay-event="update">修改</a>

</script>--%>

<script>

    layui.use(['table',"layer"], function(){
        var table = layui.table;
        var layer = layui.layer;
        var $ = layui.jquery;
        var userId=${sessionScope.user.userId}
        //第一个实例
        table.render({
            elem: '#demo'
            ,toolbar: '#toolbarDemo'
            ,height: 'full-102'
            ,url: '/getAssessByPage?userId='+ userId//数据接口
            ,page: true //分页
            ,limit: 8//每页显示几条数据
            ,limits: [8,16,24,32]
            ,cols: [[ //表头
                {type:'numbers',title:'序号'}
                ,{field: 'id', title: '学号', hide:true}
                ,{field: 'studentId', title: '学生编号',align:"center",hide:true}
                ,{field: 'studentName', title: '学生姓名',align:"center"}
                ,{field: 'classId', title: '班期编号',align:"center",hide:true}
                ,{field: 'clazz', title: '班期名称',align:"center"}
                ,{field: 'saId', title: '评价编号',align:"center",hide:true}
                ,{field: 'evaluate', title: '整体分数',align:"center",templet: function (data) {
                        if (data.evaluate == null || data.evaluate == 0){
                            return '<div style="color:red">待评价</div>'
                        }else{
                            return data.evaluate
                        }
                    }}
                ,{field: 'assess', title: '评价',align:"center",templet: function (data) {
                        if (data.assess == null || data.evaluate == ''){
                            return '<div style="color:red">待评价</div>'
                        }else{
                            return data.assess
                        }
                    }}
                ,{field: 'state', title: '操作',align:"center",templet: function (data) {
                        if (data.state == 1){
                            return '<a class="layui-btn layui-btn-xs layui-btn-disabled" lay-event="">已评价</a>' +
                                '    <a class="layui-btn layui-btn-xs" lay-event="update">修改</a>'
                        }else{
                            return '<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="assess">评价</a>' +
                                '    <a class="layui-btn layui-btn-xs  layui-btn-disabled" lay-event="" >修改</a>'
                        }
                    }
                }
               /* ,{fixed: 'right', title:'操作',align:'center', toolbar: '#barDemo'}*/
            ]]
        });

        //监听事件监听lai-filter为test的元素的工具栏
        table.on('toolbar(test)', function(obj){//obj只按钮
            switch(obj.event){
                case 'query':
                    var studentName=$("#studentName").val();
                    var classId=$("#classId").val();
                    table.reload("demo",{//demo对应table的id
                        where:{
                           studentName:studentName,
                           classId:classId
                        },//where代表过滤条件
                        page:{
                            curr:1
                        }
                    });
                    $("#studentName").val(studentName);
                    $("#classId").val(classId);
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
