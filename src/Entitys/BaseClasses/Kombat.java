// Third Entity in the triple inheritance hierarchy

package Entitys.BaseClasses;

import Utils.Vector2;

public abstract class Kombat extends SquadLeader {
    // Entity features
    protected int ultraAttackDamage;
    protected int ultraAttackRadius;


    public Kombat(Vector2<Double> position) { super(position); }

    public void initializeKombatStats(int ultraAttackDamage, int ultraAttackRadius) {
        this.ultraAttackDamage = ultraAttackDamage; this.ultraAttackRadius = ultraAttackRadius;
    }

   public abstract void kombatAttack();
}