package com.vtb.hackathon.automarket.model;

public class CalcResult {
    private double payment;
    private double loanAmount;
    private double subsidy;
    private double contractRate;
    private double lastPayment;

    public CalcResult(double payment, double loanAmount, double subsidy, double contractRate, double lastPayment) {
        this.payment = payment;
        this.loanAmount = loanAmount;
        this.subsidy = subsidy;
        this.contractRate = contractRate;
        this.lastPayment = lastPayment;
    }

    public CalcResult() {
        this(0, 0, 0, 0, 0);
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(double subsidy) {
        this.subsidy = subsidy;
    }

    public double getContractRate() {
        return contractRate;
    }

    public void setContractRate(double contractRate) {
        this.contractRate = contractRate;
    }

    public double getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(double lastPayment) {
        this.lastPayment = lastPayment;
    }
}
