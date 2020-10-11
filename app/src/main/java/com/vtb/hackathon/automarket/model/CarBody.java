package com.vtb.hackathon.automarket.model;

public class CarBody {
    private String alias;
    private int doors;
    private String photo;
    private String title;
    private String type;
    private String typeTitle;

    public CarBody(String alias, int doors, String photo, String title, String type, String typeTitle) {
        this.alias = alias;
        this.doors = doors;
        this.photo = photo;
        this.title = title;
        this.type = type;
        this.typeTitle = typeTitle;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }
}
