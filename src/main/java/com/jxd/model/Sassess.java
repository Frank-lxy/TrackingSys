package com.jxd.model;

public class Sassess {
    private Integer saId;
    private Integer studentId;
    private Integer classId;
    private String state;
    private Double evaluate;
    private String assess;

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
}
