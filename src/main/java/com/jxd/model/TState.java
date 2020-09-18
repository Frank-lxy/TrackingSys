package com.jxd.model;

public class TState {
    private Integer tState;//工作阶段id
    private String stateName;//阶段名称

    public TState() {
    }

    public TState(Integer tState, String stateName) {
        this.tState = tState;
        this.stateName = stateName;
    }

    public Integer gettState() {
        return tState;
    }

    public void settState(Integer tState) {
        this.tState = tState;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
