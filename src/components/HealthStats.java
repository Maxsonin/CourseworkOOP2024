// Component that adds all health related stuff

package components;

import entitys.Entity;

import java.awt.*;

public class HealthStats {
    private Entity parentObj;

    private final int maxHealth;
    private int health;


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
        Font newFont = originalFont.deriveFont(Font.BOLD, 14f);
        g.setFont(newFont);

        FontMetrics fm = g.getFontMetrics();

        // HP Bar
        int imgWidth = (int) (parentObj.getImgSize().getWidth() * parentObj.getScaleFactor());
        int barAdditionalSize = (int) (imgWidth * 0.50); // +50%

        int barWidth = imgWidth + barAdditionalSize;
        int barHeight = (int) (fm.getHeight() * parentObj.getScaleFactor());
        int currentBarWidth = (int) (barWidth * ((double) health / maxHealth));

        int barPosX = (int) Math.round(parentObj.getPosition().getX() - (double) barAdditionalSize / 2);
        int barPosY = (int) Math.round(parentObj.getPosition().getY() - fm.getHeight());

        // Set color based on health
        if (health <= maxHealth * 0.25) {
            g.setColor(Color.RED);
        } else if (health <= maxHealth * 0.75) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.GREEN);
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
}
