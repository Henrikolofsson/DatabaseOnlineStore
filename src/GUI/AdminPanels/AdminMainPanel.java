package GUI.AdminPanels;

import Controller.MainController;
import Entities.Admin;
import Entities.Supplier;
import GUI.ApplicationMainPanel;
import Interfaces.PanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminMainPanel extends JPanel {
    private PanelListener panelListener;
    private ApplicationMainPanel applicationMainPanel;
    private OptionsPanel optionsPanel;
    private AdminStorePanel adminStorePanel;

    public AdminMainPanel(ApplicationMainPanel applicationMainPanel, MainController controller){
        this.applicationMainPanel = applicationMainPanel;
        this.panelListener = controller;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents(){
        this.optionsPanel = new OptionsPanel((MainController) panelListener,this);
        this.adminStorePanel = new AdminStorePanel(panelListener,this);
    }

    private void initializeGUI(){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600,800));
        setMaximumSize(new Dimension(600,800));
        setMinimumSize(new Dimension(600,800));
        setBackground(Color.decode("#3b3b3b"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(optionsPanel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(adminStorePanel, gbc);
    }


    public ArrayList<String> getAllProducts(){
        return applicationMainPanel.getAllProducts();
    }


    public ArrayList<String> getSearchedProducts(String searchedCode, String searchedSupplier, String searchedProduct) {
        return applicationMainPanel.getSearchedProducts(searchedCode, searchedSupplier, searchedProduct);
    }


    public void updateFirstName(String name) {
        adminStorePanel.updateFirstname(name);
    }

}
