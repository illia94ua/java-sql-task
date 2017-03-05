package com.acceptic.task.model;

import java.sql.Timestamp;
import java.util.Arrays;

public class User extends AbstractModel {

    private String username;
    private String email;
    private String password;
    private Timestamp createTime;
    private Category[] categories;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "User [username = " + username + ", email = " + email
                + ", password = " + password + ", createTime = " + createTime
                + ", categories = " + Arrays.toString(categories) + ", id = "
                + getId() + "]";
    }

}
