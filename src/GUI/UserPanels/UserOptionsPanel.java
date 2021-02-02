package GUI.UserPanels;

import Controller.MainController;
import Entities.OrderItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Vector;

public class UserOptionsPanel extends JPanel {
    private UserMainPanel userMainPanel;
    private MainController controller;

    private JButton btnShoppingCart;
    private Vector<JComponent> components;
    private Vector<OrderItem> orderItems;
    private JLabel lblPrice;


    public UserOptionsPanel(UserMainPanel userMainPanel, MainController controller){
        this.userMainPanel = userMainPanel;
        this.controller = controller;
        components = new Vector<>();
        this.orderItems = new Vector<>();

        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents(){
        btnShoppingCart = new JButton("Checkout");
        btnShoppingCart.setSize(new Dimension(150, 25));
        btnShoppingCart.setPreferredSize(new Dimension(150, 25));
        btnShoppingCart.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnShoppingCart.setOpaque(true);
        btnShoppingCart.setBorderPainted(false);
        btnShoppingCart.setBackground(Color.decode("#518A3D"));
    }

    private void initializeGUI(){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600,250));
        setMaximumSize(new Dimension(600,250));
        setMinimumSize(new Dimension(600,250));
        setBackground(Color.decode("#2b2b2b"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        add(btnShoppingCart, gbc);
    }

    private void registerListeners() {
        btnShoppingCart.addActionListener(new BtnShoppingCart());
    }


    public void updateOrderItems(Vector<OrderItem> orderItems) {
        this.orderItems = orderItems;

        for(int i = 0; i < components.size(); i++) {
            remove(components.get(i));
        }

        components.clear();
        if(lblPrice != null) {
            remove(lblPrice);
        }
        for(int i = 0; i < orderItems.size(); i++) {
            JLabel lbl = new JLabel(orderItems.get(i).getProductName());
            lbl.setForeground(Color.LIGHT_GRAY);
            components.add(lbl);

            lbl = new JLabel(String.valueOf(orderItems.get(i).getQuantity()));
            lbl.setForeground(Color.LIGHT_GRAY);
            components.add(lbl);

            JButton btnMinus = new JButton("-");
            btnMinus.setFont(new Font("Helvetica", Font.PLAIN, 12));
            btnMinus.setOpaque(true);
            btnMinus.setBorderPainted(false);
            btnMinus.setBackground(Color.decode("#518A3D"));
            btnMinus.addActionListener(new BtnMinus());
            components.add(btnMinus);

            JButton btnPlus = new JButton("+");
            btnPlus.setFont(new Font("Helvetica", Font.PLAIN, 12));
            btnPlus.setOpaque(true);
            btnPlus.setBorderPainted(false);
            btnPlus.setBackground(Color.decode("#518A3D"));
            btnPlus.addActionListener(new BtnPlus());
            components.add(btnPlus);

        }

        GridBagConstraints gbc = new GridBagConstraints();


        int gridy = 1;
        int gridx = 0;
        for(int i = 0; i < components.size(); i += 4) {
            gbc.insets = new Insets(5,0,0,10);
            gbc.gridwidth = 1;

            for(int j = 0; j < 4; j++) {
                gbc.gridy = gridy;
                gbc.gridx = gridx;
                add(components.get(i),gbc);

                gbc.gridx = ++gridx;
                add(components.get(i+1), gbc);

                gbc.gridx = ++gridx;
                add(components.get(i+2), gbc);

                gbc.gridx = ++gridx;
                add(components.get(i+3), gbc);

                gridy++;
                gridx = 0;
            }
        }
        double price = 0;
        for(int i = 0; i < orderItems.size(); i++) {
            price += (orderItems.get(i).getPrice() * orderItems.get(i).getQuantity());
        }
        DecimalFormat df = new DecimalFormat("#.##");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        price = Double.parseDouble(df.format(price));

        gbc.gridy = ++gridy;
        gbc.gridx = gridx;
        gbc.gridwidth = 4;
        gbc.ipady = 10;
        lblPrice = new JLabel("Price: " + price + "$");
        lblPrice.setForeground(Color.LIGHT_GRAY);
        add(lblPrice, gbc);
        remove(btnShoppingCart);

        gbc.gridy = ++gridy;
        btnShoppingCart.addActionListener(new BtnShoppingCart());
        add(btnShoppingCart, gbc);


        repaint();
        revalidate();
    }

    private class BtnPlus implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < components.size(); i++) {
                if(e.getSource() == components.get(i)) {
                    int index = (i / 4);
                    orderItems.get(index).setQuantity(orderItems.get(index).getQuantity()+1);
                    updateOrderItems(orderItems);
                }
            }
        }
    }

   private class BtnMinus implements ActionListener {
       @Override
       public void actionPerformed(ActionEvent e) {
           for(int i = 0; i < components.size(); i++) {
               if(e.getSource() == components.get(i)) {
                   int index = ((i+1) / 4);
                   orderItems.get(index).setQuantity(orderItems.get(index).getQuantity()-1);
                   updateOrderItems(orderItems);
               }
           }
       }
   }

    private class BtnShoppingCart implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.placeOrder(userMainPanel.getOrderId());
            for(OrderItem item : orderItems) controller.addOrderRow(userMainPanel.getOrderId(), item);
            for(JComponent comp : components) remove(comp);
            lblPrice.setText("Thank you for your order!");
            repaint();
            revalidate();
        }
    }
}
