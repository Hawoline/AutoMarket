package com.vtb.hackathon.automarket.model;

public class Country {
    private String code;
    private int id;
    private String title;

    public Country(String code, int id, String title) {
        this.code = code;
        this.id = id;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
