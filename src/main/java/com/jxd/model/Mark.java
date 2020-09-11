package com.jxd.model;

public class Mark {
    private Integer markId;
    private Integer studentId;
    private Integer assessItemId;
    private Integer stageId;
    private Double mark;

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

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Mark() {
    }

    public Mark(Integer studentId, Integer assessItemId, Integer stageId, Double mark) {
        this.studentId = studentId;
        this.assessItemId = assessItemId;
        this.stageId = stageId;
        this.mark = mark;
    }

    public Mark(Integer markId, Integer studentId, Integer assessItemId, Integer stageId, Double mark) {
        this.markId = markId;
        this.studentId = studentId;
        this.assessItemId = assessItemId;
        this.stageId = stageId;
        this.mark = mark;
    }
}
