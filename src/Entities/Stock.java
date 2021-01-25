package Entities;

import java.sql.Date;

public class Stock {
    private int productId;
    private Date date;
    private int orderId;
    private int amount;
    private String status;

    public Stock(int productId, Date date, int orderId, int amount, String status) {
        this.productId = productId;
        this.date = date;
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "productId=" + productId +
                ", date=" + date +
                ", orderId=" + orderId +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
