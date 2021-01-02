package Entities;

import Database.DatabaseController;

public class Discount {
    private int discountCode;
    private double discountPercentage;
    private String discountReason;
    private DatabaseController databaseController;

    public Discount(int discountCode, double discountPercentage, String discountReason, DatabaseController databaseController) {
        this.discountCode = discountCode;
        this.discountPercentage = discountPercentage;
        this.discountReason = discountReason;
        this.databaseController = databaseController;
    }

    public int getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(int discountCode) {
        this.discountCode = discountCode;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getDiscountReason() {
        return discountReason;
    }

    public void setDiscountReason(String discountReason) {
        this.discountReason = discountReason;
    }

    public void addDiscount(){
        databaseController.addDiscount(this);
    }
}
