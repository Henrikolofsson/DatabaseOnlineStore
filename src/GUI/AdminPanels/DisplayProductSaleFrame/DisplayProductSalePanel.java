package GUI.AdminPanels.DisplayProductSaleFrame;

import Controller.MainController;
import Entities.ComboBoxItem;
import Entities.ListItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class DisplayProductSalePanel extends JPanel {
    private MainController controller;
    private DisplayProductSaleFrame displayProductSaleFrame;
    private JLabel lblSales;
    private JList<ListItem> listItems;
    private JScrollPane scrollPane;
    private DefaultListModel<ListItem> defaultListModel;
    private JComboBox<String> cmbItems;
    private String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private JButton btnGetSales;
    private JButton btnExit;

    private Color GRAY_BACKGROUND_COLOR;


    public DisplayProductSalePanel(MainController controller, DisplayProductSaleFrame displayProductSaleFrame) {
        this.controller = controller;
        this.displayProductSaleFrame = displayProductSaleFrame;
        defaultListModel = new DefaultListModel<>();

        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        lblSales = new JLabel("Sales:");
        lblSales.setForeground(Color.LIGHT_GRAY);

        listItems = new JList<>(defaultListModel);
        listItems.setPreferredSize(new Dimension(500, 200));
        listItems.setMinimumSize(new Dimension(500, 200));

        scrollPane = new JScrollPane(listItems, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(new Dimension(300, 300));
        scrollPane.setPreferredSize(new Dimension(300, 300));
        scrollPane.setMinimumSize(new Dimension(300, 300));

        cmbItems = new JComboBox<>(months);
        cmbItems.setBackground(Color.decode("#2b2b2b"));
        cmbItems.setForeground(Color.LIGHT_GRAY);
        cmbItems.setOpaque(true);
        cmbItems.setFont(new Font("Helvetica", Font.BOLD, 12));

        btnGetSales = new JButton("Get Sales");
        btnGetSales.setSize(new Dimension(100, 25));
        btnGetSales.setPreferredSize(new Dimension(100, 25));
        btnGetSales.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnGetSales.setOpaque(true);
        btnGetSales.setBorderPainted(false);
        btnGetSales.setBackground(Color.decode("#518A3D"));

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
        setPreferredSize(new Dimension(600, 450));
        setMaximumSize(new Dimension(600,450));
        setMinimumSize(new Dimension(600,450));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(cmbItems, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblSales, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(listItems, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(btnGetSales, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(btnExit, gbc);
    }

    private void registerListeners() {
        btnGetSales.addActionListener(new BtnGetSalesListener());
        btnExit.addActionListener(new BtnExitListener());
    }

    private void updateList(Vector<ListItem> sales) {
        defaultListModel.clear();
        for(ListItem item : sales) defaultListModel.addElement(item);
    }

    private class BtnGetSalesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = cmbItems.getSelectedIndex();
            String month = cmbItems.getItemAt(index);
            updateList(controller.getSales(month));
        }


    }

    private class BtnExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayProductSaleFrame.dispose();
        }
    }

}
