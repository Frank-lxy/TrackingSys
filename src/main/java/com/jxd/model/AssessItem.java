package com.jxd.model;

public class AssessItem {
    private Integer assessItemId;
    private String assessItemName;

    public AssessItem() {
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
