package components;

import entities.Entity;
import utils.Vector2;

import java.awt.*;
import java.io.Serializable;

public class HealthStats implements Serializable {
    private Entity parentObj;

    private final int maxHealth;
    private int health;

    private float textSize = 15f;
    private Color barColor = null; // New field to store the current health bar color

    private Vector2<Integer> barPos;

    public HealthStats(Entity parentObj, int maxHealth) {
        this.parentObj = parentObj;
        this.maxHealth = maxHealth;
        health = maxHealth;
    }

    public void drawHealthStats(Graphics g) {
        // Some string settings required for HP bar
        String healthText = Integer.toString(health);
        Font originalFont = g.getFont();

        // Derive a new font with size 14 and bold style
        Font newFont = originalFont.deriveFont(Font.BOLD, textSize * (float) Math.ceil(parentObj.getScaleFactor()));
        g.setFont(newFont);

        FontMetrics fm = g.getFontMetrics();

        // HP Bar
        int imgWidth = (int) (parentObj.getImgSize().getWidth() * parentObj.getScaleFactor());
        int barAdditionalSize = (int) (imgWidth * 0.50); // +50%

        int barWidth = imgWidth + barAdditionalSize;
        int barHeight = fm.getHeight();
        int currentBarWidth = (int) (barWidth * ((double) health / maxHealth));

        int barPosX = (int) Math.round(parentObj.getPosition().getX() - (double) barAdditionalSize / 2);
        int barPosY = (int) Math.round(parentObj.getPosition().getY() - fm.getHeight());
        barPos = new Vector2<>(barPosX, barPosY);

        // Set color based on health or use custom color if set
        if (barColor != null) {
            g.setColor(barColor);
        }

        // Draw HP Bar
        g.fillRect(barPosX, barPosY, currentBarWidth, barHeight);

        // HP Text
        g.setColor(Color.BLACK);
        int textPosX = (int) Math.round(parentObj.getPosition().getX() + (double) imgWidth / 2 - (double) fm.stringWidth(healthText) / 2);
        int textPosY = (int) Math.round(parentObj.getPosition().getY() - fm.getHeight() * 0.25);
        g.drawString(healthText, textPosX, textPosY);

        // Restore original font
        g.setFont(originalFont);
    }

    public int getHealth() { return health; }

    public void changeHealth(int changingValue) {
        health += changingValue;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        } else if (this.health < 0) {
            this.health = 0;
        }
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        } else if (this.health < 0) {
            this.health = 0;
        }
    }

    public void setBarColor(Color color) {
        this.barColor = color;
    }

    public Color getBarColor() {
        return barColor;
    }

    public Vector2<Integer> getBarPosition() { return barPos; }
}
