package GUI.UserPanels;

import Controller.MainController;
import Entities.OrderItem;
import GUI.ApplicationMainPanel;
import GUI.UserPanels.ShoppingCartFrame.ShoppingCartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class UserMainPanel extends JPanel {
    private ApplicationMainPanel applicationMainPanel;
    private MainController controller;
    private UserOptionsPanel optionsPanel;
    private UserStorePanel userStorePanel;
    private int orderId;

    public UserMainPanel(ApplicationMainPanel applicationMainPanel, MainController controller){
        this.applicationMainPanel = applicationMainPanel;
        this.controller = controller;

        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents(){
        this.optionsPanel = new UserOptionsPanel(this,controller);
        this.userStorePanel = new UserStorePanel(this, controller);
        orderId = 0;
    }

    private void initializeGUI(){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600,800));
        setMaximumSize(new Dimension(600,800));
        setMinimumSize(new Dimension(600,800));
        setBackground(Color.decode("#3b3b3b"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(optionsPanel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(userStorePanel, gbc);
    }

    public void updateFirstname(String userName) {
        userStorePanel.updateFirstname(userName);
    }

    public void updateOrderItems(Vector<OrderItem> orderItems) {
        optionsPanel.updateOrderItems(orderItems);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
