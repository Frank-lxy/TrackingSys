package com.jxd.model;

public class UserInfo {
    private Integer userId;
    private String userName;
    private String password;
    private String Character;

    public UserInfo(Integer userId, String userName, String password, String character) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        Character = character;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCharacter() {
        return Character;
    }

    public void setCharacter(String character) {
        Character = character;
    }
}
