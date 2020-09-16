package com.jxd.model;

public class Sassess {
    private Integer saId;//学校评价id
    private Integer studentId;//学员id
    private Integer classId;//班期id
    private String state;//状态
    private Double evaluate;//整体评价分数
    private String assess;//评价

    public Sassess() {
    }

    public Sassess(Integer studentId, Integer classId, String state, Double evaluate, String assess) {
        this.studentId = studentId;
        this.classId = classId;
        this.state = state;
        this.evaluate = evaluate;
        this.assess = assess;
    }

    public Sassess(Integer saId, Integer studentId, Integer classId, String state, Double evaluate, String assess) {
        this.saId = saId;
        this.studentId = studentId;
        this.classId = classId;
        this.state = state;
        this.evaluate = evaluate;
        this.assess = assess;
    }

    public Integer getSaId() {
        return saId;
    }

    public void setSaId(Integer saId) {
        this.saId = saId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Double evaluate) {
        this.evaluate = evaluate;
    }

    public String getAssess() {
        return assess;
    }

    public void setAssess(String assess) {
        this.assess = assess;
    }
}
