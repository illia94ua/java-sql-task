package com.acceptic.task.rest.controller;

import java.util.List;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.acceptic.task.facade.UserFacade;
import com.acceptic.task.model.User;
import com.opensymphony.xwork2.ModelDriven;

public class UserController implements ModelDriven<Object> {

    private User user;
    private List<User> usersList;
    private String id;

    private UserFacade userFacade;

    public UserController() {
        user = new User();
        userFacade = new UserFacade();
    }

    public HttpHeaders index() {
        usersList = userFacade.getAllUsers();
        return new DefaultHttpHeaders("index").disableCaching();
    }

    public HttpHeaders show() {
        usersList = userFacade.getUserById(Integer.parseInt(id));
        return new DefaultHttpHeaders("show").disableCaching();
    }

    public HttpHeaders create() {
        usersList = userFacade.createUser(user);
        return new DefaultHttpHeaders("create");
    }

    public HttpHeaders update() {
        user.setId(Integer.parseInt(id));
        usersList = userFacade.updateUser(user);
        return new DefaultHttpHeaders("update");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getModel() {
        return usersList != null ? usersList : user;
    }

}
