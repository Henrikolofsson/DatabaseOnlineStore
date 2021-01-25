package Entities;

import Database.DatabaseController;

import java.sql.Date;


public class Discount {
    private int discountCode;
    private int discountPercentage;
    private String discountReason;
    private Date discountStart;
    private Date discountEnd;

    public Discount(int discountCode, int discountPercentage, String discountReason, Date discountStart, Date discountEnd) {
        this.discountCode = discountCode;
        this.discountPercentage = discountPercentage;
        this.discountReason = discountReason;
        this.discountStart = discountStart;
        this.discountEnd = discountEnd;
    }

    public int getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(int discountCode) {
        this.discountCode = discountCode;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getDiscountReason() {
        return discountReason;
    }

    public void setDiscountReason(String discountReason) {
        this.discountReason = discountReason;
    }

    public Date getDiscountStart() {
        return discountStart;
    }

    public void setDiscountStart(Date discountStart) {
        this.discountStart = discountStart;
    }

    public Date getDiscountEnd() {
        return discountEnd;
    }

    public void setDiscountEnd(Date discountEnd) {
        this.discountEnd = discountEnd;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discountCode=" + discountCode +
                ", discountPercentage=" + discountPercentage +
                ", discountReason='" + discountReason + '\'' +
                ", discountStart='" + discountStart + '\'' +
                ", discountEnd='" + discountEnd + '\'' +
                '}';
    }
}
