package GUI.UserPanels.ShoppingCartFrame;

import Controller.MainController;
import Database.DatabaseController;
import GUI.AdminPanels.ViewUsedDiscountsFrame.ViewUsedDiscountsPanel;

import javax.swing.*;
import java.awt.*;

public class ShoppingCartFrame extends JFrame {
    private MainController controller;
    private ShoppingCartPanel shoppingCartPanel;

    public ShoppingCartFrame(MainController controller) {
        this.controller = controller;
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

        shoppingCartPanel = new ShoppingCartPanel(controller);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(shoppingCartPanel, gbc);

        pack();
        setLocation(new Point(300, 100));
    }
}
