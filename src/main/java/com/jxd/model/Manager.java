package com.jxd.model;

public class Manager {
    private Integer managerId;
    private String departmentName;
    private String managerName;
    private String birthday;
    private Integer departmentId;
    private Integer userId;
    private String sex;
    private String phoneNumber;
    private String homeTown;
    private String idCardNum;
    private String photo;

    public Manager() {
    }

    public Manager(Integer managerId, String departmentName, String managerName, String birthday, Integer departmentId, Integer userId, String sex, String phoneNumber, String homeTown, String idCardNum, String photo) {
        this.managerId = managerId;
        this.departmentName = departmentName;
        this.managerName = managerName;
        this.birthday = birthday;
        this.departmentId = departmentId;
        this.userId = userId;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.homeTown = homeTown;
        this.idCardNum = idCardNum;
        this.photo = photo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Manager(String managerName, String departmentName, String birthday, String idCardNum, String phoneNumber, String sex, String homeTown, String photo, Integer userId) {
        this.departmentName = departmentName;
        this.managerName = managerName;
        this.birthday = birthday;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.homeTown = homeTown;
        this.idCardNum = idCardNum;
        this.photo = photo;
        this.userId=userId;
    }
    public Manager(String managerName, String departmentName, String birthday, String idCardNum, String phoneNumber, String sex, String homeTown, String photo) {
        this.departmentName = departmentName;
        this.managerName = managerName;
        this.birthday = birthday;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.homeTown = homeTown;
        this.idCardNum = idCardNum;
        this.photo = photo;

    }

    public Manager(Integer managerId,String managerName, String departmentName, String birthday, String idCardNum, String phoneNumber, String sex, String homeTown, String photo) {
        this.managerId = managerId;
        this.departmentName = departmentName;
        this.managerName = managerName;
        this.birthday = birthday;
        this.userId = userId;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.homeTown = homeTown;
        this.idCardNum = idCardNum;
        this.photo = photo;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
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



    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }
}
