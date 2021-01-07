package GUI;

import Controller.MainController;
import Database.DatabaseController;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {
    private MainController controller;
    private DatabaseController databaseController;
    private ApplicationMainPanel mainPanel;

    public ApplicationFrame(MainController controller, DatabaseController databaseController) {
        this.controller = controller;
        this.databaseController = databaseController;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        //Default JFrame initializations
        setTitle("Local Online Store");
        setSize(new Dimension(600,800));
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 800));
        setPreferredSize(new Dimension(600,800));
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());

        mainPanel = new ApplicationMainPanel(controller);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(mainPanel,gbc);

        pack();
        setLocation(new Point(500, 100));
    }

    public void setAdminViewTitle(){
        setTitle("Admin View");
    }

    public void setUserViewTitle(){
        setTitle("User View");
    }
}
