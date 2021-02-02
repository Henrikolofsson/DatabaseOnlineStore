package GUI.AdminPanels.HandleDiscountFrame;

import Controller.MainController;
import Database.DatabaseController;
import GUI.AdminPanels.AddSupplierFrame.AddSupplierPanel;

import javax.swing.*;
import java.awt.*;

public class HandleDiscountFrame extends JFrame {
    private MainController controller;
    private DatabaseController databaseController;
    private HandleDiscountPanel handleDiscountPanel;

    public HandleDiscountFrame(MainController controller) {
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

        handleDiscountPanel = new HandleDiscountPanel(this, controller);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(handleDiscountPanel,gbc);

        pack();
        setLocation(new Point(300, 100));
    }


}
