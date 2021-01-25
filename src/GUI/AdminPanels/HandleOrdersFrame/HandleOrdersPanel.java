package GUI.AdminPanels.HandleOrdersFrame;

import Controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandleOrdersPanel extends JPanel {
    private MainController controller;
    private HandleOrdersFrame handleOrdersFrame;

    private JLabel lblOrders;

    private JList<String> listOrders;

    private JScrollPane scrollPane;

    private DefaultListModel<String> defaultListModel;

    private JButton btnConfirmOrder;
    private JButton btnExit;

    private Color GRAY_BACKGROUND_COLOR;

    public HandleOrdersPanel(MainController controller, HandleOrdersFrame handleOrdersFrame) {
        this.handleOrdersFrame = handleOrdersFrame;
        this.controller = controller;
        defaultListModel = new DefaultListModel<>();

        initializeComponents();
        initializeGUI();
        registerListener();

    }

    private void initializeComponents() {
        lblOrders = new JLabel("Orders:");
        lblOrders.setForeground(Color.LIGHT_GRAY);

        listOrders = new JList<>(defaultListModel);
        listOrders.setPreferredSize(new Dimension(500, 200));
        listOrders.setMinimumSize(new Dimension(500, 200));

        scrollPane = new JScrollPane(listOrders, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(new Dimension(300, 300));
        scrollPane.setPreferredSize(new Dimension(300, 300));
        scrollPane.setMinimumSize(new Dimension(300, 300));

        btnConfirmOrder = new JButton("Confirm order");
        btnConfirmOrder.setSize(new Dimension(125, 25));
        btnConfirmOrder.setPreferredSize(new Dimension(125, 25));
        btnConfirmOrder.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnConfirmOrder.setOpaque(true);
        btnConfirmOrder.setBorderPainted(false);
        btnConfirmOrder.setBackground(Color.decode("#518A3D"));

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
        add(lblOrders, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(listOrders, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(btnConfirmOrder, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(btnExit, gbc);
    }

    private void registerListener() {
        btnExit.addActionListener(new BtnExitListener());
    }

    public void updateList(){
        defaultListModel.removeAllElements();
        for(int i = 0; i < handleOrdersFrame.getOrdersList().size(); i++){
            defaultListModel.addElement(handleOrdersFrame.getOrdersList().get(i));
        }
    }

    private class BtnExitListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            handleOrdersFrame.dispose();
        }
    }
}
