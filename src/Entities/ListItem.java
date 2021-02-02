package Entities;

public class ListItem {
    private String itemText;
    private int itemValue;
    private double itemPrice;

    public ListItem(String itemText, int itemValue, double price) {
        this.itemText = itemText;
        this.itemValue = itemValue;
        this.itemPrice = price;
    }


    public String getItemText() {
        return itemText;
    }


    public void setItemText(String itemText) {
        this.itemText = itemText;
    }


    public int getItemValue() {
        return itemValue;
    }


    public void setItemValue(int itemValue) {
        this.itemValue = itemValue;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return itemText;
    }
}
