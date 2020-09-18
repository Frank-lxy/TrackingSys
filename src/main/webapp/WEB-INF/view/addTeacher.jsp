<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xzh
  Date: 2020/9/18
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增教师</title>
    <link rel="stylesheet" href="../../static/layui/css/layui.css">
    <script src="../../static/layui/layui.js"></script>
    <style>
        .layui-input-block {
            margin-left: 65px;
            min-height: 30px;
        }

        .layui-form-label {
            width: auto;
            min-width: 80px;
            padding: 9px 5px;
            margin-left: 10px;
        }

        .layui-form-item .layui-input-inline {
            margin-bottom: 20px;
        }

        .layui-form-select dl {
            max-height: 190px;
        }

        .layui-form-radio {
            line-height: 20px;
            margin: 3px 0 3px 0;
            padding-right: 0;
            padding-left: 20px;
        }

        img {
            height: 170px;
        }

        .showPhoto {
            border: solid 1px #eaeaea;
            margin: 15px 15px 10px 0;
            height: 170px;
            width: 110px;
        }

        .layui-laydate-main {
            height: 265px !important;
        }
    </style>
</head>
<body>
<div style="margin: 15px 0px 0px 0px">
    <div style="display: flex;justify-content: center">
        <div class="layui-form">
            <div class="layui-form-item">
                <div>
                    <label class="layui-form-label"><span style="color: red">*</span>姓名：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="teacherName" id="teacherName" required lay-verify="required"
                               lay-reqtext="姓名不能为空" autocomplete="off" class="layui-input">
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>性别：</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="sex" value="男" checked>男
                        <input type="radio" name="sex" value="女">女
                    </div>
                </div>
                <div style="height: 230px;width: 310px;float: right;margin-right: 20px">
                    <div style="margin-left: 0px">
                        <label class="layui-form-label" style="margin-left: 0px">照片：</label>
                        <div class="layui-input-inline">
                            <form id="uploadForm" method="post" enctype="multipart/form-data">
                                <input type="file" id="photo" name="photo" style="display: none">
                                <button type="button" class="layui-btn" id="updatePhoto">上传</button>
                            </form>
                            <input type="hidden" id="filePath">
                            <div class="showPhoto" id="uploadDiv"></div>
                        </div>
                    </div>
                </div>
                <div style="float: left">
                    <label class="layui-form-label"><span style="color: red">*</span>出生年月：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="birthday" id="birthday" required lay-verify="date"
                               placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div style="float: left">
                    <label class="layui-form-label"><span style="color: red">*</span>籍贯：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="homeTown" id="homeTown" required lay-verify="required"
                               lay-reqtext="籍贯不能为空" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div style="float: left">
                    <label class="layui-form-label"><span style="color: red">*</span>联系电话：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="phoneNumber" id="phoneNumber" required lay-verify="phone"
                               lay-reqtext="联系电话不能为空"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                    <div style="float: left">
                    <label class="layui-form-label"><span style="color: red">*</span>身份证号：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="idCardNum" id="idCardNum" required lay-verify="required"
                               lay-reqtext="身份证号码不能为空" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-input-inline" style="margin-left: 45%;margin-bottom: 0;margin-top: 20px">
                    <button id="addTeacher" class="layui-btn" lay-submit lay-filter="demo1">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['layer', 'form', 'upload', 'laydate'], function () {
        var layer = layui.layer
            , form = layui.form
            , upload = layui.upload
            , laydate = layui.laydate;
        $ = layui.jquery;

        //监听出生日期
        laydate.render({
            elem: '#birthday',
            trigger: 'click'
        });

        //失去焦点时验证身份证号
        $("#idCardNum").blur(function () {
            var reg = /^\d{14}(\d|X|x)$|^\d{17}(\d|X|x)$/;
            if (!reg.test($(this).val())) {
                layer.msg("输入的身份证号不正确");
            }
        });

        /*点击上传照片*/
        $("#updatePhoto").click(function () {
            $('#photo').click();
        });

        /*上传照片*/
        $("#photo").change(function () {
            //使用FormData实现ajax的文件提交
            var formData = new FormData($("#uploadForm")[0]);
            $.ajax({
                url: "uploadFile",
                type: "post",
                data: formData,
                cache: false,//是否缓存
                //即告诉服务器，从浏览器提交过来的数据采用默认的数据格式
                contentType: false,
                //设定为false可避免jQuery对formData的默认处理
                processData: false,
                success: function (data) {
                    var img = "<img src='../../" + data + "'>";
                    $("#uploadDiv").html(img);
                    $("#filePath").val(data);
                },
                error: function (data) {
                    alert("执行失败")
                }
            })
        });
        /*点击提交按钮*/
        $("#addTeacher").click(function () {
            var teacherName = $("#teacherName").val(),
                sex = $("input[name='sex']:checked").val(),
                birthday = $("#birthday").val(),
                homeTown = $("#homeTown").val(),
                phoneNumber = $("#phoneNumber").val(),
                idCardNum = $("#idCardNum").val(),
                photo = $("#filePath").val()
            if (teacherName != '' && birthday != '' && homeTown != '' && phoneNumber != '' && idCardNum != '') {
                $.ajax({
                    url: "addNewTeacher",
                    type: "post",
                    data: {
                        teacherName: teacherName,
                        sex: sex,
                        birthday: birthday,
                        homeTown: homeTown,
                        phoneNumber: phoneNumber,
                        idCardNum: idCardNum,
                        photo: photo,
                    },
                    dataType: "text",
                    success: function (data) {
                        layer.msg(data);
                        setTimeout('closeAdd()', 1000);
                    },
                    error: function (data) {
                        layer.msg("执行失败");
                        setTimeout('closeAdd()', 1000);
                    }
                });
            }
        });
    });
    var closeAdd = function () {
        parent.location.reload();//刷新父页面
    }
</script>
</body>
</html>
