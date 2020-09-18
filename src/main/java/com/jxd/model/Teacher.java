package com.jxd.model;

public class Teacher {
    private Integer teacherId;
    private String teacherName;
    private String birthday;
    private Integer userId;
    private String sex;
    private String phoneNumber;
    private String homeTown;
    private String idCardNum;
    private String photo;

    public Teacher(String teacherName, String birthday, String idCardNum, String phoneNumber, String sex, String homeTown, String photo, Integer userId) {

        this.teacherName = teacherName;
        this.birthday = birthday;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.homeTown = homeTown;
        this.idCardNum = idCardNum;
        this.photo = photo;
        this.userId=userId;
    }
    public Teacher(String teacherName, String birthday, String idCardNum, String phoneNumber, String sex, String homeTown, String photo) {

        this.teacherName = teacherName;
        this.birthday = birthday;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.homeTown = homeTown;
        this.idCardNum = idCardNum;
        this.photo = photo;
    }

    public Teacher() {
    }

    public Teacher(Integer teacherId, String teacherName, String birthday, String idCardNum, String phoneNumber, String sex, String homeTown, String photo) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.birthday = birthday;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.homeTown = homeTown;
        this.idCardNum = idCardNum;
        this.photo = photo;
    }
    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
