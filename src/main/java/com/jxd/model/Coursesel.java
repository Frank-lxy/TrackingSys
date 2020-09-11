package com.jxd.model;

public class Coursesel {
    private Integer courseId;
    private Integer classId;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Coursesel() {
    }

    public Coursesel(Integer classId) {
        this.classId = classId;
    }

    public Coursesel(Integer courseId, Integer classId) {
        this.courseId = courseId;
        this.classId = classId;
    }
}
