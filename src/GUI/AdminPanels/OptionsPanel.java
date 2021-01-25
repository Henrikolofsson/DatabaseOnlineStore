package GUI.AdminPanels;

import Controller.MainController;
import Entities.Supplier;
import Interfaces.PanelListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OptionsPanel extends JPanel {
    private PanelListener panelListener;
    private AdminMainPanel adminMainPanel;

    private String[] options;
    private JComboBox<String> cmbBoxOptions;
    private JButton btnGo;

    public OptionsPanel(MainController controller, AdminMainPanel adminMainPanel){
        this.panelListener = controller;
        this.adminMainPanel = adminMainPanel;

        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents(){
        options = new String[]{"Add supplier", "Add discount", "Add product", "Delete product", "Handle product",
                "Handle orders", "Discount history"};

        cmbBoxOptions = new JComboBox<>();
        for(int i = 0; i < options.length; i++){
            cmbBoxOptions.addItem(options[i]);
        }
        cmbBoxOptions.setBackground(Color.decode("#2b2b2b"));
        cmbBoxOptions.setForeground(Color.LIGHT_GRAY);
        cmbBoxOptions.setOpaque(true);
        cmbBoxOptions.setFont(new Font("Helvetica", Font.BOLD, 12));

        btnGo = new JButton("Go to option:");
        btnGo.setSize(new Dimension(150, 25));
        btnGo.setPreferredSize(new Dimension(150, 25));
        btnGo.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnGo.setOpaque(true);
        btnGo.setBorderPainted(false);
        btnGo.setBackground(Color.decode("#518A3D"));
    }

    private void initializeGUI(){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600,100));
        setMaximumSize(new Dimension(600,100));
        setMinimumSize(new Dimension(600,100));
        setBackground(Color.decode("#2b2b2b"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(btnGo, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        add(cmbBoxOptions, gbc);
    }

    private void registerListeners() {
        btnGo.addActionListener(new BtnGoListener());
    }


    private class BtnGoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(cmbBoxOptions.getSelectedIndex() == 0){
                panelListener.btnPressed("OpenAddSupplierWindow");
            } else if(cmbBoxOptions.getSelectedIndex() == 1){
                panelListener.btnPressed("OpenAddDiscountWindow");
            } else if(cmbBoxOptions.getSelectedIndex() == 2){
                panelListener.btnPressed("OpenAddProductWindow");
            } else if(cmbBoxOptions.getSelectedIndex() == 3){
                panelListener.btnPressed("OpenDeleteProductWindow");
            } else if(cmbBoxOptions.getSelectedIndex() == 4){
                panelListener.btnPressed("OpenHandleProductWindow");
            } else if(cmbBoxOptions.getSelectedIndex() == 5){
                panelListener.btnPressed("OpenHandleOrdersWindow");
            } else {
                panelListener.btnPressed("OpenViewUsedDiscountsWindow");
            }
        }
    }
}
