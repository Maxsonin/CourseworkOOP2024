package dialogBoxes;

import bases.NaziBase;
import main.Game;
import main.GameWorld;
import utils.SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreateDialog {
    private GameWorld gameWorld;

    public CreateDialog(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public void showCreateDialog() {
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
