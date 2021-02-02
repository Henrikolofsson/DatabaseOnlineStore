package GUI.AdminPanels.HandleProductFrame;

import Controller.MainController;
import Entities.ComboBoxItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class HandleProductPanel extends JPanel {
    private MainController controller;
    private HandleProductFrame handleProductFrame;

    private JLabel lblProductToEdit;

    private JLabel lblNewQuantity;

    private JTextField txtNewQuantity;

    private JComboBox<ComboBoxItem> cmbBoxProducts;

    private JButton btnEditQuantity;
    private JButton btnExit;

    private Color GRAY_BACKGROUND_COLOR;

    public HandleProductPanel(MainController controller, HandleProductFrame handleProductFrame){
        this.controller = controller;
        this.handleProductFrame = handleProductFrame;

        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        lblProductToEdit = new JLabel("<html><div style='text-align: center;'>Select a product to edit</div></html>");
        lblProductToEdit.setMinimumSize(new Dimension(140,20));
        lblProductToEdit.setPreferredSize(new Dimension(140,20));
        lblProductToEdit.setForeground(Color.LIGHT_GRAY);

        DefaultComboBoxModel<ComboBoxItem> model = new DefaultComboBoxModel<>(controller.getActiveProducts());
        cmbBoxProducts = new JComboBox<>(model);

        cmbBoxProducts.setMinimumSize(new Dimension(200,20));
        cmbBoxProducts.setPreferredSize(new Dimension(200,20));
        cmbBoxProducts.setBackground(Color.decode("#2b2b2b"));
        cmbBoxProducts.setForeground(Color.LIGHT_GRAY);
        cmbBoxProducts.setOpaque(true);
        cmbBoxProducts.setFont(new Font("Helvetica", Font.BOLD, 12));

        lblNewQuantity = new JLabel("Add Quantity: ");
        lblNewQuantity.setMinimumSize(new Dimension(120,20));
        lblNewQuantity.setPreferredSize(new Dimension(120,20));
        lblNewQuantity.setForeground(Color.LIGHT_GRAY);

        txtNewQuantity = new JTextField();
        txtNewQuantity.setSize(new Dimension(50, 20));
        txtNewQuantity.setPreferredSize(new Dimension(50, 20));
        txtNewQuantity.setBackground(GRAY_BACKGROUND_COLOR);
        txtNewQuantity.setForeground(Color.LIGHT_GRAY);
        txtNewQuantity.setOpaque(true);
        txtNewQuantity.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        btnEditQuantity = new JButton("Edit quantity");
        btnEditQuantity.setSize(new Dimension(100, 25));
        btnEditQuantity.setPreferredSize(new Dimension(100, 25));
        btnEditQuantity.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnEditQuantity.setOpaque(true);
        btnEditQuantity.setBorderPainted(false);
        btnEditQuantity.setBackground(Color.decode("#518A3D"));

        btnExit = new JButton("Exit");
        btnExit.setSize(new Dimension(100, 25));
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
        setPreferredSize(new Dimension(600, 600));
        setMaximumSize(new Dimension(600,600));
        setMinimumSize(new Dimension(600,600));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblProductToEdit, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(cmbBoxProducts, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(lblNewQuantity, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtNewQuantity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(btnEditQuantity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        add(btnExit, gbc);
    }

    private void registerListeners() {
        btnEditQuantity.addActionListener(new BtnEditQuantityListener());
        btnExit.addActionListener(new BtnExitListener());
    }

    public Map<String, Integer> getIdAndQuantity() {
        int index = cmbBoxProducts.getSelectedIndex();
        Map<String, Integer> map = new HashMap<>(2);
        map.put("product_id", cmbBoxProducts.getItemAt(index).getItemValue());
        map.put("quantity", Integer.parseInt(txtNewQuantity.getText()));
        return map;
    }

    private class BtnEditQuantityListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.btnPressed("AddQuantity");
        }
    }


    private class BtnExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleProductFrame.dispose();
        }

    }
}

