<%@ page import="com.jxd.model.Student" %>
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
    <title>编辑学员</title>
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
                        <label class="layui-form-label">学员编号：</label>
                        <div class="layui-input-inline">
                            <input type="text" name="studentId" id="studentId" value="${student.studentId}" class="layui-input" readonly>
                        </div>
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>姓名：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="studentName" id="studentName" value="${student.studentName}" required  lay-verify="required" lay-reqtext="姓名不能为空" autocomplete="off" class="layui-input">
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>性别：</label>
                    <div class="layui-input-inline">
                        <c:if test="${student.sex == '男'}">
                            <input type="radio" name="sex" value="男" checked>男
                            <input type="radio" name="sex" value="女">女
                        </c:if>
                        <c:if test="${student.sex == '女'}">
                            <input type="radio" name="sex" value="男">男
                            <input type="radio" name="sex" value="女" checked>女
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
                            <input type="hidden" id="filePath" value="${student.photo}">
                            <div class="showPhoto" id="uploadDiv">
                                <c:if test="${student.photo == '' || student.photo == null}">
                                    <span style="align-content: center">学员照片</span>
                                </c:if>
                                <c:if test="${student.photo != '' && student.photo != null}">
                                    <img src="../../${student.photo}">
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="float: left">
                    <label class="layui-form-label"><span style="color: red">*</span>民族：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="nation" id="nation" value="${student.nation}" required  lay-verify="required" lay-reqtext="民族不能为空" autocomplete="off" class="layui-input">
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>出生年月：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="birthday" id="birthday" value="${student.birthday}" required lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div style="float: left">
                    <label class="layui-form-label"><span style="color: red">*</span>籍贯：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="homeTown" id="homeTown" value="${student.homeTown}" required lay-verify="required" lay-reqtext="籍贯不能为空" autocomplete="off" class="layui-input">
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>婚否：</label>
                    <div class="layui-input-inline">
                        <c:if test="${student.marriage == '未婚'}">
                            <input type="radio" name="marriage" value="未婚" checked>未婚
                            <input type="radio" name="marriage" value="已婚">已婚
                        </c:if>
                        <c:if test="${student.marriage == '已婚'}">
                            <input type="radio" name="marriage" value="未婚">未婚
                            <input type="radio" name="marriage" value="已婚" checked>已婚
                        </c:if>
                    </div>
                </div>
                <div style="float: left">
                    <label class="layui-form-label"><span style="color: red">*</span>联系电话：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="phone" id="phone" value="${student.phone}" required  lay-verify="phone" lay-reqtext="联系电话不能为空" autocomplete="off" class="layui-input">
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>身份证号：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="identityNum" id="identityNum" value="${student.identityNum}" required lay-verify="required" lay-reqtext="身份证号码不能为空" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div style="float: left">
                    <label class="layui-form-label">班期：</label>
                    <div class="layui-input-inline">
                        <select name="classId" id="classId" lay-filter="receive" lay-search="">
                            <option></option>
                            <c:forEach var="clazzList" items="${sessionScope.clazzList}">
                                <c:if test="${clazzList.classId == student.classId}">
                                    <option value="${clazzList.classId}" name="classId" selected>${clazzList.clazz}</option>
                                </c:if>
                                <c:if test="${clazzList.classId != student.classId}">
                                    <option value="${clazzList.classId}" name="classId">${clazzList.clazz}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>毕业学校：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="graduate" id="graduate" value="${student.graduate}" required lay-verify="required" lay-reqtext="毕业学校不能为空" autocomplete="off" class="layui-input">
                    </div>
                    <label class="layui-form-label"><span style="color: red">*</span>专业：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="major" id="major" value="${student.major}" required lay-verify="required" lay-reqtext="专业不能为空" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div style="float: left">
                    <%
                        String state = (String)request.getAttribute("state");
                        if ("1".equals(state)){
                    %>
                    <div id="checkDiv">
                        <label class="layui-form-label">项目经理：</label>
                        <div class="layui-input-inline">
                            <select name="managerId" id="managerId" lay-filter="receive" lay-search="">
                                <option></option>
                                <c:forEach var="managerList" items="${sessionScope.managerList}">
                                    <c:if test="${managerList.userId == student.managerId}">
                                        <option value="${managerList.userId}" name="classId" selected>${managerList.userName}</option>
                                    </c:if>
                                    <c:if test="${managerList.userId != student.managerId}">
                                        <option value="${managerList.userId}" name="classId">${managerList.userName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="layui-form-label">部门：</label>
                        <div class="layui-input-inline">
                            <select name="departmentId" id="departmentId" lay-filter="receive" lay-search="">
                                <option></option>
                                <c:forEach var="departmentList" items="${sessionScope.departmentList}">
                                    <c:if test="${departmentList.departmentId == student.departmentId}">
                                        <option value="${departmentList.departmentId}" name="classId" selected>${departmentList.departmentName}</option>
                                    </c:if>
                                    <c:if test="${departmentList.departmentId != student.departmentId}">
                                        <option value="${departmentList.departmentId}" name="classId">${departmentList.departmentName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="layui-form-label">职务：</label>
                        <div class="layui-input-inline">
                            <select name="jobId" id="jobId" lay-filter="receive" lay-search="">
                                <option></option>
                                <c:forEach var="jobList" items="${sessionScope.jobList}">
                                    <c:if test="${jobList.jobId == student.jobId}">
                                        <option value="${jobList.jobId}" name="classId" selected>${jobList.jobName}</option>
                                    </c:if>
                                    <c:if test="${jobList.jobId != student.jobId}">
                                        <option value="${jobList.jobId}" name="classId">${jobList.jobName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="layui-form-label">入职日期：</label>
                        <div class="layui-input-inline">
                            <input type="text" name="hiredate" id="hiredate" value="${student.hiredate}" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <%
                        }else {
                    %>
                    <div id="checkDiv1">
                        <label class="layui-form-label">项目经理：</label>
                        <div class="layui-input-inline">
                            <select name="managerId" id="managerId1">
                                <option value="">未完成全部课程</option>
                            </select>
                        </div>
                        <label class="layui-form-label">部门：</label>
                        <div class="layui-input-inline">
                            <select name="departmentId" id="departmentId1">
                                <option value="">未完成全部课程</option>
                            </select>
                        </div>
                        <label class="layui-form-label">职务：</label>
                        <div class="layui-input-inline">
                            <select name="jobId" id="jobId1">
                                <option value="">未完成全部课程</option>
                            </select>
                        </div>
                        <label class="layui-form-label">入职日期：</label>
                        <div class="layui-input-inline">
                            <input type="text" name="hiredate" id="hiredate1"  placeholder="未完成全部课程" autocomplete="off" class="layui-input" readonly>
                        </div>
                    </div>
                    <%
                        }
                    %>
                    <label class="layui-form-label">备注：</label>
                    <div class="layui-input-inline" style="width: 500px">
                        <input type="text" name="remarks" id="remarks" value="${student.remarks}" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-input-inline" style="margin-left: 45%;margin-bottom: 0px">
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
            ,laydate = layui.laydate;
        $ = layui.jquery;

        //监听日期组件
        laydate.render({
            elem: '#birthday',
            trigger: 'click'
        });
        laydate.render({
            elem: '#hiredate',
            trigger: 'click'
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
            var studentId = $("#studentId").val(),
                studentName = $("#studentName").val(),
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
                managerId = $("#managerId").val(),
                departmentId = $("#departmentId").val(),
                jobId = $("#jobId").val(),
                hiredate = $("#hiredate").val(),
                remarks = $("#remarks").val();
            if (studentName != '' && nation != '' && birthday != '' && homeTown != '' && phone != '' && graduate != '' && major != '' && identityNum != ''){
                $.ajax({
                    url:"editStudent",
                    type:"post",
                    data:{
                        studentId:studentId,
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
                        managerId:managerId,
                        departmentId:departmentId,
                        jobId:jobId,
                        hiredate:hiredate,
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
    };
</script>
</body>
</html>
