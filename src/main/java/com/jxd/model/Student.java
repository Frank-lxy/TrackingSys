package com.jxd.model;

public class Student {
    private Integer studentId;//学员id
    private String studentName;//学员姓名
    private String sex;//性别
    private String nation;//民族
    private String birthday;//出生年月
    private String homeTown;//籍贯
    private String marriage;//婚否
    private String phone;//联系电话
    private String identityNum;//身份证号码
    private String graduate;//毕业院校
    private String major;//专业
    private String photo;//照片
    private Integer classId;//班期id
    private Integer managerId;//项目经理id
    private Integer jobId;//职务id
    private Integer departmentId;//部门id
    private String hiredate;//入职日期
    private String remarks;//备注
    private String tState;

    public Student() {
    }

    public Student(Integer studentId, String studentName, String sex, String homeTown, String phone, String graduate, String major) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.sex = sex;
        this.homeTown = homeTown;
        this.phone = phone;
        this.graduate = graduate;
        this.major = major;
    }

    public Student(String studentName, String sex, String nation, String birthday, String homeTown, String marriage, String phone, String identityNum, String graduate, String major, String photo, String remarks) {
        this.studentName = studentName;
        this.sex = sex;
        this.nation = nation;
        this.birthday = birthday;
        this.homeTown = homeTown;
        this.marriage = marriage;
        this.phone = phone;
        this.identityNum = identityNum;
        this.graduate = graduate;
        this.major = major;
        this.photo = photo;
        this.remarks = remarks;
    }

    public Student(String studentName, String sex, String nation, String birthday, String homeTown, String marriage, String phone, String identityNum, String graduate, String major, String photo, Integer classId, Integer managerId, String hiredate, String remarks) {
        this.studentName = studentName;
        this.sex = sex;
        this.nation = nation;
        this.birthday = birthday;
        this.homeTown = homeTown;
        this.marriage = marriage;
        this.phone = phone;
        this.identityNum = identityNum;
        this.graduate = graduate;
        this.major = major;
        this.photo = photo;
        this.classId = classId;
        this.managerId = managerId;
        this.hiredate = hiredate;
        this.remarks = remarks;
    }

    public Student(Integer studentId, String studentName, String sex, String nation, String birthday, String homeTown, String marriage, String phone, String identityNum, String graduate, String major, String photo, Integer classId, Integer managerId, String hiredate, String remarks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.sex = sex;
        this.nation = nation;
        this.birthday = birthday;
        this.homeTown = homeTown;
        this.marriage = marriage;
        this.phone = phone;
        this.identityNum = identityNum;
        this.graduate = graduate;
        this.major = major;
        this.photo = photo;
        this.classId = classId;
        this.managerId = managerId;
        this.hiredate = hiredate;
        this.remarks = remarks;
    }

    public Student(Integer studentId, String studentName, String sex, String nation, String birthday, String homeTown, String marriage, String phone, String identityNum, String graduate, String major, String photo, Integer classId, Integer managerId, Integer jobId, Integer departmentId, String hiredate, String remarks, String tState) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.sex = sex;
        this.nation = nation;
        this.birthday = birthday;
        this.homeTown = homeTown;
        this.marriage = marriage;
        this.phone = phone;
        this.identityNum = identityNum;
        this.graduate = graduate;
        this.major = major;
        this.photo = photo;
        this.classId = classId;
        this.managerId = managerId;
        this.jobId = jobId;
        this.departmentId = departmentId;
        this.hiredate = hiredate;
        this.remarks = remarks;
        this.tState = tState;
    }

    public Student(Integer studentId, String studentName, String sex, String nation, String birthday, String homeTown, String marriage, String phone, String identityNum, String graduate, String major, String photo, Integer classId, Integer managerId, Integer jobId, Integer departmentId, String hiredate, String remarks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.sex = sex;
        this.nation = nation;
        this.birthday = birthday;
        this.homeTown = homeTown;
        this.marriage = marriage;
        this.phone = phone;
        this.identityNum = identityNum;
        this.graduate = graduate;
        this.major = major;
        this.photo = photo;
        this.classId = classId;
        this.managerId = managerId;
        this.jobId = jobId;
        this.departmentId = departmentId;
        this.hiredate = hiredate;
        this.remarks = remarks;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String gettState() {
        return tState;
    }

    public void settState(String tState) {
        this.tState = tState;
    }
}
