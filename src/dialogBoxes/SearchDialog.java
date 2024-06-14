package dialogBoxes;

import bases.Base;
import entities.base.Infantry;
import main.GameWorld;
import utils.SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SearchDialog {
    private GameWorld gameWorld;

    public SearchDialog(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public void showSearchDialog() {
        JFrame frame = new JFrame("Search Entity");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Set dialogue window to center
        frame.setSize(new Dimension(200, 200));

        JPanel panel = new JPanel(new GridLayout(5, 2));

        // Adding all elements
        panel.add(new JLabel("Entity ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);

        String[] teamsOptions = SD.getAllTeams().toArray(new String[0]);
        JComboBox<String> teamsComboBox = new JComboBox<>(teamsOptions);
        panel.add(new JLabel("Select team:"));
        panel.add(teamsComboBox);

        String[] subclassOptions = SD.getAllEntityTypes().toArray(new String[0]);
        JComboBox<String> classComboBox = new JComboBox<>(subclassOptions);
        panel.add(new JLabel("Select class:"));
        panel.add(classComboBox);

        panel.add(new JLabel("Entity HP:"));
        JTextField HPField = new JTextField();
        panel.add(HPField);

        JButton okButton = new JButton("OK");
        okButton.addActionListener((ActionEvent e) -> {
            String idParam = idField.getText();

            String selectedTeamParam = (String) teamsComboBox.getSelectedItem();

            String selectedClassParam = (String) classComboBox.getSelectedItem();

            int HPParam;
            try {
                HPParam = Integer.parseInt(HPField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid HP input", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            searchEntity(idParam, selectedTeamParam, selectedClassParam, HPParam);

            frame.dispose();
        });
        panel.add(okButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void searchEntity(String id, String selectedTeam, String selectedClass, int HP) {
        ArrayList<Base> bases = gameWorld.getBases();
        Infantry foundedEntity = null; ArrayList<Base> basesThatContainsEntity = new ArrayList<>();

        for (var base : bases) {
            for (var entity : base.getEntities()) {
                if (isValid(entity, id, selectedTeam, selectedClass, HP)) {
                    foundedEntity  = entity;
                    basesThatContainsEntity.add(base);
                }
            }
        }

        if (foundedEntity != null) {
            StringBuilder message = new StringBuilder("Found Element: ");
            message.append(foundedEntity);
            message.append("Bases: ");
            for (var base : basesThatContainsEntity) {
                message.append(base.getName() + "\n");
            }

            JOptionPane.showMessageDialog(null, message.toString(), "Found Element", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        ArrayList<Infantry> nonBaseEntities = gameWorld.getEntitiesThatAreNOTInBase();
        for (var entity : nonBaseEntities) {
            if (isValid(entity, id, selectedTeam, selectedClass, HP)) {
                StringBuilder message = new StringBuilder("Found Element: ");
                message.append(entity);
                message.append("Base: = None" );
                JOptionPane.showMessageDialog(null, message.toString(), "Found Element", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "No Entities Found", "Found Element", JOptionPane.WARNING_MESSAGE);
    }
    private boolean isValid(Infantry entity, String id, String selectedTeam, String selectedClass, int HP) {
        if (entity.getID().equals(id)) {
            if (entity.getHP() == HP) {
                if (entity.getClass().toString().contains(selectedTeam)) {
                    if (entity.getClass().toString().contains(selectedClass)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}

