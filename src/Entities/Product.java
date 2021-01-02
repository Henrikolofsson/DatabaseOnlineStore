package Entities;

import Database.DatabaseController;

public class Product {
    private String productName;
    private int productQuantity;
    private int productPrice;
    private String productSupplier;
    private DatabaseController databaseController;

    public Product(String productName, int productQuantity, int productPrice, String productSupplier, DatabaseController databaseController) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productSupplier = productSupplier;
        this.databaseController = databaseController;
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

    public String getProductSupplier() {
        return productSupplier;
    }

    public void setProductSupplier(String productSupplier) {
        this.productSupplier = productSupplier;
    }

    public void addProduct(){
        databaseController.addProduct(this);
    }
}
