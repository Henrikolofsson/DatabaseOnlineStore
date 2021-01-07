package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogInPanel extends JPanel {
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
    private JButton btnLogIn;
    private ApplicationMainPanel applicationMainPanel;

    private JLabel lblNoAccount;

    private Color GRAY_BACKGROUND_COLOR;

    public LogInPanel(ApplicationMainPanel applicationMainPanel) {
        this.applicationMainPanel = applicationMainPanel;
        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        lblUsername = new JLabel("Username: ");
        lblUsername.setForeground(Color.LIGHT_GRAY);

        txtUsername = new JTextField();
        txtUsername.setMinimumSize(new Dimension(80,20));
        txtUsername.setPreferredSize(new Dimension(80,20));

        lblPassword = new JLabel("Password: ");
        lblPassword.setForeground(Color.LIGHT_GRAY);

        txtPassword = new JPasswordField();
        txtPassword.setMinimumSize(new Dimension(80,20));
        txtPassword.setPreferredSize(new Dimension(80,20));

        btnLogIn = new JButton("Log in");
        btnLogIn.setMinimumSize(new Dimension(60,20));
        btnLogIn.setPreferredSize(new Dimension(60,20));
        btnLogIn.setFont(new Font("Helvetica", Font.PLAIN, 9));
        btnLogIn.setOpaque(true);
        btnLogIn.setBorderPainted(false);
        btnLogIn.setBackground(Color.decode("#518A3D"));

        lblNoAccount = new JLabel("No account yet?");
        lblNoAccount.setForeground(Color.decode("#40BDBD"));
        lblNoAccount.setMinimumSize(new Dimension(100,30));
        lblNoAccount.setPreferredSize(new Dimension(100,30));

        GRAY_BACKGROUND_COLOR = Color.decode("#808285");
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setBackground(GRAY_BACKGROUND_COLOR);
        setPreferredSize(new Dimension(600, 100));
        setMaximumSize(new Dimension(600,100));
        setMinimumSize(new Dimension(600,100));
        setBackground(Color.decode("#2b2b2b"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblUsername, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtUsername, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(lblPassword, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        add(txtPassword, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        add(btnLogIn, gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        add(lblNoAccount, gbc);
    }

    private void registerListeners() {
        btnLogIn.addActionListener(new BtnLoginListener());
        lblNoAccount.addMouseListener(new LabelListener());
    }

    public String getUsernameLogin(){
        return txtUsername.getText();
    }

    public String getPasswordLogin() {
        char[] charPass = txtPassword.getPassword();
        return String.valueOf(charPass);
    }

    private class BtnLoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String userNameLogin = txtUsername.getText();
            char[] charPass = txtPassword.getPassword();
            String passwordLogin = String.valueOf(charPass);

            if(!applicationMainPanel.isUserNormal(userNameLogin, passwordLogin) && applicationMainPanel.isUserAdmin(userNameLogin, passwordLogin)) {
                applicationMainPanel.updateAdminView();
                applicationMainPanel.updateAdminFirstName();
                applicationMainPanel.setAdminViewTitle();
            } else if(applicationMainPanel.isUserNormal(userNameLogin, passwordLogin) && !applicationMainPanel.isUserAdmin(userNameLogin, passwordLogin)){
                applicationMainPanel.updateUserView();
                applicationMainPanel.updateUserFirstName();
                applicationMainPanel.setUserViewTitle();
            } else {
                JOptionPane.showMessageDialog(null, "Wrong login credentials!");
            }

        }
    }

    private class LabelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            applicationMainPanel.openCreateAccountWindow();
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
