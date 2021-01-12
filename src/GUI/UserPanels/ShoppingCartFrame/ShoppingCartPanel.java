package GUI.UserPanels.ShoppingCartFrame;

import Controller.MainController;

import javax.swing.*;
import java.awt.*;

public class ShoppingCartPanel extends JPanel {
    private MainController controller;

    private JList<String> listOrderContent;
    private JScrollPane scrollPane;

    private DefaultListModel<String> defaultListModel;

    private JButton btnExit;
    private JButton btnPlaceOrder;

    private JLabel lblPrice;
    private JLabel lblOrder;

    private Color GRAY_BACKGROUND_COLOR;

    public ShoppingCartPanel(MainController controller){
        this.controller = controller;

        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        defaultListModel = new DefaultListModel<>();

        listOrderContent = new JList<>(defaultListModel);
        if(defaultListModel.isEmpty()){
            defaultListModel.addElement("No products added");
        }
        listOrderContent.setSize(new Dimension(400, 300));
        listOrderContent.setPreferredSize(new Dimension(500, 300));
        listOrderContent.setMinimumSize(new Dimension(500, 300));

        scrollPane = new JScrollPane(listOrderContent, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(new Dimension(300, 200));
        scrollPane.setPreferredSize(new Dimension(300, 200));
        scrollPane.setMinimumSize(new Dimension(300, 200));

        lblPrice = new JLabel("Price: ");

        lblOrder = new JLabel("Your order");

        btnExit = new JButton("Exit");
        btnExit.setSize(new Dimension(100, 25));
        btnExit.setPreferredSize(new Dimension(100, 25));
        btnExit.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnExit.setOpaque(true);
        btnExit.setBorderPainted(false);
        btnExit.setBackground(Color.decode("#518A3D"));

        btnPlaceOrder = new JButton("Exit");
        btnPlaceOrder.setSize(new Dimension(100, 25));
        btnPlaceOrder.setPreferredSize(new Dimension(100, 25));
        btnPlaceOrder.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnPlaceOrder.setOpaque(true);
        btnPlaceOrder.setBorderPainted(false);
        btnPlaceOrder.setBackground(Color.decode("#518A3D"));

        GRAY_BACKGROUND_COLOR = Color.decode("#2b2b2b");
    }

    private void initializeGUI() {

    }
}
