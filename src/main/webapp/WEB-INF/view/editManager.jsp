<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xzh
  Date: 2020/9/18
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑经理信息</title>
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
        img{
            height: 170px;
        }
        .showPhoto{
            border: solid 1px #eaeaea;
            margin: 15px 15px 10px 0;
            height: 170px;
            width: 110px;
        }
        #checkDiv1{
            color: #9F9F9F;
        }
    </style>
</head>
<body>
<div style="margin: 15px 0 0 0">
    <div style="display: flex;justify-content: center">
        <div class="layui-form">
            <div class="layui-form-item">
                <div>
                    <div style="display: none">
                        <label class="layui-form-label">经理编号：</label>
                        <div class="layui-input-inline">
                            <input type="text" name="managerId" id="managerId" value="${managerId}" class="layui-input" readonly>
                        </div>
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>姓名：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="managerName" id="managerName" value="${managerName}" required  lay-verify="required" lay-reqtext="姓名不能为空" autocomplete="off" class="layui-input">
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>性别：</label>
                    <div class="layui-input-inline">
                        <c:if test="${sex=='男'}">
                            <input type="radio" name="sex" value="男" checked>男
                            <input type="radio" name="sex" value="女">女
                        </c:if>
                        <c:if test="${sex== '女'}">
                            <input type="radio" name="sex" value="男">男
                            <input type="radio" name="sex" value="女" checked>女
                        </c:if>
                        <c:if test="${sex==''||sex==null}">
                            <input type="radio" name="sex" value="男" checked>男
                            <input type="radio" name="sex" value="女">女
                        </c:if>
                    </div>
                </div>
                <div style="height: 230px;width: 310px;float: right;margin-right: 20px">
                    <div style="margin-left: 0px">
                        <label class="layui-form-label" style="margin-left: 0px">照片：</label>
                        <div class="layui-input-inline">
                            <form id="uploadForm" method="post" enctype="multipart/form-data">
                                <input type="file" id="photo" name="photo" style="display: none">
                                <button type="button" class="layui-btn" id="updatePhoto">重传</button>
                            </form>
                            <input type="hidden" id="filePath" value="${photo}">
                            <div class="showPhoto" id="uploadDiv">
                                <c:if test="${photo == '' || photo == null}">
                                    <span style="align-content: center">教师照片</span>
                                </c:if>
                                <c:if test="${photo != null}">
                                    <img src="../../${photo}">
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="float: left">
                    <label class="layui-form-label"><span style="color: red">*</span>出生年月：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="birthday" id="birthday" value="${birthday}" required lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>部门：</label>
                    <div class="layui-input-inline">
                        <select name="departmentName" lay-verify="" id="departmentName">
                            <option value="${department}">${department}</option>
                            <c:forEach var="departmentName" items="${departmentName}">
                                <option value="${departmentName}">${departmentName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div style="float: left">
                    <label class="layui-form-label"><span style="color: red">*</span>籍贯：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="homeTown" id="homeTown" value="${homeTown}" required lay-verify="required" lay-reqtext="籍贯不能为空" autocomplete="off" class="layui-input">
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>联系电话：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="phoneNumber" id="phoneNumber" value="${phoneNumber}" required  lay-verify="phone" lay-reqtext="联系电话不能为空" autocomplete="off" class="layui-input">
                    </div>
                </div>
                    <div style="float: left">
                    <label class="layui-form-label"><span style="color: red">*</span>身份证号：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="idCardNum" id="idCardNum" value="${idCardNum}" required lay-verify="required" lay-reqtext="身份证号码不能为空" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div style="float: left">
                <div class="layui-input-inline" style="margin-left: 45%;margin-bottom: 0px">
                    <button id="addCourse" class="layui-btn" lay-submit lay-filter="demo1">提交</button>
                </div>
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
            ,laydate = layui.laydate;
        $ = layui.jquery;

        //监听日期组件
        laydate.render({
            elem: '#birthday',
            trigger: 'click'
        });

        //失去焦点时验证身份证号
        $("#idCardNum").blur(function () {
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
            var path = $("#filePath").val();
            var isDel = '';
            if(path != null && path != ''){
                $.ajax({
                    url:"delFile",
                    type:"post",
                    data:{
                        path:path
                    },
                    success:function (data) {
                        isDel = data;
                    },
                    error:function (data) {
                        isDel = "删除失败";
                    }
                });
            }
            if ("删除失败" != isDel){
                //使用FormData实现ajax的文件提交
                var formData = new FormData($("#uploadForm")[0]);
                $.ajax({
                    url:"uploadFile",
                    type:"post",
                    data:formData,
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
                        layer.msg("执行失败")
                    }
                })
            }else {
                layer.msg("重传失败")
            }
        });
        /*点击提交按钮*/
        $("#addCourse").click(function () {
            var managerId = $("#managerId").val(),
                managerName = $("#managerName").val(),
                sex = $("input[name='sex']:checked").val(),
                birthday = $("#birthday").val(),
                homeTown = $("#homeTown").val(),
                phoneNumber = $("#phoneNumber").val(),
                idCardNum = $("#idCardNum").val(),
                photo = $("#filePath").val(),
                departmentName = $("#departmentName").val();
            if (managerName != '' && birthday != '' && homeTown != '' && phoneNumber != ''  && idCardNum != ''){
                $.ajax({
                    url:"editTheManager",
                    type:"post",
                    data:{
                        managerId:managerId,
                        managerName:managerName,
                        sex:sex,
                        birthday:birthday,
                        homeTown:homeTown,
                        phoneNumber:phoneNumber,
                        idCardNum:idCardNum,
                        photo:photo,
                        departmentName:departmentName

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
    };
</script>
</body>
</html>
