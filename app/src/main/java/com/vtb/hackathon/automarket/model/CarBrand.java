package com.vtb.hackathon.automarket.model;

public class CarBrand extends CarBrandOnly {
    private int currentCarCount;
    private int currentModelsTotal;
    private String[] generations;
    private CarModel[] models;

    public CarBrand(boolean absentee, String alias, Country country, int currentCarCount,
                    int currentModelsTotal, String[] generations, int id, boolean isOutOfBound,
                    String logo, CarModel[] models, String title, String titleRus) {
        super(absentee, alias, country, logo, isOutOfBound, id, title, titleRus);
        this.currentCarCount = currentCarCount;
        this.currentModelsTotal = currentModelsTotal;
        this.generations = generations;
        this.models = models;
    }

    public int getCurrentCarCount() {
        return currentCarCount;
    }

    public void setCurrentCarCount(int currentCarCount) {
        this.currentCarCount = currentCarCount;
    }

    public int getCurrentModelsTotal() {
        return currentModelsTotal;
    }

    public void setCurrentModelsTotal(int currentModelsTotal) {
        this.currentModelsTotal = currentModelsTotal;
    }

    public String[] getGenerations() {
        return generations;
    }

    public void setGenerations(String[] generations) {
        this.generations = generations;
    }

    public CarModel[] getModels() {
        return models;
    }

    public void setModels(CarModel[] models) {
        this.models = models;
    }

}
