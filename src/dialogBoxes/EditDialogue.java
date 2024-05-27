package dialogBoxes;

import entitys.base.Infantry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditDialogue {
    public static void showEditDialogue(Infantry entityToEdit) {

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

    private static void editEntity(Infantry entityToEdit, String id, boolean isControllable, double velocity, int damage) {
        entityToEdit.setID(id);
        entityToEdit.getControllableComponent().setControllable(isControllable);
        entityToEdit.setVelocity(velocity);
        entityToEdit.setDamage(damage);
    }
}
