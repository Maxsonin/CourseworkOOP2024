package dialogBoxes;

import utils.SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseBaseDialog {

    public ChooseBaseDialog() { }

    public String showChooseBaseDialog() {
        JDialog dialog = new JDialog((Frame) null, "Choose Base", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(2, 1));

        // Retrieve base options and create a combo box
        String[] basesOptions = SD.getAllBasesAndCapturePointsNames().toArray(new String[0]);
        JComboBox<String> basesComboBox = new JComboBox<>(basesOptions);

        // Add label and combo box to the panel
        panel.add(new JLabel("Select base:"));
        panel.add(basesComboBox);

        final String[] selectedBase = new String[1];

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedBase[0] = (String) basesComboBox.getSelectedItem();
                dialog.dispose();
            }
        });

        panel.add(okButton);

        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return selectedBase[0];
    }
}
