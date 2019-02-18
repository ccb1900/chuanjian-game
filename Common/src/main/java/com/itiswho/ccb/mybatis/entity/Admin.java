package com.itiswho.ccb.mybatis.entity;

public class Admin {
    private int id;
    private String username;
    private String password;
    private String name;
    private String login_sign_token;
    private String note;
    private String deleted_at;
    private String created_at;
    private String updated_at;

    public Admin() {
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getLogin_sign_token() {
        return login_sign_token;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public String getUsername() {
        return username;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin_sign_token(String login_sign_token) {
        this.login_sign_token = login_sign_token;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
