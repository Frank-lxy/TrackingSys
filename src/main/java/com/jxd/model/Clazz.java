package com.jxd.model;

public class Clazz {
    private Integer classId;//班期id
    private String clazz;//班期名称
    private String teacherName;//授课教师名称

    public Clazz() {
    }

    public Clazz(String clazz, String teacherName) {
        this.clazz = clazz;
        this.teacherName = teacherName;
    }

    public Clazz(Integer classId, String clazz, String teacherName) {
        this.classId = classId;
        this.clazz = clazz;
        this.teacherName = teacherName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
