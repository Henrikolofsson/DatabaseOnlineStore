package GUI;

import Controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LogInPanel extends JPanel {
    private MainController controller;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
    private JButton btnLogIn;

    private JLabel lblNoAccount;

    private Color GRAY_BACKGROUND_COLOR;

    public LogInPanel(MainController controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        lblUsername = new JLabel("Username: ");

        txtUsername = new JTextField();
        txtUsername.setMinimumSize(new Dimension(80,20));
        txtUsername.setPreferredSize(new Dimension(80,20));

        lblPassword = new JLabel("Password: ");

        txtPassword = new JPasswordField();
        txtPassword.setMinimumSize(new Dimension(80,20));
        txtPassword.setPreferredSize(new Dimension(80,20));

        btnLogIn = new JButton("Log in");
        btnLogIn.setMinimumSize(new Dimension(80,30));
        btnLogIn.setPreferredSize(new Dimension(80,30));

        lblNoAccount = new JLabel("No account yet?");
        lblNoAccount.setForeground(Color.BLUE);
        lblNoAccount.setMinimumSize(new Dimension(100,30));
        lblNoAccount.setPreferredSize(new Dimension(100,30));

        GRAY_BACKGROUND_COLOR = Color.decode("#808285");
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setBackground(GRAY_BACKGROUND_COLOR);
        setPreferredSize(new Dimension(490, 720));
        setMaximumSize(new Dimension(490,720));
        setMinimumSize(new Dimension(490,720));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblUsername, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtPassword, gbc);

        gbc.insets = new Insets(40, 0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(btnLogIn, gbc);

        gbc.gridy = 6;
        add(lblNoAccount, gbc);

    }

    private void registerListeners() {
        btnLogIn.addActionListener(new LogInListener());
        lblNoAccount.addMouseListener(new LabelListener());
    }

    private class LogInListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }

    private class LabelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            System.out.println("HEJHEJHEJ");
            controller.openCreateAccountWindow();
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            lblNoAccount.setText("<HTML><U>No account yet?</U></HTML>");
            lblNoAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            lblNoAccount.setText("No account yet?");
            lblNoAccount.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

}
