package main;

import dialogBoxes.*;
import entitys.base.Infantry;
import utils.SD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MenuBar extends JMenuBar {
    GameWorld gameWorld;


    public MenuBar(GameWorld gameWorld) {
        this.gameWorld = gameWorld;

        this.add(InitializeActionsWithEntitiesMenu());
        this.add(InitializeSortManu());
        this.add(InitializeHelpMenu());
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

        ///////////////////////////////////////////////////////////////////////////////////////

        addEntityMenu.add(addItem);
        addEntityMenu.add(editEntity);
        addEntityMenu.add(copyEntity);

        return addEntityMenu;
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
            EditDialog.showEditDialogue(entityList.getFirst());
        } else {
            JOptionPane.showMessageDialog(null, "You need to select one Entity", "Controllable Entities Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JMenu InitializeSortManu() {
        // Separators
        JSeparator s1 = new JSeparator();
        s1.setOrientation(SwingConstants.HORIZONTAL);
        JSeparator s2 = new JSeparator();
        s2.setOrientation(SwingConstants.HORIZONTAL);
        JSeparator s3 = new JSeparator();
        s3.setOrientation(SwingConstants.HORIZONTAL);
        JSeparator s4 = new JSeparator();
        s4.setOrientation(SwingConstants.HORIZONTAL);

        ///////////////////////////////////////////////////////////////////////////////////////

        JMenu addEntityMenu = new JMenu("Sorting & Searching");

        JMenuItem showAllControllable = new JMenuItem("Show All Controllable Entities");
        showAllControllable.addActionListener((ActionEvent e) -> {
            showAllControllable();
        });

        JMenuItem showAll = new JMenuItem("Show All Entities");
        showAll.addActionListener((ActionEvent e) -> {
            showAll();
        });

        ///////////////////////////////////////////////////////////////////////////////////////

        JMenuItem showAllEntitiesThatBelongsToSpecificBase = new JMenuItem("Show All Entities That Belong To Specific Base");
        showAllEntitiesThatBelongsToSpecificBase.addActionListener((ActionEvent e) -> {
            ChooseBaseDialog chooseBaseDialog = new ChooseBaseDialog();
            String choosedBase = chooseBaseDialog.showChooseBaseDialog();
            showAllEntitiesFromBase(choosedBase);
        });

        JMenuItem showAllEntitiesThatAreNOTBelongsToAnyBase = new JMenuItem("Show All Entities That NOT Belong To Any Base");
        showAllEntitiesThatAreNOTBelongsToAnyBase.addActionListener((ActionEvent e) -> {
            ShowAllEntitiesThatAreNOTBelongsToAnyBase();
        });

        ///////////////////////////////////////////////////////////////////////////////////////

        JMenuItem countElement = new JMenuItem("Count Element");
        countElement.addActionListener((ActionEvent e) -> {
            countElement();
        });

        ///////////////////////////////////////////////////////////////////////////////////////

        JMenuItem searchEntity = new JMenuItem("Search Element");
        searchEntity.addActionListener((ActionEvent e) -> {
            searchElement();
        });

        ///////////////////////////////////////////////////////////////////////////////////////

        JMenuItem sortEntities = new JMenuItem("Sort Element");
        sortEntities.addActionListener((ActionEvent e) -> {
            SortDialog chooseSortDialog = new SortDialog();
            String choosedSortOption = chooseSortDialog.showSortDialog();
            sortEntities(choosedSortOption);
        });


        addEntityMenu.add(showAll);
        addEntityMenu.add(showAllControllable);
        addEntityMenu.add(s1);
        addEntityMenu.add(showAllEntitiesThatBelongsToSpecificBase);
        addEntityMenu.add(showAllEntitiesThatAreNOTBelongsToAnyBase);
        addEntityMenu.add(s2);
        addEntityMenu.add(countElement);
        addEntityMenu.add(s3);
        addEntityMenu.add(searchEntity);
        addEntityMenu.add(s4);
        addEntityMenu.add(sortEntities);


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
    private void showAllEntitiesFromBase(String choosedBase) {
        ArrayList<Infantry> baseEntityArray = gameWorld.getBaseByName(choosedBase).getEntities();

        StringBuilder message = new StringBuilder("List of All Entities that Belong to " + choosedBase + ":\n");
        if (!baseEntityArray.isEmpty()) {
            for (Infantry entity : baseEntityArray) {
                message.append("- ").append(entity.getID()).append("\n");
            }
            JOptionPane.showMessageDialog(null, message.toString(), "List of Entities", JOptionPane.INFORMATION_MESSAGE);
        } else {
            message.append("No Entities Are in this Base");
            JOptionPane.showMessageDialog(null, message.toString(), "List of Entities", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void ShowAllEntitiesThatAreNOTBelongsToAnyBase() {
        ArrayList<Infantry> freeEntities = gameWorld.getEntitiesThatAreNOTInBase();

        StringBuilder message = new StringBuilder("List of All Entities that Belong Are NOT Belong to Any Base:\n");
        if (!freeEntities.isEmpty()) {
            for (Infantry entity : freeEntities) {
                message.append("- ").append(entity.getID()).append("\n");
            }
            JOptionPane.showMessageDialog(null, message, "List of Entities", JOptionPane.INFORMATION_MESSAGE);
        } else {
            message.append("No Entities Are in this Base");
            JOptionPane.showMessageDialog(null, message, "List of Entities", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void countElement() {
        ArrayList<Infantry> entities = gameWorld.getEntities();

        int entitiesWithLessThenHalfOfHP = 0;
        for (var entity : entities) {
            if (entity.getHP() < 50) {
                entitiesWithLessThenHalfOfHP++;
            }
        }

        int controllableEntities = gameWorld.getAllControllableEntities().size();


        int entitiesWithHighDamage = 0; // Damage > 20
        for (var entity : entities) {
            if (entity.getDamage() > 20) {
                entitiesWithHighDamage++;
            }
        }

        StringBuilder message = new StringBuilder("Entities with Less then Half of HP: " + entitiesWithLessThenHalfOfHP + "\n" +
                                                  "Controllable Entities: " + controllableEntities + "\n" +
                                                  "Entities with High Damage(>20): " + entitiesWithHighDamage + "\n");
        JOptionPane.showMessageDialog(null, message.toString(), "Count of Entities", JOptionPane.INFORMATION_MESSAGE);
    }
    private void searchElement() {
        SearchDialog searchDialog = new SearchDialog(gameWorld);
        searchDialog.showSearchDialog();
    }
    private void sortEntities(String choosedSortOption) {
        ArrayList<Infantry> entitiesCopy = (ArrayList<Infantry>) gameWorld.getEntities().clone();

        // Define comparators for each attribute
        Comparator<Infantry> hpComparator = new Comparator<Infantry>() {
            @Override
            public int compare(Infantry e1, Infantry e2) {
                return Integer.compare(e1.getHP(), e2.getHP());
            }
        };

        Comparator<Infantry> idComparator = new Comparator<Infantry>() {
            @Override
            public int compare(Infantry e1, Infantry e2) {
                return e1.getID().compareTo(e2.getID()); // fix
            }
        };

        Comparator<Infantry> damageComparator = new Comparator<Infantry>() {
            @Override
            public int compare(Infantry e1, Infantry e2) {
                return Integer.compare(e1.getDamage(), e2.getDamage());
            }
        };


        switch (choosedSortOption) {
            case "HP":
                Collections.sort(entitiesCopy, hpComparator);
                break;
            case "ID":
                Collections.sort(entitiesCopy, idComparator);
                break;
            case "Damage":
                Collections.sort(entitiesCopy, damageComparator);
                break;
        }


        StringBuilder message = getStringBuilder(choosedSortOption, entitiesCopy);
        JOptionPane.showMessageDialog(null, message.toString(), "List of Sorted Entities", JOptionPane.INFORMATION_MESSAGE);
    }

    private static StringBuilder getStringBuilder(String choosedSortOption, ArrayList<Infantry> entitiesCopy) {
        StringBuilder message = new StringBuilder("List of Sorted Entities by" + choosedSortOption + ":\n");
        for (Infantry entity : entitiesCopy) {
            message.append(entity.getID() + ": ");
            switch (choosedSortOption) {
                case "HP":
                    message.append(entity.getHP() + ";\n");
                    break;
                case "ID":
                    message.append(entity.getID() + ";\n");
                    break;
                case "Damage":
                    message.append(entity.getDamage() + ";\n");
                    break;
            }
        }
        return message;
    }

    private JMenu InitializeHelpMenu() {
        JMenu helpMenu = new JMenu("Help");

        JMenuItem showAllControllable = new JMenuItem("Show Shortcuts");
        showAllControllable.addActionListener((ActionEvent e) -> {
            showShortcuts();
        });

        helpMenu.add(showAllControllable);

        return helpMenu;
    }
    private void showShortcuts() {
        ArrayList<String> shortcuts = new ArrayList<>();

        shortcuts.add("INSERT - insert a new Entity");
        shortcuts.add("DELETE - delete all Controllable Entities");
        shortcuts.add("ESCAPE - UnControll all Controllable Entities");
        shortcuts.add("T - Test if Keyboard is Working");

        StringBuilder message = new StringBuilder("List of Shortcuts:\n");
        for (String shortcut : shortcuts) {
            message.append(shortcut).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString(), "List of Entities", JOptionPane.INFORMATION_MESSAGE);
    }
}
