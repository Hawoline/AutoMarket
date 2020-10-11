package com.vtb.hackathon.automarket.model;

public class CarModel extends CarModelModel{
    private int specMetallicPay;
    private String photo;
    private int pearlPay;
    private int metallicPay;
    private TransportType transportType;
    private String ownTitle;
    private int minPrice;
    private int count;
    private int colorsCount;
    private String carId;

    public CarModel(boolean absentee, String alias, int id, String prefix, String title, String titleRus, int specMetallicPay, String photo, int pearlPay, int metallicPay, TransportType transportType, String ownTitle, int minPrice, int count, int colorsCount, String carId) {
        super(absentee, alias, id, prefix, title, titleRus);
        this.specMetallicPay = specMetallicPay;
        this.photo = photo;
        this.pearlPay = pearlPay;
        this.metallicPay = metallicPay;
        this.transportType = transportType;
        this.ownTitle = ownTitle;
        this.minPrice = minPrice;
        this.count = count;
        this.colorsCount = colorsCount;
        this.carId = carId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public int getColorsCount() {
        return colorsCount;
    }

    public void setColorsCount(int colorsCount) {
        this.colorsCount = colorsCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMetallicPay() {
        return metallicPay;
    }

    public void setMetallicPay(int metallicPay) {
        this.metallicPay = metallicPay;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public String getOwnTitle() {
        return ownTitle;
    }

    public void setOwnTitle(String ownTitle) {
        this.ownTitle = ownTitle;
    }

    public int getPearlPay() {
        return pearlPay;
    }

    public void setPearlPay(int pearlPay) {
        this.pearlPay = pearlPay;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getSpecMetallicPay() {
        return specMetallicPay;
    }

    public void setSpecMetallicPay(int specMetallicPay) {
        this.specMetallicPay = specMetallicPay;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }
}
