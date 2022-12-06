package com.raven.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author garcia_d
 */
public class TableHeader extends JLabel {

    /**
     * 
     * @param text 
     */
    public TableHeader(String text) {
        super(text);
        setOpaque(true);
        setBackground(new Color(51, 51, 51));
        setFont(new Font("Segoe UI", 1, 12));
        setForeground(new Color(204, 204, 204));
        setBorder(new EmptyBorder(10, 5, 10, 5));
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.setColor(new Color(65, 65, 65));
        grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }
}
