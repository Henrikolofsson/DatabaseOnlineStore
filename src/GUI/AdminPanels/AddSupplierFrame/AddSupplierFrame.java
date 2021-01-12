package GUI.AdminPanels.AddSupplierFrame;

import Controller.MainController;
import Database.DatabaseController;
import GUI.Usercreation.CreateAccountPanel;

import javax.swing.*;
import java.awt.*;

public class AddSupplierFrame extends JFrame {
    private MainController controller;
    private DatabaseController databaseController;
    private AddSupplierPanel addSupplierPanel;

    public AddSupplierFrame(MainController controller, DatabaseController databaseController) {
        this.controller = controller;
        this.databaseController = databaseController;
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

        addSupplierPanel = new AddSupplierPanel(controller, this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(addSupplierPanel,gbc);

        pack();
        setLocation(new Point(300, 100));
    }
}
