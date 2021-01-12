package GUI.AdminPanels.DeleteProductFrame;

import Controller.MainController;
import Database.DatabaseController;
import GUI.AdminPanels.AddProductFrame.AddProductPanel;

import javax.swing.*;
import java.awt.*;

public class DeleteProductFrame extends JFrame {
    private MainController controller;
    private DatabaseController databaseController;
    private DeleteProductPanel deleteProductPanel;

    public DeleteProductFrame(MainController controller, DatabaseController databaseController) {
        this.controller = controller;
        this.databaseController = databaseController;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        //Default JFrame initializations
        setTitle("Add Product");
        setSize(new Dimension(500,300));
        setMinimumSize(new Dimension(500, 300));
        setPreferredSize(new Dimension(500,300));
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,2, 1, 0));

        deleteProductPanel = new DeleteProductPanel(controller, this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(deleteProductPanel, gbc);

        pack();
        setLocation(new Point(300, 100));
    }
}

