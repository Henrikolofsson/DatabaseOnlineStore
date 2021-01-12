package GUI.AdminPanels.HandleProductFrame;

import Controller.MainController;
import Database.DatabaseController;
import GUI.AdminPanels.DeleteProductFrame.DeleteProductPanel;

import javax.swing.*;
import java.awt.*;

public class HandleProductFrame extends JFrame {
    private MainController controller;
    private DatabaseController databaseController;
    private HandleProductPanel handleProductPanel;

    public HandleProductFrame(MainController controller, DatabaseController databaseController) {
        this.controller = controller;
        this.databaseController = databaseController;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        //Default JFrame initializations
        setTitle("Handle product");
        setSize(new Dimension(600,600));
        setMinimumSize(new Dimension(600, 600));
        setPreferredSize(new Dimension(600,600));
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,2, 1, 0));

        handleProductPanel = new HandleProductPanel(controller, this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(handleProductPanel, gbc);

        pack();
        setLocation(new Point(300, 100));
    }
}
