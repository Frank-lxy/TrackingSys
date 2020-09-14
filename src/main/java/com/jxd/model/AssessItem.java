package com.jxd.model;

public class AssessItem {
    private Integer assessItemId;//评价项id
    private String assessItemName;//评价项名称

    public AssessItem() {
    }

    public AssessItem(String assessItemName) {
        this.assessItemName = assessItemName;
    }

    public AssessItem(Integer assessItemId, String assessItemName) {
        this.assessItemId = assessItemId;
        this.assessItemName = assessItemName;
    }

    public Integer getAssessItemId() {
        return assessItemId;
    }

    public void setAssessItemId(Integer assessItemId) {
        this.assessItemId = assessItemId;
    }

    public String getAssessItemName() {
        return assessItemName;
    }

    public void setAssessItemName(String assessItemName) {
        this.assessItemName = assessItemName;
    }
}
