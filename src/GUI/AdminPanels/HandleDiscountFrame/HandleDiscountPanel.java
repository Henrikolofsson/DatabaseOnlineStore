package GUI.AdminPanels.HandleDiscountFrame;

import Controller.MainController;
import Entities.ComboBoxItem;
import Entities.ListItem;
import Enums.Countries;
import GUI.AdminPanels.AddSupplierFrame.AddSupplierFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class HandleDiscountPanel extends JPanel {
    private MainController controller;
    private HandleDiscountFrame handleDiscountFrame;

    private JLabel lblProductName;
    private JLabel lblDiscountReason;

    private JComboBox<ComboBoxItem> cmbProducts;
    private JComboBox<ComboBoxItem> cmbDiscount;

    private DefaultComboBoxModel<ComboBoxItem> modelProducts;
    private DefaultComboBoxModel<ComboBoxItem> modelDiscounts;

    private JButton btnAddDiscount;
    private JButton btnExit;

    private Color GRAY_BACKGROUND_COLOR;

    public HandleDiscountPanel(HandleDiscountFrame handleDiscountFrame, MainController controller) {
        this.handleDiscountFrame = handleDiscountFrame;
        this.controller = controller;


        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void updateProducts(Vector<ComboBoxItem> items) {
        modelProducts.removeAllElements();
        for(int i = 0; i < items.size(); i++) {
            modelProducts.addElement(items.get(i));
        }
    }

    private void updateDiscounts(Vector<ComboBoxItem> items) {
        modelDiscounts.removeAllElements();
        for(int i = 0; i < items.size(); i++) {
            modelDiscounts.addElement(items.get(i));
        }
    }

    private void initializeComponents() {
        lblProductName = new JLabel("Product name: ");
        lblProductName.setMinimumSize(new Dimension(120,20));
        lblProductName.setPreferredSize(new Dimension(120,20));
        lblProductName.setForeground(Color.LIGHT_GRAY);

        lblDiscountReason = new JLabel("Discount reason: ");
        lblDiscountReason.setMinimumSize(new Dimension(120,20));
        lblDiscountReason.setPreferredSize(new Dimension(120,20));
        lblDiscountReason.setForeground(Color.LIGHT_GRAY);

        modelProducts = new DefaultComboBoxModel<>(controller.getActiveProducts());
        cmbProducts = new JComboBox<>(modelProducts);
        cmbProducts.setMinimumSize(new Dimension(120,20));
        cmbProducts.setPreferredSize(new Dimension(120,20));
        cmbProducts.setBackground(Color.decode("#2b2b2b"));
        cmbProducts.setForeground(Color.LIGHT_GRAY);
        cmbProducts.setOpaque(true);
        cmbProducts.setFont(new Font("Helvetica", Font.BOLD, 12));

        modelDiscounts = new DefaultComboBoxModel<>(controller.getAllDiscounts());
        cmbDiscount = new JComboBox<>(modelDiscounts);
        cmbDiscount.setMinimumSize(new Dimension(120,20));
        cmbDiscount.setPreferredSize(new Dimension(120,20));
        cmbDiscount.setBackground(Color.decode("#2b2b2b"));
        cmbDiscount.setForeground(Color.LIGHT_GRAY);
        cmbDiscount.setOpaque(true);
        cmbDiscount.setFont(new Font("Helvetica", Font.BOLD, 12));

        btnAddDiscount = new JButton("Add discount");
        btnAddDiscount.setSize(new Dimension(150, 25));
        btnAddDiscount.setPreferredSize(new Dimension(150, 25));
        btnAddDiscount.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnAddDiscount.setOpaque(true);
        btnAddDiscount.setBorderPainted(false);
        btnAddDiscount.setBackground(Color.decode("#518A3D"));

        btnExit = new JButton("Exit");
        btnExit.setSize(new Dimension(200, 25));
        btnExit.setPreferredSize(new Dimension(100, 25));
        btnExit.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnExit.setOpaque(true);
        btnExit.setBorderPainted(false);
        btnExit.setBackground(Color.decode("#518A3D"));

        GRAY_BACKGROUND_COLOR = Color.decode("#2b2b2b");
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setBackground(GRAY_BACKGROUND_COLOR);
        setPreferredSize(new Dimension(500, 400));
        setMaximumSize(new Dimension(500,400));
        setMinimumSize(new Dimension(500,400));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblProductName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(cmbProducts, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblDiscountReason, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(cmbDiscount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(btnAddDiscount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(btnExit, gbc);
    }

    private void registerListeners() {
        btnAddDiscount.addActionListener(new BtnAddDiscount());
        btnExit.addActionListener(new BtnExitListener());
    }

    private class BtnAddDiscount implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int productIndex = cmbProducts.getSelectedIndex();
            int discountIndex = cmbDiscount.getSelectedIndex();
            controller.addProductDiscount(cmbProducts.getItemAt(productIndex).getItemValue(), cmbDiscount.getItemAt(discountIndex).getItemValue());
        }
    }

    private class BtnExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleDiscountFrame.dispose();
        }
    }
}
