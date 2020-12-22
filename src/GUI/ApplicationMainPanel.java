package GUI;

import Controller.MainController;

import javax.swing.*;
import java.awt.*;

public class ApplicationMainPanel extends JPanel {
    private MainController controller;
    private JTabbedPane tabbedPane;
    private LogInPanel pnlLogIn;
    private StorePanel pnlStore;


    public ApplicationMainPanel(MainController controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        tabbedPane = new JTabbedPane();
        pnlLogIn = new LogInPanel(controller);
        pnlStore = new StorePanel();

        tabbedPane.addTab("Log In", pnlLogIn);
        tabbedPane.addTab("Store", pnlStore);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(500,750));
        setMaximumSize(new Dimension(500,750));
        setMinimumSize(new Dimension(500,750));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        add(tabbedPane, gbc);
        repaint();
    }


}
