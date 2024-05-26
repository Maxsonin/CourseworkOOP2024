package main;

import bases.NaziBase;
import dialogBoxes.CreateDialog;
import dialogBoxes.EditDialogue;
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

        JMenuItem showAll = new JMenuItem("Show All Controllable Entities");
        showAll.setAction(new AbstractAction("Show All") {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAll();
            }
        });

        addEntityMenu.add(addItem);
        addEntityMenu.add(editEntity);
        addEntityMenu.add(copyEntity);
        addEntityMenu.add(showAll);

        return addEntityMenu;
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

    private void copyEntity() {
        ArrayList<Infantry> entityList = gameWorld.getAllControllableEntitiesFromHierarchy();
        for (var entity : entityList) {
            copyElement(entity);
        }
    }
    private void copyElement(Infantry objToCopy) {
        gameWorld.getNaziBaseByName(SD.YaltaNaziBase).getEntities().add(objToCopy.deepCopy());
    }

    private void editEntity() {
        ArrayList<Infantry> entityList = gameWorld.getAllControllableEntitiesFromHierarchy();
        if (entityList.size() == 1) {
            EditDialogue.showEditDialogue(entityList.getFirst());
        } else {
            JOptionPane.showMessageDialog(null, "You need to select one Entity", "Controllable Entities Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
