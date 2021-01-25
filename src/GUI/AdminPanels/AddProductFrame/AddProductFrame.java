package GUI.AdminPanels.AddProductFrame;

import Controller.MainController;
import Database.DatabaseController;
import Entities.ComboBoxItem;
import Entities.Product;
import GUI.Usercreation.CreateAccountPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddProductFrame extends JFrame {
    private MainController controller;
    private AddProductPanel addProductPanel;


    public AddProductFrame(MainController controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        //Default JFrame initializations
        setTitle("Add Product");
        setSize(new Dimension(500,400));
        setMinimumSize(new Dimension(500, 400));
        setPreferredSize(new Dimension(500,400));
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,2, 1, 0));

        addProductPanel = new AddProductPanel(controller, this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(addProductPanel,gbc);

        pack();
        setLocation(new Point(300, 100));
    }

    public Product getProduct() {
        return addProductPanel.getProduct();
    }

    public void setSuppliers(ArrayList<ComboBoxItem> suppliers) {
        addProductPanel.setSuppliers(suppliers);
    }

}
