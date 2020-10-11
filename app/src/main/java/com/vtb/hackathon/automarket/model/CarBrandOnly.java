package com.vtb.hackathon.automarket.model;

public class CarBrandOnly {
    private boolean absentee;
    private String alias;
    private Country country;
    private String logo;
    private boolean isOutOfBound;
    private int id;
    private String title;
    private String titleRus;

    public CarBrandOnly(boolean absentee, String alias, Country country, String logo, boolean isOutOfBound, int id, String title, String titleRus) {
        this.absentee = absentee;
        this.alias = alias;
        this.country = country;
        this.logo = logo;
        this.isOutOfBound = isOutOfBound;
        this.id = id;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean isOutOfBound() {
        return isOutOfBound;
    }

    public void setOutOfBound(boolean outOfBound) {
        isOutOfBound = outOfBound;
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

    public String getTitleRus() {
        return titleRus;
    }

    public void setTitleRus(String titleRus) {
        this.titleRus = titleRus;
    }
}
