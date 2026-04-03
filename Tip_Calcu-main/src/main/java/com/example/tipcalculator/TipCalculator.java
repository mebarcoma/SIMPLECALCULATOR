package com.example.tipcalculator;

public class TipCalculator {
    double amount;
    double tipAmount;


    public TipCalculator(){

    }

    public double calculateTip(double tip, double amount){
        this.amount = amount;
        this.tipAmount = this.amount * tip;
        return  this.tipAmount;
    }
}