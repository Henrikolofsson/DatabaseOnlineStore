package Usercreation;

import Controller.MainController;
import GUI.ApplicationMainPanel;

import javax.swing.*;
import java.awt.*;

public class CreateAccountFrame extends JFrame {
    private MainController controller;
    private CreateAccountPanel accountPanel;

    public CreateAccountFrame(MainController controller) {
        this.controller = controller;
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
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,2, 1, 0));

        accountPanel = new CreateAccountPanel(controller);
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
        setLocation(new Point(300, 100));
    }
}
