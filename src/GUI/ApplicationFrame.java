package GUI;

import Controller.MainController;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {
    private MainController controller;
    private ApplicationMainPanel mainPanel;

    public ApplicationFrame(MainController controller) {
        this.controller = controller;
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
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,2, 1, 0));

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


}
