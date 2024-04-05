package Bases;

import Entitys.NaziAttacker;
import Entitys.NaziKombat;
import Entitys.NaziSquadLeader;
import map.Map;
import utils.Loader;
import utils.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NaziBase extends Base {
    private ArrayList<NaziAttacker> attackers;
    private ArrayList<NaziSquadLeader> squadLeaders;
    private ArrayList<NaziKombat> kombats;
    private final int numOfAttackers = 5;
    private final int numOfSquadLeaders = 1;
    private final int numOfKombats = 1;
    private final Point<Double> spawnPos;

    Map map;

    private long lastUpdateTime = System.currentTimeMillis();

    public NaziBase(Map map, Point<Double> position) {
        super(position);
        this.map = map;
        InitializeImg("naziBase.png", new Point<>(270, 187));

        img = Loader.GetSprite(fileName);

        attackers = new ArrayList<>();
        squadLeaders = new ArrayList<>();
        kombats = new ArrayList<>();

        spawnPos = new Point<>(position.getX() + imgSize.getX() / 2 , position.getY() + imgSize.getY() / 2);
    }

    @Override
    public void AddEntity() {
        if (kombats.size() < numOfKombats) {
            kombats.add(new NaziKombat(map, spawnPos.getCopy()));
        } else if (squadLeaders.size() < numOfSquadLeaders) {
            squadLeaders.add(new NaziSquadLeader(map, spawnPos.getCopy()));
        } else if (attackers.size() < numOfAttackers) {
            attackers.add(new NaziAttacker(map, spawnPos.getCopy()));
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(img, (int)(position.getX() - 0), (int)(position.getY() - 0), imgSize.getX(), imgSize.getY(), null);

        for (NaziAttacker attacker : attackers) {
            attacker.Draw(g);
        }
        for (NaziSquadLeader squadLeader : squadLeaders) {
            squadLeader.Draw(g);
        }
        for (NaziKombat kombat : kombats) {
            kombat.Draw(g);
        }

        ((Graphics2D)g).setStroke(new BasicStroke(8));
        g.setColor(Color.BLACK);
        g.drawOval((int)(position.getX() - 10), (int)(position.getY() - 25), 35, 35);

        g.setColor(baseColor);
        g.fillOval((int)(position.getX() - 10), (int)(position.getY() - 25), 35, 35);

        // Вывод текста
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString(baseName, (int)(position.getX() + 40), (int)(position.getY() + 0));
    }


    @Override
    public void Update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdateTime >= 2500) {
            AddEntity();
            lastUpdateTime = currentTime;
        }

        for (NaziKombat kombat : kombats) {
            kombat.Update();
        }

        for (NaziSquadLeader squadLeader : squadLeaders) {
            squadLeader.Update();
        }

        for (NaziAttacker attacker : attackers) {
            attacker.Update();
        }
    }
}