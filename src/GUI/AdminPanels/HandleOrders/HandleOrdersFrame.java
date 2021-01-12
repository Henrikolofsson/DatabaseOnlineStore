package GUI.AdminPanels.HandleOrders;

import Controller.MainController;
import Database.DatabaseController;
import GUI.AdminPanels.HandleProductFrame.HandleProductPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HandleOrdersFrame extends JFrame {
    private MainController controller;
    private DatabaseController databaseController;
    private HandleOrdersPanel handleOrdersPanel;

    public HandleOrdersFrame(MainController controller, DatabaseController databaseController) {
        this.controller = controller;
        this.databaseController = databaseController;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        //Default JFrame initializations
        setTitle("Handle product");
        setSize(new Dimension(600,450));
        setMinimumSize(new Dimension(600, 450));
        setPreferredSize(new Dimension(600,450));
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,2, 1, 0));

        handleOrdersPanel = new HandleOrdersPanel(controller, this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(handleOrdersPanel, gbc);

        pack();
        setLocation(new Point(300, 100));
    }

    public ArrayList<String> getOrdersList() {
        return controller.getOrdersList();
    }
}
