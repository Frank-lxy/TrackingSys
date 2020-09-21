<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/13
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增学员</title>
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
        .layui-btn {
            height: 28px;
            line-height: 28px;
        }
        img{
            height: 170px;
        }
        .showPhoto{
            border: solid 1px #eaeaea;
            margin: 15px 15px 10px 0;
            height: 170px;
            width: 110px;
        }
        .layui-laydate-main{
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
                        <input type="text" name="studentName" id="studentName" required  lay-verify="required" lay-reqtext="姓名不能为空" autocomplete="off" class="layui-input">
                    </div><label class="layui-form-label"><span style="color: red">*</span>性别：</label>
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
                    <label class="layui-form-label"><span style="color: red">*</span>民族：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="nation" id="nation" required  lay-verify="required" lay-reqtext="民族不能为空" autocomplete="off" class="layui-input">
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>出生年月：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="birthday" id="birthday" required lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div style="float: left">
                    <label class="layui-form-label"><span style="color: red">*</span>籍贯：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="homeTown" id="homeTown" required lay-verify="required" lay-reqtext="籍贯不能为空" autocomplete="off" class="layui-input">
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>婚否：</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="marriage" value="未婚" checked>未婚
                        <input type="radio" name="marriage" value="已婚">已婚
                    </div>
                </div>
                <div style="float: left">
                    <label class="layui-form-label"><span style="color: red">*</span>联系电话：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="phone" id="phone" required  lay-verify="phone" lay-reqtext="联系电话不能为空" autocomplete="off" class="layui-input">
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>身份证号：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="identityNum" id="identityNum" required lay-verify="required" lay-reqtext="身份证号码不能为空" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div style="float: left">
                    <label class="layui-form-label">班期：</label>
                    <div class="layui-input-inline">
                        <select name="classId" id="classId" lay-filter="receive" lay-search="">
                            <option></option>
                            <c:forEach var="clazzList" items="${clazzList}">
                                <option value="${clazzList.classId}" name="className">${clazzList.clazz}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>毕业学校：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="graduate" id="graduate" required lay-verify="required" lay-reqtext="毕业学校不能为空"  autocomplete="off" class="layui-input">
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>专业：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="major" id="major" required lay-verify="required" lay-reqtext="专业不能为空" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <label class="layui-form-label">备注：</label>
                <div class="layui-input-inline" style="width: 790px">
                    <input type="text" name="remarks" id="remarks" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-input-inline" style="margin-left: 45%;margin-bottom: 0;margin-top: 10px">
                    <button id="addCourse" class="layui-btn" lay-submit lay-filter="demo1">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['layer','form','upload','laydate'],function () {
        var layer = layui.layer
            ,form = layui.form
            ,upload = layui.upload
            ,laydate = layui.laydate
            ,date = new Date();
        $ = layui.jquery;

        //监听出生日期
        laydate.render({
            elem: '#birthday',
            trigger: 'click',
            max: 'date'
        });

        //失去焦点时验证身份证号
        $("#identityNum").blur(function () {
            var reg = /^\d{14}(\d|X|x)$|^\d{17}(\d|X|x)$/;
            if (!reg.test($(this).val())){
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
            var newFormData = new FormData($("#uploadForm")[0]);
            var filesLength = document.querySelector('#photo').files.length;
            if (filesLength != 0){
                $.ajax({
                    url:"uploadFile",
                    type:"post",
                    data:newFormData,
                    cache:false,//是否缓存
                    //即告诉服务器，从浏览器提交过来的数据采用默认的数据格式
                    contentType:false,
                    //设定为false可避免jQuery对formData的默认处理
                    processData:false,
                    success:function (data) {
                        var img = "<img src='../../" + data + "'>";
                        $("#uploadDiv").html(img);
                        $("#filePath").val(data);
                    },
                    error:function (data) {
                        alert("执行失败")
                    }
                })
            }
        });
        /*点击提交按钮*/
        $("#addCourse").click(function () {
            var studentName = $("#studentName").val(),
                sex = $("input[name='sex']:checked").val(),
                nation = $("#nation").val(),
                birthday = $("#birthday").val(),
                homeTown = $("#homeTown").val(),
                marriage = $("input[name='marriage']:checked").val(),
                phone = $("#phone").val(),
                identityNum = $("#identityNum").val(),
                graduate = $("#graduate").val(),
                major = $("#major").val(),
                photo = $("#filePath").val(),
                classId = $("#classId").val(),
                remarks = $("#remarks").val();
            if (studentName != '' && nation != '' && birthday != '' && homeTown != '' && phone != '' && graduate != '' && major != '' && identityNum != ''){
                $.ajax({
                    url:"addStudent",
                    type:"post",
                    data:{
                        studentName:studentName,
                        sex:sex,
                        nation:nation,
                        birthday:birthday,
                        homeTown:homeTown,
                        marriage:marriage,
                        phone:phone,
                        identityNum:identityNum,
                        graduate:graduate,
                        major:major,
                        photo:photo,
                        classId:classId,
                        remarks:remarks
                    },
                    dataType:"text",
                    success:function (data) {
                        layer.msg(data);
                        setTimeout('closeAdd()',1000);
                    },
                    error:function (data) {
                        layer.msg("执行失败");
                        setTimeout('closeAdd()',1000);
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
