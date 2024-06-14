package main;

import dialogBoxes.*;
import entities.base.Infantry;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;

public class MenuBar extends JMenuBar {
    GameWorld gameWorld;

    {  // Requirement №4
        this.add(InitializeActionsWithEntitiesMenu());
        this.add(InitializeSortMenu());
        this.add(InitializeHelpMenu());
    }

    public MenuBar(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    // Utility method to create menu items and add action listeners
    JMenuItem createMenuItem(String text, Runnable action) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(e -> action.run());
        return menuItem;
    }

    // Actions With Entities Menu
    private JMenu InitializeActionsWithEntitiesMenu() {
        JMenu addEntityMenu = new JMenu("Actions with Entities");

        addEntityMenu.add(createMenuItem("Create new Entity", () -> {
            CreateDialog createDialog = new CreateDialog(gameWorld);
            createDialog.showCreateDialog();
        }));
        addEntityMenu.add(createMenuItem("Edit Selected Entity", this::editEntity));
        addEntityMenu.add(createMenuItem("Duplicate Selected Entity", this::duplicateEntity));

        return addEntityMenu;
    }
    private void duplicateEntity() { // Requirement №7
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
            JOptionPane.showMessageDialog(null, "You need to select ONLY ONE Entity", "Controllable Entities Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Sort & Search Menu
    private JMenu InitializeSortMenu() {
        JMenu sortSearchMenu = new JMenu("Sorting & Searching");

        sortSearchMenu.add(createMenuItem("Show All Entities", this::showAll));
        sortSearchMenu.add(createMenuItem("Show All Controllable Entities", this::showAllControllable));
        sortSearchMenu.add(new JSeparator(SwingConstants.HORIZONTAL));

        sortSearchMenu.add(createMenuItem("Show All Entities That Belong To Specific Base", () -> {
            ChooseBaseDialog chooseBaseDialog = new ChooseBaseDialog();
            String choosedBase = chooseBaseDialog.showChooseBaseDialog();
            showAllEntitiesFromBase(choosedBase);
        }));
        sortSearchMenu.add(createMenuItem("Show All Entities That NOT Belong To Any Base", this::ShowAllEntitiesThatAreNOTBelongsToAnyBase));
        sortSearchMenu.add(new JSeparator(SwingConstants.HORIZONTAL));

        sortSearchMenu.add(createMenuItem("Count Element", this::countElement));
        sortSearchMenu.add(new JSeparator(SwingConstants.HORIZONTAL));

        sortSearchMenu.add(createMenuItem("Search Element", this::searchElement));
        sortSearchMenu.add(new JSeparator(SwingConstants.HORIZONTAL));

        sortSearchMenu.add(createMenuItem("Sort Element", () -> {
            SortDialog chooseSortDialog = new SortDialog();
            String choosedSortOption = chooseSortDialog.showSortDialog();
            sortEntities(choosedSortOption);
        }));

        return sortSearchMenu;
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
    private void showAllEntitiesFromBase(String chosenBase) {
        ArrayList<Infantry> baseEntityArray = gameWorld.getBaseByName(chosenBase).getEntities();

        StringBuilder message = new StringBuilder("List of All Entities that Belong to " + chosenBase + ":\n");
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
            message.append("No Entities are outside the bases");
            JOptionPane.showMessageDialog(null, message, "List of Entities", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void countElement() { // Requirement №42-43
        ArrayList<Infantry> entities = gameWorld.getEntities();

        long entitiesWithLessThenHalfOfHP = entities.stream()
                .filter(entity -> entity.getHP() < 50)
                .count();

        int controllableEntities = gameWorld.getAllControllableEntities().size();

        long entitiesWithHighDamage = entities.stream()
                .filter(entity -> entity.getDamage() > 20)
                .count();

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
        ArrayList<Infantry> entitiesCopy = new ArrayList<>(gameWorld.getEntities());

        // Define comparators for each attribute using lambdas
        Comparator<Infantry> hpComparator = Comparator.comparingInt(Infantry::getHP);
        Comparator<Infantry> idComparator = Comparator.comparingInt(e -> Integer.parseInt(e.getID()));
        Comparator<Infantry> damageComparator = Comparator.comparingInt(Infantry::getDamage);

        switch (choosedSortOption) {
            case "HP":
                entitiesCopy.sort(hpComparator);
                break;
            case "ID":
                entitiesCopy.sort(idComparator);
                break;
            case "Damage":
                entitiesCopy.sort(damageComparator);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort option: " + choosedSortOption);
        }

        // Build and show the message with sorted entities
        StringBuilder message = getSortedEntitiesMessage(choosedSortOption, entitiesCopy);
        JOptionPane.showMessageDialog(null, message.toString(), "List of Sorted Entities", JOptionPane.INFORMATION_MESSAGE);
    }
    private StringBuilder getSortedEntitiesMessage(String choosedSortOption, ArrayList<Infantry> entitiesCopy) {
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

    // Help Menu
    private JMenu InitializeHelpMenu() {
        JMenu helpMenu = new JMenu("Help");

        helpMenu.add(createMenuItem("Show Shortcuts", this::showShortcuts));

        return helpMenu;
    }
    private void showShortcuts() {
        ArrayList<String> shortcuts = new ArrayList<>();

        shortcuts.add("INSERT - insert a new Entity");
        shortcuts.add("DELETE - delete all Controllable Entities");
        shortcuts.add("ESCAPE - UnControll all Controllable Entities");
        shortcuts.add("T - Test if Keyboard is Working");
        shortcuts.add("WASD - Move view");
        shortcuts.add("H - move all entities to main base");

        StringBuilder message = new StringBuilder("List of Shortcuts:\n");
        for (String shortcut : shortcuts) {
            message.append(shortcut).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString(), "List of Entities", JOptionPane.INFORMATION_MESSAGE);
    }
}