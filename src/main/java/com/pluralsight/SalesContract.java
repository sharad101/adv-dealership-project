package com.pluralsight;

public class SalesContract extends Contract {
    private boolean financeOption;
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean financeOption) {
        super(date, customerName, customerEmail, vehicleSold);
        this.financeOption = financeOption;

        // Calculations for fees
        this.salesTaxAmount = getVehicleSold().getPrice() * 0.05;
        this.recordingFee = 100.00;
        this.processingFee = getVehicleSold().getPrice() < 10000 ? 295.00 : 495.00;
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + salesTaxAmount + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        int numberOfPayments = 0;
        double interestRate = 0;
        if (financeOption) {
            if (getVehicleSold().getPrice() >= 10000) {
                numberOfPayments = 48;
                interestRate = 4.25 / 1200;
            } else {
                numberOfPayments = 24;
                interestRate = 5.25 / 1200;
            }

            double monthlyPayment = getTotalPrice() * (interestRate * Math.pow(1 + interestRate, numberOfPayments)) /
                    (Math.pow(1 + interestRate, numberOfPayments) - 1);
            monthlyPayment = Math.round(monthlyPayment * 100);
            monthlyPayment /= 100;
            return monthlyPayment;
        } else {
            return 0.0;
        }
    }

    public boolean isFinanceOption() {
        return financeOption;
    }
}
