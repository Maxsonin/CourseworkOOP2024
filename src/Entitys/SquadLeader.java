package Entitys;

import map.Map;
import utils.Loader;
import utils.Point;

public abstract class SquadLeader extends Attacker {

    protected int megaAttackDamage;
    protected int megaAttackRadius;

    public SquadLeader(Map map, Point<Double> position) {
        super(map, position);
    }

    public void InitializeStats(float velocity, int damage, int sightRadius, int megaAttackDamage, int megaAttackRadius) {
        InitializeStats(velocity, damage, sightRadius);
        this.megaAttackDamage = megaAttackDamage;
        this.megaAttackRadius = megaAttackRadius;
    }

    public abstract void MegaAttack();
}
