package com.jxd.model;

public class Massess {
    private Integer maId;
    private Integer studentId;
    private Double evaluate;
    private String assess;
    private Integer state;
    private Integer tState;

    public Massess() {
    }

    public Massess(Integer maId, Integer studentId, Double evaluate, String assess, Integer state, Integer tState) {
        this.maId = maId;
        this.studentId = studentId;
        this.evaluate = evaluate;
        this.assess = assess;
        this.state = state;
        this.tState = tState;
    }

    public Integer gettState() {
        return tState;
    }

    public void settState(Integer tState) {
        this.tState = tState;
    }

    public Integer getMaId() {
        return maId;
    }

    public void setMaId(Integer maId) {
        this.maId = maId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
