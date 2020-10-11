package com.vtb.hackathon.automarket.model;

public class SizesPhotos {
    private String width250;
    private String width220;

    public SizesPhotos(String width250, String width220) {
        this.width250 = width250;
        this.width220 = width220;
    }

    public String getWidth250() {
        return width250;
    }

    public void setWidth250(String width250) {
        this.width250 = width250;
    }

    public String getWidth220() {
        return width220;
    }

    public void setWidth220(String width220) {
        this.width220 = width220;
    }
}
