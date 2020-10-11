package com.vtb.hackathon.automarket.model;

public class CarModelModel {
    private boolean absentee;
    private String alias;
    private int id;
    private String prefix;
    private String title;
    private String titleRus;

    public CarModelModel(boolean absentee, String alias, int id, String prefix, String title, String titleRus) {
        this.absentee = absentee;
        this.alias = alias;
        this.id = id;
        this.prefix = prefix;
        this.title = title;
        this.titleRus = titleRus;
    }

    public boolean isAbsentee() {
        return absentee;
    }

    public void setAbsentee(boolean absentee) {
        this.absentee = absentee;
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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleRus() {
        return titleRus;
    }

    public void setTitleRus(String titleRus) {
        this.titleRus = titleRus;
    }
}
