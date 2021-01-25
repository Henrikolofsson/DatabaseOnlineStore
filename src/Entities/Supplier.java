package Entities;

import Database.DatabaseController;

public class Supplier {
    private String supplierName;
    private String supplierPhone;
    private String supplierAddress;
    private String supplierCity;
    private String supplierCountry;


    public Supplier(String supplierName, String supplierPhone, String supplierAddress, String supplierCity, String supplierCountry) {
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierPhone = supplierPhone;
        this.supplierCity = supplierCity;
        this.supplierCountry = supplierCountry;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierCity() {
        return supplierCity;
    }

    public void setSupplierCity(String supplierCity) {
        this.supplierCity = supplierCity;
    }

    public String getSupplierCountry() {
        return supplierCountry;
    }

    public void setSupplierCountry(String supplierCountry) {
        this.supplierCountry = supplierCountry;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierName='" + supplierName + '\'' +
                ", supplierPhone='" + supplierPhone + '\'' +
                ", supplierAddress='" + supplierAddress + '\'' +
                ", supplierCity='" + supplierCity + '\'' +
                ", supplierCountry='" + supplierCountry + '\'' +
                '}';
    }
}
