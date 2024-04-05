package Entitys;

import map.Map;
import utils.Point;

public abstract class Kombat extends SquadLeader {

    protected int ultraAttackDamage;
    protected int ultraAttackRadius;

    public Kombat(Map map, Point<Double> position) {
        super(map, position);
    }

    public void InitializeStats(float velocity, int damage, int sightRadius, int megaAttackDamage, int megaAttackRadius, int ultraAttackDamage, int ultraAttackRadius) {
        InitializeStats(velocity, damage, sightRadius);
        this.megaAttackDamage = megaAttackDamage; this.megaAttackRadius = megaAttackRadius;
        this.ultraAttackDamage = ultraAttackDamage; this.ultraAttackRadius = ultraAttackRadius;
    }

   public abstract void UltraAttack();
}