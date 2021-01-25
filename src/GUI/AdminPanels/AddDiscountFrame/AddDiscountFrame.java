package GUI.AdminPanels.AddDiscountFrame;

import Controller.MainController;
import Database.DatabaseController;
import Entities.Discount;
import GUI.AdminPanels.AddProductFrame.AddProductPanel;

import javax.swing.*;
import java.awt.*;

public class AddDiscountFrame extends JFrame {
    private MainController controller;
    private AddDiscountPanel addDiscountPanel;

    public AddDiscountFrame(MainController controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        //Default JFrame initializations
        setTitle("Create account");
        setSize(new Dimension(500,400));
        setMinimumSize(new Dimension(500, 400));
        setPreferredSize(new Dimension(500,400));
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,2, 1, 0));

        addDiscountPanel = new AddDiscountPanel(controller, this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(addDiscountPanel, gbc);

        pack();
        setLocation(new Point(300, 100));
    }

    public Discount getDiscount() {
        return addDiscountPanel.getDiscount();
    }
}
