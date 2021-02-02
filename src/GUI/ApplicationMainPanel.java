package GUI;

import Controller.MainController;
import Entities.Supplier;
import GUI.AdminPanels.AdminMainPanel;
import GUI.UserPanels.UserMainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ApplicationMainPanel extends JPanel {
    private MainController controller;
    private LogInPanel pnlLogIn;
    private StorePanel pnlStore;
    private AdminMainPanel pnlAdminMain;
    private UserMainPanel pnlUserMain;


    public ApplicationMainPanel(MainController controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        pnlLogIn = new LogInPanel(this, controller);
        pnlStore = new StorePanel(this, controller);
        pnlAdminMain = new AdminMainPanel(this, controller);
        pnlUserMain = new UserMainPanel(this, controller);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600,800));
        setMaximumSize(new Dimension(600,800));
        setMinimumSize(new Dimension(600,800));
        setBackground(Color.decode("#2b2b2b"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(pnlLogIn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(pnlStore, gbc);
    }

    public void updateAdminView() {
        remove(pnlLogIn);
        remove(pnlStore);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600,800));
        setMaximumSize(new Dimension(600,800));
        setMinimumSize(new Dimension(600,800));
        setBackground(Color.decode("#2b2b2b"));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(pnlAdminMain, gbc);

        revalidate();
        repaint();
    }

    public void updateUserView() {
        remove(pnlLogIn);
        remove(pnlStore);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600,800));
        setMaximumSize(new Dimension(600,800));
        setMinimumSize(new Dimension(600,800));
        setBackground(Color.decode("#2b2b2b"));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(pnlUserMain, gbc);

        revalidate();
        repaint();
    }


    public void openDeleteProductFrame() {
        //controller.openDeleteProductFrame();
    }

    public ArrayList<String> getAllProducts() {
       // return controller.getAllProducts();
        ArrayList<String> arr = new ArrayList<>();
        return arr;
    }

    public ArrayList<String> getSearchedProducts(String searchedCode, String searchedSupplier, String searchedProduct) {
       // return controller.getSearchedProducts(searchedCode, searchedSupplier, searchedProduct);
        ArrayList<String> arr = new ArrayList<>();
        return arr;
    }

    public void openHandleProductFrame() {
       // controller.openHandleProductFrame();
    }

    public String getFirstname(String usernameLogin, String passwordLogin) {
        return controller.getFirstName(usernameLogin, passwordLogin);
    }

    public String getUsernameLogin() {
        return pnlLogIn.getUsernameLogin();
    }

    public char[] getPasswordLogin() {
        return pnlLogIn.getPassword();
    }

    public void updateUserFirstName(String userName) {
        pnlUserMain.updateFirstname(userName);
    }

    public void updateAdminFirstName(String name) {
        pnlAdminMain.updateFirstName(name);
    }

    public void openViewUsedDiscountsFrame() {
        controller.openViewUsedDiscountsFrame();
    }



    public ArrayList<String> getProductsForCustomers() {
        //return controller.getProductsForCustomers();
        ArrayList<String> arr = new ArrayList<>();
        return arr;
    }

}
