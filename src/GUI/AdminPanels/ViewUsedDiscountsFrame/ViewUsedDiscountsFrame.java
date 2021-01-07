package GUI.AdminPanels.ViewUsedDiscountsFrame;

import Controller.MainController;
import Database.DatabaseController;
import GUI.AdminPanels.HandleProductFrame.HandleProductPanel;

import javax.swing.*;
import java.awt.*;

public class ViewUsedDiscountsFrame extends JFrame {
    private MainController controller;
    private ViewUsedDiscountsPanel viewUsedDiscountsPanel;
    private DatabaseController databaseController;

    public ViewUsedDiscountsFrame(MainController controller, DatabaseController databaseController) {
        this.controller = controller;
        this.databaseController = databaseController;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        //Default JFrame initializations
        setTitle("Discount history");
        setSize(new Dimension(600,600));
        setMinimumSize(new Dimension(600, 600));
        setPreferredSize(new Dimension(600,600));
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,2, 1, 0));

        viewUsedDiscountsPanel = new ViewUsedDiscountsPanel(controller, this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(viewUsedDiscountsPanel, gbc);

        pack();
        setLocation(new Point(300, 100));
    }

    public void updateUsedDiscounts() {
        viewUsedDiscountsPanel.updateUsedDiscountList();
    }
}
