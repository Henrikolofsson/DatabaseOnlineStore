package GUI.AdminPanels.DisplayProductSaleFrame;

import Controller.MainController;
import GUI.AdminPanels.DeleteProductFrame.DeleteProductPanel;

import javax.swing.*;
import java.awt.*;

public class DisplayProductSaleFrame extends JFrame {
    private MainController controller;
    private DisplayProductSalePanel displayProductSalePanel;

    public DisplayProductSaleFrame(MainController controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        //Default JFrame initializations
        setTitle("Show Product Sales");
        setSize(new Dimension(600,450));
        setMinimumSize(new Dimension(600, 450));
        setPreferredSize(new Dimension(600,450));
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,2, 1, 0));

        displayProductSalePanel = new DisplayProductSalePanel(controller, this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(displayProductSalePanel, gbc);

        pack();
        setLocation(new Point(300, 100));
    }
}
