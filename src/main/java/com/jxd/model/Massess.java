package com.jxd.model;

public class Massess {
private Integer maId;
private Integer studentId;
private String  managerName;
private Integer departmentId;
private Integer jobId;
private Integer assessItemId;
private Double evaluate;
private String assess;

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

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getAssessItemId() {
        return assessItemId;
    }

    public void setAssessItemId(Integer assessItemId) {
        this.assessItemId = assessItemId;
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

    public Massess() {
    }

    public Massess(Integer studentId, String managerName, Integer departmentId, Integer jobId, Integer assessItemId, Double evaluate, String assess) {
        this.studentId = studentId;
        this.managerName = managerName;
        this.departmentId = departmentId;
        this.jobId = jobId;
        this.assessItemId = assessItemId;
        this.evaluate = evaluate;
        this.assess = assess;
    }

    public Massess(Integer maId, Integer studentId, String managerName, Integer departmentId, Integer jobId, Integer assessItemId, Double evaluate, String assess) {
        this.maId = maId;
        this.studentId = studentId;
        this.managerName = managerName;
        this.departmentId = departmentId;
        this.jobId = jobId;
        this.assessItemId = assessItemId;
        this.evaluate = evaluate;
        this.assess = assess;
    }
}
