package Entities;

public class ComboBoxItem {
    private String itemText;
    private int itemValue;

    public ComboBoxItem(String itemText, int itemValue) {
        this.itemText = itemText;
        this.itemValue = itemValue;
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

    @Override
    public String toString() {
        return itemText;
    }
}
