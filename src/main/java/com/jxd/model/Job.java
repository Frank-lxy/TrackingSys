package com.jxd.model;

public class Job {
    private Integer jobId;//职务id
    private String jobName;//职务名称

    public Job() {
    }

    public Job(String jobName) {
        this.jobName = jobName;
    }

    public Job(Integer jobId, String jobName) {
        this.jobId = jobId;
        this.jobName = jobName;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
