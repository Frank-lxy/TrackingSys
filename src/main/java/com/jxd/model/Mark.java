package com.jxd.model;

public class Mark {
    private Integer markId;//评分表
    private Integer studentId;//学员id
    private Integer assessItemId;//评分项id
    private Integer tState;//阶段id
    private Double mark;//评分

    public Mark() {
    }

    public Mark(Integer studentId, Integer assessItemId, Integer tState, Double mark) {
        this.studentId = studentId;
        this.assessItemId = assessItemId;
        this.tState = tState;
        this.mark = mark;
    }

    public Mark(Integer markId, Integer studentId, Integer assessItemId, Integer tState, Double mark) {
        this.markId = markId;
        this.studentId = studentId;
        this.assessItemId = assessItemId;
        this.tState = tState;
        this.mark = mark;
    }

    public Integer getMarkId() {
        return markId;
    }

    public void setMarkId(Integer markId) {
        this.markId = markId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getAssessItemId() {
        return assessItemId;
    }

    public void setAssessItemId(Integer assessItemId) {
        this.assessItemId = assessItemId;
    }

    public Integer gettState() {
        return tState;
    }

    public void settState(Integer tState) {
        this.tState = tState;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }
}
