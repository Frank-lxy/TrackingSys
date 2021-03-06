<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/9/15
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生评分</title>
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

    </style>
</head>
<body id="body">
<div align="center"     style="margin: 0px 10px"><table id="demo" lay-filter="test"></table>
    <div id="demoDiv" style="margin-top: -10px"></div>
</div>
<script type="text/html" id="toolbarDemo">
    <div align="left" style="float: left">
        <h2>评分列表</h2>
    </div>
    <div align="right">
        <div class="layui-input-inline">
            <input type="text" id="studentName" name="studentName"  autocomplete="off" placeholder="请输入姓名" class="layui-input">
        </div>

        <button class="layui-btn layui-btn-sm" lay-event="query" id="query">查询</button>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn  layui-btn-normal layui-btn-xs" lay-event="detail">查看</a>
</script>
<script>
    layui.use(['table',"layer"], function(){
        var table = layui.table;
        var layer = layui.layer;
        var $ = layui.jquery;
        var tState = ${requestScope.tState};
        //存放每一个部门的评分项
        var departmentId=${requestScope.departmentId};
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
                    head.push( {field:value.assessItemId, title:value.assessItemName,align:"center",edit: 'text'
                       /* ,templet:function () {
                                alert(value.assessItemId)
                                return"<div style='color: red'>待评价</div>"

                        }*/
                    })
                })
                table.render({
                    elem: '#demo'
                    ,toolbar: '#toolbarDemo'
                    ,height: 'full-32'
                    ,url: '/getAllMarkInfo?tState='+tState//数据接口
                    ,page: true //分页
                    ,limit: 8//每页显示几条数据
                    ,limits: [8,16,24,32]
                    ,cols: [[ //表头
                        {type:'numbers',title:'序号',rowspan:2}
                        ,{field: 'studentId', title: '学生编号',align:"center",hide:true,rowspan:2}
                        ,{field: 'studentName', title: '姓名',align:"center",rowspan:2}
                        ,{title: '评价分项',align:"center",colspan:head.length}
                        ,{title:'操作',align:'center', toolbar: '#barDemo',width:180,rowspan:2}
                    ],head]
                });
            },
            error:function () {
                layer.msg("执行失败")
            }
        })
        //监听单元格编辑
        table.on('edit(test)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            var oldScore = $(this).prev().text();// 单元格编辑之前的值
            if(isNaN(value) || value < 0 || value > 5){
                //重新赋值
                $(this).val(oldScore);
                layer.msg("输入错误，只能输入数字(0-5)")
                return;
            }else {
                $.ajax({
                    url:'addOrEditMark',
                    type:"post",
                    data:{
                        studentId:data.studentId,
                        assessItemId:field,
                        mark:value,
                        tState:${requestScope.tState}
                    },
                    dataType:"text",//服务器响应数据的类型
                    success:function (data) {
                        if(data){
                            layer.msg(data);
                            setTimeout("close()",2000)
                        }
                    },
                    error:function () {
                        layer.msg("执行失败")
                        setTimeout("close()",2000)
                    }
                })
            }

        });
        //监听事件监听lai-filter为test的元素的工具栏
        table.on('toolbar(test)', function(obj){//obj只按钮
            switch(obj.event){
                case 'query':
                    var studentName=$("#studentName").val();
                    table.reload("demo",{//demo对应table的id
                        url:'getAllMarkInfo?tState='+tState,
                        where:{
                            studentName:studentName,
                        },//where代表过滤条件
                        page:{
                            curr:1
                        }
                    });
                    //在页面上保留查询条件
                    $("#studentName").val(studentName);
                    break;
            };
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                //获取要编辑的编号
                var studentId = data.studentId;
                var tState = ${requestScope.tState}
                //根据编号获取信息
                layer.open({
                    type: 2,//弹出完整jsp，type=1弹出底层div
                    title: "学员详细信息",
                    content: "massessDetailed?studentId=" + studentId + "&tState=" + tState,
                    shadeClose: true,//点击遮罩，关闭弹框
                    area: ['1035px','470px'],
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

