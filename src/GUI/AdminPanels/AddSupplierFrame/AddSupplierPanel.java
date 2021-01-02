package GUI.AdminPanels.AddSupplierFrame;

import Controller.MainController;
import Database.DatabaseController;
import Enums.Countries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class AddSupplierPanel extends JPanel{
    private MainController controller;
    private AddSupplierFrame addSupplierFrame;

    private JLabel lblSupplierName;
    private JLabel lblSupplierPhone;
    private JLabel lblSupplierAddress;
    private JLabel lblSupplierCity;
    private JLabel lblSupplierCountry;


    private JTextField txtSupplierName;
    private JTextField txtSupplierPhone;
    private JTextField txtSupplierAddress;
    private JTextField txtSupplierCity;
    private JComboBox<Countries> cmbBoxCountries;


    private JButton btnAddSupplier;
    private JButton btnExit;

    private Color GRAY_BACKGROUND_COLOR;

    public AddSupplierPanel(MainController controller, AddSupplierFrame addSupplierFrame) {
        this.controller = controller;
        this.addSupplierFrame = addSupplierFrame;

        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        lblSupplierName = new JLabel("Supplier name: ");
        lblSupplierName.setMinimumSize(new Dimension(120,20));
        lblSupplierName.setPreferredSize(new Dimension(120,20));
        lblSupplierName.setForeground(Color.LIGHT_GRAY);

        txtSupplierName = new JTextField();
        txtSupplierName.setMinimumSize(new Dimension(120,20));
        txtSupplierName.setPreferredSize(new Dimension(120,20));

        lblSupplierPhone = new JLabel("Supplier phone: ");
        lblSupplierPhone.setMinimumSize(new Dimension(120,20));
        lblSupplierPhone.setPreferredSize(new Dimension(120,20));
        lblSupplierPhone.setForeground(Color.LIGHT_GRAY);

        txtSupplierPhone = new JTextField();
        txtSupplierPhone.setMinimumSize(new Dimension(120,20));
        txtSupplierPhone.setPreferredSize(new Dimension(120,20));

        lblSupplierAddress = new JLabel("Supplier address: ");
        lblSupplierAddress.setMinimumSize(new Dimension(120,20));
        lblSupplierAddress.setPreferredSize(new Dimension(120,20));
        lblSupplierAddress.setForeground(Color.LIGHT_GRAY);

        txtSupplierAddress = new JTextField();
        txtSupplierAddress.setMinimumSize(new Dimension(120,20));
        txtSupplierAddress.setPreferredSize(new Dimension(120,20));

        lblSupplierCity = new JLabel("Supplier city: ");
        lblSupplierCity.setMinimumSize(new Dimension(120,20));
        lblSupplierCity.setPreferredSize(new Dimension(120,20));
        lblSupplierCity.setForeground(Color.LIGHT_GRAY);

        txtSupplierCity = new JTextField();
        txtSupplierCity.setMinimumSize(new Dimension(120,20));
        txtSupplierCity.setPreferredSize(new Dimension(120,20));

        lblSupplierCountry = new JLabel("Supplier country: ");
        lblSupplierCountry.setMinimumSize(new Dimension(120,20));
        lblSupplierCountry.setPreferredSize(new Dimension(120,20));
        lblSupplierCountry.setForeground(Color.LIGHT_GRAY);

        cmbBoxCountries = new JComboBox<Countries>(controller.getCountries());
        cmbBoxCountries.setMinimumSize(new Dimension(120,20));
        cmbBoxCountries.setPreferredSize(new Dimension(120,20));

        btnAddSupplier = new JButton("Add supplier");
        btnAddSupplier.setSize(new Dimension(150, 25));
        btnAddSupplier.setPreferredSize(new Dimension(150, 25));
        btnAddSupplier.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnAddSupplier.setOpaque(true);
        btnAddSupplier.setBorderPainted(false);
        btnAddSupplier.setBackground(Color.decode("#518A3D"));

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
        add(lblSupplierName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtSupplierName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblSupplierPhone, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtSupplierPhone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblSupplierAddress, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtSupplierAddress, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblSupplierCity, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtSupplierCity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblSupplierCountry, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(cmbBoxCountries, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(btnAddSupplier, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(btnExit, gbc);
    }

    private void registerListeners(){
        btnAddSupplier.addActionListener(new BtnAddSupplierListener());
        btnExit.addActionListener(new BtnExitListener());
    }

    private class BtnExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            addSupplierFrame.dispose();
        }
    }

    private class BtnAddSupplierListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String supplierName = txtSupplierName.getText();
            String supplierPhone = txtSupplierPhone.getText();
            String supplierAddressLine = txtSupplierAddress.getText();
            String supplierCity = txtSupplierCity.getText();
            int countryIndex = cmbBoxCountries.getSelectedIndex();
            String supplierCountry = String.valueOf(cmbBoxCountries.getItemAt(countryIndex));

            String supplierAddress = supplierAddressLine + ", " + supplierCity + ", " + supplierCountry;

            if(!supplierName.isEmpty() && !supplierPhone.isEmpty() && !supplierAddressLine.isEmpty() &&!supplierCity.isEmpty()){
                controller.sendSupplierCredentials(supplierName, supplierAddress, supplierPhone);
            }
            else {
                JOptionPane.showMessageDialog(null, "Enter all credentials!");
            }
        }
    }
}
