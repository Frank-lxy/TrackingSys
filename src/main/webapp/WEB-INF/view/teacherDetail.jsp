<%--
  Created by IntelliJ IDEA.
  User: xzh
  Date: 2020/9/18
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师详细信息</title>
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

        #checkDiv1 {
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
                            <input type="text" name="teacherId" id="teacherId" value="${teacherId}" class="layui-input"
                                   readonly>
                        </div>
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>姓名：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="teacherName" id="teacherName" value="${teacherName}" required
                               lay-verify="required" lay-reqtext="姓名不能为空" autocomplete="off" class="layui-input"
                               readonly>
                    </div>
                </div>
                <div style="float: left">
                    <label class="layui-form-label"><span style="color: red">*</span>性别：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="sex" id="sex" value="${sex}" required lay-verify="required"
                               lay-reqtext="籍贯不能为空" autocomplete="off" class="layui-input" readonly>
                    </div>
                </div>
                <div style="float: left">
                    <label class="layui-form-label"><span style="color: red">*</span>出生年月：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="birthday" id="birthday" value="${birthday}" required lay-verify="date"
                               placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" readonly>
                    </div>
                </div>
                    <div style="float: left">
                        <label class="layui-form-label"><span style="color: red">*</span>籍贯：</label>
                        <div class="layui-input-inline">
                            <input type="text" name="homeTown" id="homeTown" value="${homeTown}" required
                                   lay-verify="required" lay-reqtext="籍贯不能为空" autocomplete="off" class="layui-input"
                                   readonly>
                        </div>
                    </div>
                    <div style="float: left">
                        <label class="layui-form-label"><span style="color: red">*</span>联系电话：</label>
                        <div class="layui-input-inline">
                            <input type="text" name="phoneNumber" id="phoneNumber" value="${phoneNumber}" required
                                   lay-verify="phone" lay-reqtext="联系电话不能为空" autocomplete="off" class="layui-input"
                                   readonly>
                        </div>
                        <label class="layui-form-label"><span style="color: red">*</span>身份证号：</label>
                        <div class="layui-input-inline">
                            <input type="text" name="idCardNum" id="idCardNum" value="${idCardNum}" required
                                   lay-verify="required" lay-reqtext="身份证号码不能为空" autocomplete="off" class="layui-input"
                                   readonly>
                        </div>
                    </div>
                    <div style="margin-left: 0px">
                        <label class="layui-form-label" style="margin-left: 0px"></label>
                        <div class="layui-input-inline">
                            <input type="hidden" id="filePath" value="${photo}" readonly>
                            <div class="showPhoto" id="uploadDiv" readonly>
                                <c:if test="${photo == '' || photo == null}">
                                    <span style="align-content: center">教师照片</span>
                                </c:if>
                                <c:if test="${photo != '' || photo != null}">
                                    <img src="../../${photo}">
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
