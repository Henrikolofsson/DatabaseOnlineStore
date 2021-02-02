package Entities;

import java.sql.Date;

public class Order {
    private int userId;
    private Date date;
    private String status;

    public Order(int userId, Date date, String status) {
        this.userId = userId;
        this.date = date;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
