package GUI.AdminPanels.AddDiscountFrame;

import Controller.MainController;
import Entities.Discount;
import GUI.AdminPanels.AddProductFrame.AddProductPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddDiscountPanel extends JPanel {
    private MainController controller;
    private AddDiscountFrame addDiscountFrame;

    private JLabel lblDiscountCode;
    private JLabel lblDiscountPercentage;
    private JLabel lblDiscountReason;
    private JLabel lblAddDiscountStart;
    private JLabel lblAddDiscountEnd;

    private JTextField txtDiscountCode;
    private JTextField txtDiscountPercentage;
    private JTextField txtDiscountReason;
    private JTextField txtAddDiscountStart;
    private JTextField txtAddDiscountEnd;

    private JButton btnAddDiscount;
    private JButton btnExit;
    private Color GRAY_BACKGROUND_COLOR;

    public AddDiscountPanel(MainController controller, AddDiscountFrame addDiscountFrame) {
        this.controller = controller;
        this.addDiscountFrame = addDiscountFrame;

        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        lblDiscountCode = new JLabel("Discount code: ");
        lblDiscountCode.setMinimumSize(new Dimension(120,20));
        lblDiscountCode.setPreferredSize(new Dimension(120,20));
        lblDiscountCode.setForeground(Color.LIGHT_GRAY);

        txtDiscountCode = new JTextField();
        txtDiscountCode.setMinimumSize(new Dimension(120,20));
        txtDiscountCode.setPreferredSize(new Dimension(120,20));
        txtDiscountCode.setBackground(GRAY_BACKGROUND_COLOR);
        txtDiscountCode.setForeground(Color.LIGHT_GRAY);
        txtDiscountCode.setOpaque(true);
        txtDiscountCode.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        lblDiscountPercentage = new JLabel("Discount percentage: ");
        lblDiscountPercentage.setMinimumSize(new Dimension(120,20));
        lblDiscountPercentage.setPreferredSize(new Dimension(120,20));
        lblDiscountPercentage.setForeground(Color.LIGHT_GRAY);

        txtDiscountPercentage = new JTextField();
        txtDiscountPercentage.setMinimumSize(new Dimension(120,20));
        txtDiscountPercentage.setPreferredSize(new Dimension(120,20));
        txtDiscountPercentage.setBackground(GRAY_BACKGROUND_COLOR);
        txtDiscountPercentage.setForeground(Color.LIGHT_GRAY);
        txtDiscountPercentage.setOpaque(true);
        txtDiscountPercentage.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        lblDiscountReason = new JLabel("Discount reason: ");
        lblDiscountReason.setMinimumSize(new Dimension(120,20));
        lblDiscountReason.setPreferredSize(new Dimension(120,20));
        lblDiscountReason.setForeground(Color.LIGHT_GRAY);

        txtDiscountReason = new JTextField();
        txtDiscountReason.setMinimumSize(new Dimension(120,20));
        txtDiscountReason.setPreferredSize(new Dimension(120,20));
        txtDiscountReason.setBackground(GRAY_BACKGROUND_COLOR);
        txtDiscountReason.setForeground(Color.LIGHT_GRAY);
        txtDiscountReason.setOpaque(true);
        txtDiscountReason.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        lblAddDiscountStart = new JLabel("Discount startdate:");
        lblAddDiscountStart.setMinimumSize(new Dimension(120,20));
        lblAddDiscountStart.setPreferredSize(new Dimension(120,20));
        lblAddDiscountStart.setForeground(Color.LIGHT_GRAY);

        txtAddDiscountStart = new JTextField();
        txtAddDiscountStart.setSize(new Dimension(120, 20));
        txtAddDiscountStart.setPreferredSize(new Dimension(120, 20));
        txtAddDiscountStart.setText("YYYY-MM-DD");
        txtAddDiscountStart.setBackground(GRAY_BACKGROUND_COLOR);
        txtAddDiscountStart.setForeground(Color.LIGHT_GRAY);
        txtAddDiscountStart.setOpaque(true);
        txtAddDiscountStart.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        lblAddDiscountEnd = new JLabel("Discount startdate:");
        lblAddDiscountEnd.setMinimumSize(new Dimension(120,20));
        lblAddDiscountEnd.setPreferredSize(new Dimension(120,20));
        lblAddDiscountEnd.setForeground(Color.LIGHT_GRAY);

        txtAddDiscountEnd = new JTextField();
        txtAddDiscountEnd.setSize(new Dimension(120, 20));
        txtAddDiscountEnd.setPreferredSize(new Dimension(120, 20));
        txtAddDiscountEnd.setText("YYYY-MM-DD");
        txtAddDiscountEnd.setBackground(GRAY_BACKGROUND_COLOR);
        txtAddDiscountEnd.setForeground(Color.LIGHT_GRAY);
        txtAddDiscountEnd.setOpaque(true);
        txtAddDiscountEnd.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

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
        add(lblDiscountCode, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtDiscountCode, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblDiscountPercentage, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtDiscountPercentage, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblDiscountReason, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtDiscountReason, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblAddDiscountStart, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtAddDiscountStart, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblAddDiscountEnd, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(txtAddDiscountEnd, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(btnAddDiscount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(btnExit, gbc);
    }

    private void registerListeners(){
        btnAddDiscount.addActionListener(new AddDiscountPanel.BtnAddDiscountListener());
        btnExit.addActionListener(new BtnExitListener());
    }

    public Discount getDiscount() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //Text fields text formatted to a SimpleDateFormat
            java.util.Date dateStart = format.parse(txtAddDiscountStart.getText());
            java.util.Date dateEnd = format.parse(txtAddDiscountEnd.getText());

            //Converted to a SQL date object, so that the database can store the object.
            java.sql.Date sqlDateStart = new java.sql.Date(dateStart.getTime());
            java.sql.Date sqlDateEnd = new java.sql.Date(dateEnd.getTime());

            return new Discount(Integer.parseInt(txtDiscountCode.getText()),
                    Integer.parseInt(txtDiscountPercentage.getText()),
                    txtDiscountReason.getText(),
                    sqlDateStart,
                    sqlDateEnd);

        } catch(ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class BtnExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addDiscountFrame.dispose();
        }
    }

    private class BtnAddDiscountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!txtDiscountReason.getText().isEmpty() &&
                    Integer.parseInt(txtDiscountCode.getText()) > -1 &&
                    Integer.parseInt(txtDiscountPercentage.getText()) > -1 &&
                    !txtAddDiscountStart.getText().isEmpty() &&
                    !txtAddDiscountEnd.getText().isEmpty()) {

                controller.btnPressed("AddDiscount");
            }
            else {
                JOptionPane.showMessageDialog(null, "Enter all credentials!");
            }
        }
    }
}