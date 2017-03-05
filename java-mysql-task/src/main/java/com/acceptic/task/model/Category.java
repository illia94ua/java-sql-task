package com.acceptic.task.model;

public class Category extends AbstractModel {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category [name = " + name + "]";
    }

}
