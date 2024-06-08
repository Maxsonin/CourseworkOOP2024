package dialogBoxes;

import main.GameWorld;
import utils.SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortDialog {
    public SortDialog() { }

    public String showSortDialog() {
        JDialog dialog = new JDialog((Frame) null, "Choose Sort", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null); // Set dialog window to center
        dialog.setSize(new Dimension(200, 200));

        JPanel panel = new JPanel(new GridLayout(2, 1));

        // Adding all elements
        String[] sortOptions = SD.getSortOptions().toArray(new String[0]);
        JComboBox<String> sortComboBox = new JComboBox<>(sortOptions);
        panel.add(new JLabel("Select by what to sort:"));
        panel.add(sortComboBox);

        // Holder for the selected base name
        final String[] selectedSortOption = new String[1];

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSortOption[0] = (String) sortComboBox.getSelectedItem();
                dialog.dispose();
            }
        });
        panel.add(okButton);

        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setVisible(true);

        return selectedSortOption[0];
    }
}
