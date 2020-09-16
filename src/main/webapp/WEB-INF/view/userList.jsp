<%--
  Created by IntelliJ IDEA.
  User: xzh
  Date: 2020/9/12
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>用户管理</title>
    <style>
    .layui-table-tool-self{
        display: none;
    }


</style >
    <link rel="stylesheet" href="../../static/layui/css/layui.css">
    <script src="../../static/layui/layui.js"></script>
</head>
<body>
<div align="center">
    <table id="demo" lay-filter="test"></table>
</div>

<script type="text/html" id="toolbarDemo">
    <div align="right">
        <div class="layui-input-inline">
            <input type="text" placeholder="请输入用户名" class="layui-input" id="userName">
        </div>
        <div class="layui-input-inline">
            <input type="text" placeholder="请输入用户职位" class="layui-input" id="Character">
        </div>
        <div class="layui-input-inline">
            <button class="layui-btn layui-btn-sm"  lay-event="query">查询</button>
            <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
            <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
        </div>
    </div>

</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="modify">重置密码</a>
</script>
<script>
    layui.use(['table','layer','jquery'], function() {
        var table = layui.table;
        var layer = layui.layer;
        var $ = layui.jquery;
        //第一个实例
        table.render({
            elem: '#demo'
            , height: 'full-90'
            , limit: 5
            , limits: [5, 10, 15, 20]
            , toolbar: '#toolbarDemo'//添加工具栏
            , url: '/getAllUser' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {type: 'checkbox'}
                , {field: 'userId', title: '用户id', width: 130, sort: true, hide: true}
                , {type: 'numbers', title: '序号'}
                , {field: 'userName', title: '用户名', width: 150}
                , {field: 'password', title: '密码', width: 150, sort: true}
                , {field: 'character', title: '权限', width: 150, sort: true}
                , {field: 'operate', title: '操作',toolbar:'#barDemo',width:150}
            ]]
        });
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'query':
                    layer.msg("查询");
                    var userName = $("#userName").val();
                    var Character = $("#Character").val();
                    //table重载
                    table.reload('demo',{
                        url: '/getUsers' //数据接口
                        ,where:{
                            userName:userName
                            ,Character:Character
                        }
                        ,page: {
                            curr: 1 //重新从第 1 页开始
                        }
                    });
                    break;
                case 'add':
                    layer.open({
                        type:2,
                        title:'新增用户',
                        content:'addUser',
                        shadeClose:true,//点击遮罩关闭弹窗
                        area:['380px','460px'],
                    });
                    break;
                case 'delete':
                    var checkStatus = table.checkStatus('demo');
                    //demo为选中行的id
                    var data=checkStatus.data;
                    if (data.length<1){
                        layer.msg("请选择要删除的数据")
                    } else{
                        layer.confirm('真的删除行么', function(index) {
                            var userIds = "";
                            for (var i = 0; i < data.length; i++) {
                                userIds += data[i].userId + ",";
                            }
                            userIds = userIds.substring(0, userIds.length - 1);
                            $.ajax({
                                url:"delUserById"
                                ,type:"post"
                                ,data:{
                                    userIds:userIds
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
            };
        });
        table.on('tool(test)',function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            if(layEvent === 'modify'){ //编辑
                var userId=data.userId;
                layer.confirm('真的重置么', function(index){
                    $.ajax({
                        url:"/inituser",
                        data:{userId:userId},
                        type:"post",
                        dataType:"text",
                        success: function (result) {
                            layer.msg('已重置',{icon:1});
                        },
                        error:function (e) {
                            layer.msg('重置失败',{icon:2});
                        }
                    })

                });
            }
        })
    });

</script>
</body>
</html>