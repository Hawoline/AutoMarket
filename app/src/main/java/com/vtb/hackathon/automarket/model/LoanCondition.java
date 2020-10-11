package com.vtb.hackathon.automarket.model;

public class LoanCondition {
    private String[] clientTypes;
    private double cost;
    private double initialFee;
    private int kaskoValue;
    private String language;
    private double residualPayment;
    private String settingsName;
    private String[] specialConditions;
    private int term;

    public LoanCondition() {
        this(new String[0],
                850_000,
                200000,
                0,
                "en",
                0,
                "Haval",
                new String[0],
                5
        );
    }
    public LoanCondition(String[] clientTypes, double cost, double initialFee, int kaskoValue, String language, double residualPayment, String settingsName, String[] specialConditions, int term) {
        this.clientTypes = clientTypes;
        this.cost = cost;
        this.initialFee = initialFee;
        this.kaskoValue = kaskoValue;
        this.language = language;
        this.residualPayment = residualPayment;
        this.settingsName = settingsName;
        this.specialConditions = specialConditions;
        this.term = term;
    }

    public String[] getClientTypes() {
        return clientTypes;
    }

    public void setClientTypes(String[] clientTypes) {
        this.clientTypes = clientTypes;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getInitialFee() {
        return initialFee;
    }

    public void setInitialFee(double initialFee) {
            this.initialFee = initialFee;
    }

    public int getKaskoValue() {
        return kaskoValue;
    }

    public void setKaskoValue(int kaskoValue) {
            this.kaskoValue = kaskoValue;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getResidualPayment() {
        return residualPayment;
    }

    public void setResidualPayment(double residualPayment) {
        this.residualPayment = residualPayment;
    }

    public String getSettingsName() {
        return settingsName;
    }

    public void setSettingsName(String settingsName) {
        this.settingsName = settingsName;
    }

    public String[] getSpecialConditions() {
        return specialConditions;
    }

    public void setSpecialConditions(String[] specialConditions) {
        this.specialConditions = specialConditions;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }
}
