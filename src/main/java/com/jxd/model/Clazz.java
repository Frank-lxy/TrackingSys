package com.jxd.model;

public class Clazz {
    private Integer classId;
    private String clazz;
    private String teacherName;

    public Clazz(String clazz, String teacherName) {
        this.clazz = clazz;
        this.teacherName = teacherName;
    }

    public Clazz(Integer classId, String clazz, String teacherName) {
        this.classId = classId;
        this.clazz = clazz;
        this.teacherName = teacherName;
    }

    public Clazz() {
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
