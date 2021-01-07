package Usercreation;

import Controller.MainController;
import Database.DatabaseController;
import GUI.ApplicationMainPanel;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;

public class CreateAccountFrame extends JFrame {
    private MainController controller;
    private DatabaseController databaseController;
    private CreateAccountPanel accountPanel;

    public CreateAccountFrame(MainController controller, DatabaseController databaseController) {
        this.controller = controller;
        this.databaseController = databaseController;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        //Default JFrame initializations
        setTitle("Create account");
        setSize(new Dimension(600,500));
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 500));
        setPreferredSize(new Dimension(600,500));
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,2, 1, 0));

        accountPanel = new CreateAccountPanel(controller, databaseController);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(accountPanel,gbc);

        pack();
        //setLocation(new Point(300, 100));
    }
}
