<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/16
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学员跟踪表</title>
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
        .layui-table-cell {
            height: auto;
            overflow: visible;
            text-overflow: inherit;
            white-space: normal;
        }
        .layui-table-cell {
            padding: 0 2px;
        }
    </style>
</head>
<body id="body">
<div align="center" style="margin: 0 10px">
    <table id="demo" lay-filter="test" lay-data="{id:'head'}"></table>
    <div id="demoDiv" style="margin-top: -10px"></div>
</div>
<script type="text/html" id="toolbarDemo">
    <div align="left" style="float: left">
        <h2>学员跟踪表</h2>
    </div>
    <div align="right">
        <div class="layui-input-inline">
            <%--班期下拉列表--%>
            <select name="classId" id="classId" lay-filter="receive" lay-search="">
                <c:forEach var="clazzList" items="${sessionScope.clazzList}">
                    <option value="${clazzList.classId}" name="classId">${clazzList.clazz}</option>
                </c:forEach>
            </select>
        </div>
        <button class="layui-btn layui-btn-sm" lay-event="query" id="query">查询</button>
    </div>
</script>
<script>
    layui.use(['table',"layer","laypage"], function(){
        var table = layui.table,
            layer = layui.layer,
            laypage = layui.laypage;
        var $ = layui.jquery;
        //获取最新班期的班期id
        var classId = ${requestScope.clazzId};
        //定义一个表头二级菜单内容的数组
        var head =[];
        //获取表格数据
        $.ajax({
            url:"/getCourseByClassId",
            type:"get",
            data:{
                classId:classId
            },
            success:function (data) {
                //执行成功，获取最新班期课程，并将课程名添加到表头二级菜单中
                $.each(data,function (index,value) {
                    head.push( {field:value.courseId, title:value.courseName, align:"center"})
                });
                //渲染表格
                table.render({
                    elem: '#demo'
                    ,toolbar: '#toolbarDemo'    //头工具栏
                    ,height: 'full-102'
                    ,url: '/getStudentListByClassId?classId=' + classId     //数据接口
                    ,page: true  //分页
                    ,limit: 4   //每页显示几条数据
                    ,limits: [4,8,16,24,32]
                    ,cols: [[ //表头
                        {type:'numbers',title:'序号',rowspan:2}
                        ,{field: 'studentId', title: '学生编号', hide:true, rowspan:2}
                        ,{field: 'studentName', title: '姓名', align:"center", rowspan:2}
                        ,{field: 'sex', title: '性别',align:"center", rowspan:2,width:40}
                        ,{field: 'graduate', title: '学校', align:"center", rowspan:2,width:120}
                        ,{field: 'homeTown', title: '籍贯', align:"center", rowspan:2,width:110}
                        ,{title: '培训期间测试成绩', align:"center", colspan:head.length}
                        ,{field: 'sevaluate', title: '学校评价', align:"center", rowspan:2}
                        ,{field: 'mevaluate1', title: '转正评价', align:"center", rowspan:2}
                        ,{field: 'mevaluate2', title: '一年评价', align:"center", rowspan:2}
                        ,{field: 'mevaluate3', title: '两年评价', align:"center", rowspan:2}
                        ,{field: 'mevaluate4', title: '三年评价', align:"center", rowspan:2}
                    ],head]
                });
            },
            error:function () {
                layer.msg("执行失败")
            }
        });

        //监听事件，监听lai-filter为test的元素的工具栏
        table.on('toolbar(test)', function(obj){    //obj指按钮
            if (obj.event == 'query'){
                //获取当前选中的下拉菜单的value值
                var clazzId = $("#classId option:selected").val();

                $.ajax({
                    url:"/getCourseByClassId",
                    type:"get",
                    data:{
                        classId:clazzId
                    },
                    success:function (data) {
                        //将表头二级菜单内容置空
                        head = [];
                        //重新设置表头二级菜单内容
                        $.each(data,function (index,value) {
                            head.push( {field:value.courseId, title:value.courseName, align:"center"})
                        });

                        var tableIns = table.render({
                            elem: '#demo'
                            ,toolbar: '#toolbarDemo'    //头工具栏
                            ,height: 'full-102'
                            ,url: '/getStudentListByClassId'     //数据接口
                            ,page: true  //分页
                            ,limit: 4   //每页显示几条数据
                            ,limits: [4,8,16,24,32]
                            ,cols: []
                        });

                        //重载表格数据
                        tableIns.reload({//demo对应table的id
                            where:{ //where代表过滤条件
                                classId:clazzId,
                            },
                            page:{
                                curr:1
                            },
                            cols:[[ //表头
                                {type:'numbers',title:'序号',rowspan:2}
                                ,{field: 'studentId', title: '学生编号', hide:true, rowspan:2}
                                ,{field: 'studentName', title: '姓名', align:"center", rowspan:2}
                                ,{field: 'sex', title: '性别',align:"center", rowspan:2,width:40}
                                ,{field: 'graduate', title: '学校', align:"center", rowspan:2,width:120}
                                ,{field: 'homeTown', title: '籍贯', align:"center", rowspan:2,width:110}
                                ,{title: '培训期间测试成绩', align:"center", colspan:head.length}
                                ,{field: 'sevaluate', title: '学校评价', align:"center", rowspan:2}
                                ,{field: 'mevaluate1', title: '转正评价', align:"center", rowspan:2}
                                ,{field: 'mevaluate2', title: '一年评价', align:"center", rowspan:2}
                                ,{field: 'mevaluate3', title: '两年评价', align:"center", rowspan:2}
                                ,{field: 'mevaluate4', title: '三年评价', align:"center", rowspan:2}
                            ],head]
                        });
                    },
                    error:function () {
                        layer.msg("执行失败")
                    }
                });

                //在页面上保留查询条件
                $("#classId").val(clazzId);
            }
        });

        //监听行单击事件，单击行弹出详细的学员评价表
        table.on('row(test)', function(obj){
            var data = obj.data;

            /*layer.alert(JSON.stringify(data), {
                title: '当前行数据：'
            });*/

            //获取要编辑的编号
            var studentId = data.studentId;
            //根据编号获取信息
            layer.open({
                type: 2,//弹出完整jsp，type=1弹出底层div
                title: "学员成长跟踪表",
                content: "sassessDetailed?studentId=" + studentId,
                shadeClose: true,//点击遮罩，关闭弹框
                area: ['1035px','470px'],
            });

            //标注选中样式
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        });
    });
</script>
</body>
</html>
