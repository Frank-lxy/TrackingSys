package com.jxd.model;

public class Stage {
    private Integer stageId;
    private String stageName;

    public Stage() {
    }

    public Stage(String stageName) {
        this.stageName = stageName;
    }

    public Stage(Integer stageId, String stageName) {
        this.stageId = stageId;
        this.stageName = stageName;
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
}
