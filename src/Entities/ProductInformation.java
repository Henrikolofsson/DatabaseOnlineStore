package Entities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ProductInformation {
    private int productId;
    private String productName;
    private double productPrice;
    private int discountId;
    private String discountReason;
    private String supplierName;
    private double discountPercentage;
    private double discountedPrice;

    public ProductInformation(int productId, String productName, double productPrice, int discountId, String discountReason, String supplierName, double discountPercentage, double discountedPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.discountId = discountId;
        this.discountReason = discountReason;
        this.supplierName = supplierName;
        this.discountPercentage = discountPercentage;
        DecimalFormat df = new DecimalFormat("#.##");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        this.discountedPrice = Double.parseDouble(df.format(discountedPrice));
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getDiscountReason() {
        return discountReason;
    }

    public void setDiscountReason(String discountReason) {
        this.discountReason = discountReason;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    @Override
    public String toString() {
        String productInfo = "";
        if(discountId == -1) {
            productInfo += productName + " | " + "Price: " + productPrice + " | " + "Supplier: " + supplierName;
        } else {
            productInfo += productName + " | " + "Price: " + productPrice + " | Discounted price: " + discountedPrice +
                    " | " + "Discount reason: " + discountReason + " | " + "Discount percentage: " + discountPercentage +
                    " | " + "Supplier: " + supplierName;
        }
        return productInfo;
    }
}
