package ru.nsu.gorin.lab4.factory.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Вспомогательный класс для GUI
 */
public class BorderPanel extends JPanel {
    private final Border border;
    private final JPanel innerPanel;

    public void addIn(Component comp) {
        innerPanel.add(comp);
    }

    public BorderPanel(String title) {
        super();

        border = BorderFactory.createTitledBorder(title);

        setBorder(border);

        innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

        add(innerPanel);
    }
}
