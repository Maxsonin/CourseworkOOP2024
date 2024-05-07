package main;

import bases.NaziBase;
import entitys.BaseClasses.Infantry;
import utils.SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MenuBar extends JMenuBar {
    GameWorld gameWorld;


    public MenuBar(GameWorld gameWorld) {
        this.gameWorld = gameWorld;

        this.add(InitializeActionsWithEntitiesMenu());

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0), "showInsertDialog");
        this.getActionMap().put("showInsertDialog", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                showInsertDialog();
            }
        });
    }

    private JMenu InitializeActionsWithEntitiesMenu() {
        JMenu addEntityMenu = new JMenu("Actions with Entities");

        JMenuItem addItem = new JMenuItem("Insert");
        addItem.addActionListener((ActionEvent e) -> {
            showInsertDialog();
        });

        JMenuItem editEntity = new JMenuItem("Edit Entity");
        editEntity.addActionListener((ActionEvent e) -> {
            editEntity();
        });

        JMenuItem showAll = new JMenuItem("Show All");
        showAll.setAction(new AbstractAction("Show All") {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAll();
            }
        });

        addEntityMenu.add(addItem);
        addEntityMenu.add(editEntity);
        addEntityMenu.add(showAll);

        return addEntityMenu;
    }

    private void editEntity() {
        ArrayList<Infantry> entityList = gameWorld.getAllControllableEntitiesFromHierarchy();
        if (entityList.size() == 1) {
            showEditDialogue(entityList.getFirst());
        } else {
            JOptionPane.showMessageDialog(null, "You need to select one Entity", "Controllable Entities", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void showAll() {
        ArrayList<Infantry> entityList = gameWorld.getAllControllableEntitiesFromHierarchy();

        StringBuilder message = new StringBuilder("List of Controllable Entities:\n");

        if (!entityList.isEmpty()) {
            for (Infantry entity : entityList) {
                message.append("- ").append(entity.getID()).append("\n");
            }
            JOptionPane.showMessageDialog(null, message.toString(), "Controllable Entities", JOptionPane.INFORMATION_MESSAGE);
        } else {
            message.append("None of the Entities are Controllable");
            JOptionPane.showMessageDialog(null, message.toString(), "Controllable Entities", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void showEditDialogue(Infantry entityToEdit) {
        JFrame frame = new JFrame("Edit Entity");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Set dialogue window to center
        frame.setSize(new Dimension(200, 200));

        JPanel panel = new JPanel(new GridLayout(5, 2));

        // Adding all elements
        panel.add(new JLabel("Entity ID:"));
        JTextField idField = new JTextField(entityToEdit.getID());
        panel.add(idField);

        panel.add(new JLabel("Is Controllable?:"));
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(entityToEdit.getControllableComponent().isControllable());
        panel.add(checkBox);

        panel.add(new JLabel("Entity Velocity:"));
        JTextField velocityField = new JTextField(String.valueOf(entityToEdit.getVelocity()));
        panel.add(velocityField);

        panel.add(new JLabel("Entity Damage:"));
        JTextField damageField = new JTextField(String.valueOf(entityToEdit.getDamage()));
        panel.add(damageField);

        JButton okButton = new JButton("OK");
        okButton.addActionListener((ActionEvent e) -> {
            String idParam = idField.getText();
            boolean isControllableParam = checkBox.isSelected();

            double velocityParam;
            try {
                velocityParam = Double.parseDouble(velocityField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid velocity input", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int damageParam;
            try {
                damageParam = Integer.parseInt(damageField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid damage input", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            editEntity(entityToEdit, idParam, isControllableParam, velocityParam, damageParam);

            frame.dispose();
        });
        panel.add(okButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    private void editEntity(Infantry entityToEdit, String id, boolean isControllable, double velocity, int damage) {
        entityToEdit.setID(id);
        entityToEdit.getControllableComponent().setControllable(isControllable);
        entityToEdit.setVelocity(velocity);
        entityToEdit.setDamage(damage);
    }

    private void showInsertDialog() {
        JFrame frame = new JFrame("Insert Parameters");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Set dialogue window to center
        frame.setSize(new Dimension(200, 200));

        JPanel panel = new JPanel(new GridLayout(7, 2));

        // Adding all elements
        panel.add(new JLabel("Entity ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Is Controllable?:"));
        JCheckBox checkBox = new JCheckBox();
        panel.add(checkBox);

        String[] baseNames = SD.getAllNaziBasesNames().toArray(new String[0]);
        JComboBox<String> baseNamesComboBox = new JComboBox<>(baseNames);
        panel.add(new JLabel("Select Base:"));
        panel.add(baseNamesComboBox);

        String[] subclassOptions = SD.getAllEntityTypes().toArray(new String[0]);
        JComboBox<String> classComboBox = new JComboBox<>(subclassOptions);
        panel.add(new JLabel("Select class:"));
        panel.add(classComboBox);

        panel.add(new JLabel("Entity Velocity:"));
        JTextField velocityField = new JTextField();
        panel.add(velocityField);

        panel.add(new JLabel("Entity Damage:"));
        JTextField damageField = new JTextField();
        panel.add(damageField);

        JButton okButton = new JButton("OK");
        okButton.addActionListener((ActionEvent e) -> {
            String idParam = idField.getText();
            boolean isControllableParam = checkBox.isSelected();
            String selectedBaseParam = (String) baseNamesComboBox.getSelectedItem();
            String selectedClassParam = (String) classComboBox.getSelectedItem();

            double velocityParam;
            try {
                velocityParam = Double.parseDouble(velocityField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid velocity input", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int damageParam;
            try {
                damageParam = Integer.parseInt(damageField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid damage input", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            createEntity(idParam, isControllableParam, selectedBaseParam, selectedClassParam, velocityParam, damageParam);

            frame.dispose();
        });
        panel.add(okButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    private void createEntity(String id, boolean isControllable, String selectedBase, String selectedClass, double velocity, int damage) {
        NaziBase base = gameWorld.getNaziBaseByName(selectedBase);
        base.addCustomEntity(id, isControllable, selectedClass, velocity, damage);
    }
}
