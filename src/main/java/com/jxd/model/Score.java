package com.jxd.model;

public class Score {
    private Integer studentId;//学员id
    private Integer courseId;//课程id
    private Double score;//课程分数

    public Score() {
    }

    public Score(Double score) {
        this.score = score;
    }

    public Score(Integer studentId, Integer courseId, Double score) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.score = score;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
