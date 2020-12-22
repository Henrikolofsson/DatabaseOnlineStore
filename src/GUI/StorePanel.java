package GUI;

import javax.swing.*;
import java.awt.*;

public class StorePanel extends JPanel {

    public StorePanel() {
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {

    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(490, 720));
        setMaximumSize(new Dimension(490,720));
        setMinimumSize(new Dimension(490,720));

    }
}
