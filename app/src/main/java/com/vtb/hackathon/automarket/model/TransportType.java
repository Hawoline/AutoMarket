package com.vtb.hackathon.automarket.model;

public class TransportType {
    private String alias;
    private int id;
    private String title;

    public TransportType(String alias, int id, String title) {
        this.alias = alias;
        this.id = id;
        this.title = title;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
