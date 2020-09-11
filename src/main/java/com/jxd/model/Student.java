package com.jxd.model;

public class Student {
    private Integer studentId;
    private String studentName;
    private String sex;
    private String nation;
    private String birthday;
    private String homeTown;
    private String marriage;
    private String phone;
    private String identityNum;
    private String graduate;
    private String major;
    private String photo;
    private Integer classId;
    private Integer managerId;
    private String hiredate;
    private String remarks;

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

    public Student() {
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
}
