package com.example.adminapp.dto;

public class CategoryRequest {
    private String name;

    public CategoryRequest(String name) {
        this.name = name;
    }
    // getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
