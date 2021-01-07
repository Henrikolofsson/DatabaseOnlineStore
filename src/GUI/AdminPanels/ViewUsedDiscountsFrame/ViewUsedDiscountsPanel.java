package GUI.AdminPanels.ViewUsedDiscountsFrame;

import Controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewUsedDiscountsPanel extends JPanel {
    private MainController controller;
    private ViewUsedDiscountsFrame viewUsedDiscountsFrame;
    private JList<String> listUsedDiscounts;
    private JScrollPane scrollPane;

    private DefaultListModel<String> defaultListModel;

    private JButton btnExit;

    private Color GRAY_BACKGROUND_COLOR;
    public ViewUsedDiscountsPanel(MainController controller, ViewUsedDiscountsFrame viewUsedDiscountsFrame) {
        this.controller = controller;
        this.viewUsedDiscountsFrame = viewUsedDiscountsFrame;

        initializeComponents();
        updateUsedDiscountList();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        defaultListModel = new DefaultListModel<>();

        listUsedDiscounts = new JList<>(defaultListModel);
        for(int i = 0; i < controller.getUsedDiscounts().size(); i++){
            defaultListModel.addElement(controller.getUsedDiscounts().get(i));
        }
        if(defaultListModel.isEmpty()){
            defaultListModel.addElement("No previous discounts");
        }
        listUsedDiscounts.setSize(new Dimension(400, 400));
        listUsedDiscounts.setPreferredSize(new Dimension(500, 400));
        listUsedDiscounts.setMinimumSize(new Dimension(500, 400));

        scrollPane = new JScrollPane(listUsedDiscounts, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(new Dimension(300, 300));
        scrollPane.setPreferredSize(new Dimension(300, 300));
        scrollPane.setMinimumSize(new Dimension(300, 300));

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

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(scrollPane, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(btnExit, gbc);
    }

    private void registerListeners() {
        btnExit.addActionListener(new BtnExitListener());
    }

    public void updateUsedDiscountList(){
        defaultListModel.removeAllElements();
        for(int i = 0; i < controller.getUsedDiscounts().size(); i++){
            defaultListModel.addElement(controller.getUsedDiscounts().get(i));
        }
        if(defaultListModel.isEmpty()){
            defaultListModel.addElement("No previous discounts");
        }
    }

    private class BtnExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewUsedDiscountsFrame.dispose();
        }
    }
}
