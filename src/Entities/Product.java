package Entities;

import Database.DatabaseController;

public class Product {
    private String productName;
    private int productQuantity;
    private int productPrice;
    private int productSupplier;
    private String productStatus;

    public Product(String productName, int productQuantity, int productPrice, int productSupplier) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productSupplier = productSupplier;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductSupplier() {
        return productSupplier;
    }

    public void setProductSupplier(int productSupplier) {
        this.productSupplier = productSupplier;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productQuantity=" + productQuantity +
                ", productPrice=" + productPrice +
                ", productSupplier='" + productSupplier + '\'' +
                '}';
    }
}
