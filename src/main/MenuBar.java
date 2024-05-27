package main;

import dialogBoxes.CreateDialog;
import dialogBoxes.EditDialogue;
import entitys.base.Infantry;

import javax.swing.*;
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
                CreateDialog createDialog = new CreateDialog(gameWorld);
                createDialog.showCreateDialog();
            }
        });
    }

    private JMenu InitializeActionsWithEntitiesMenu() {
        JMenu addEntityMenu = new JMenu("Actions with Entities");

        JMenuItem addItem = new JMenuItem("Create new Entity");
        addItem.addActionListener((ActionEvent e) -> {
            CreateDialog createDialog = new CreateDialog(gameWorld);
            createDialog.showCreateDialog();
        });

        JMenuItem editEntity = new JMenuItem("Edit Selected Entity");
        editEntity.addActionListener((ActionEvent e) -> {
            editEntity();
        });

        JMenuItem copyEntity = new JMenuItem("Duplicate Selected Entity");
        copyEntity.addActionListener((ActionEvent e) -> {
            copyEntity();
        });

        JMenuItem showAllControllable = new JMenuItem("Show All Controllable Entities");
        showAllControllable.addActionListener((ActionEvent e) -> {
            showAllControllable();
        });

        JMenuItem showAll = new JMenuItem("Show All Entities");
        showAll.addActionListener((ActionEvent e) -> {
            showAll();
        });

        addEntityMenu.add(addItem);
        addEntityMenu.add(editEntity);
        addEntityMenu.add(copyEntity);
        addEntityMenu.add(showAll);

        return addEntityMenu;
    }

    private void showAllControllable() {
        ArrayList<Infantry> entityList = gameWorld.getAllControllableEntities();

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

    private void showAll() {
        ArrayList<Infantry> entityList = gameWorld.getEntities();

        StringBuilder message = new StringBuilder("List of All Entities:\n");
        if (!entityList.isEmpty()) {
            for (Infantry entity : entityList) {
                message.append("- ").append(entity.getID()).append("\n");
            }
            JOptionPane.showMessageDialog(null, message.toString(), "List of Entities", JOptionPane.INFORMATION_MESSAGE);
        } else {
            message.append("No Entities Created");
            JOptionPane.showMessageDialog(null, message.toString(), "List of Entities", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void copyEntity() {
        ArrayList<Infantry> entityList = gameWorld.getAllControllableEntities();
        for (var entity : entityList) {
            gameWorld.getEntities().add(entity.deepCopy());
        }
    }

    private void editEntity() {
        ArrayList<Infantry> entityList = gameWorld.getAllControllableEntities();
        if (entityList.size() == 1) {
            EditDialogue.showEditDialogue(entityList.getFirst());
        } else {
            JOptionPane.showMessageDialog(null, "You need to select one Entity", "Controllable Entities Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
