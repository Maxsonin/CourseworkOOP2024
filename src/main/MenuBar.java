package main;

import Map.Map;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuBar extends JMenuBar {
    public MenuBar(Map map) {
        JMenu viewMenu = new JMenu("View");
        JMenuItem view25Item = new JMenuItem("25%");
        JMenuItem view50Item = new JMenuItem("50%");
        JMenuItem view75Item = new JMenuItem("75%");
        JMenuItem view100Item = new JMenuItem("100%");

        view25Item.addActionListener((ActionEvent e) -> map.setScalePercentage(0.25));
        view50Item.addActionListener((ActionEvent e) -> map.setScalePercentage(0.5));
        view75Item.addActionListener((ActionEvent e) -> map.setScalePercentage(0.75));
        view100Item.addActionListener((ActionEvent e) -> map.setScalePercentage(1));

        viewMenu.add(view25Item);
        viewMenu.add(view50Item);
        viewMenu.add(view75Item);
        viewMenu.add(view100Item);

        JMenu deleteMenu = new JMenu("Delete");

        JMenu deleteAllMenu = new JMenu("Delete All");

        this.add(viewMenu);

        this.add(deleteMenu);

        this.add(deleteAllMenu);
    }
}