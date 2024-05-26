// Third Entity in the triple inheritance hierarchy

package entitys.BaseClasses;

import utils.Vector2;

public abstract class Kombat extends SquadLeader {
    // Entity features
    protected int ultraAttackDamage;
    protected int ultraAttackRadius;


    public Kombat(Vector2<Double> position) { super(position); }

    public void initializeKombatStats(int ultraAttackDamage, int ultraAttackRadius) {
        this.ultraAttackDamage = ultraAttackDamage; this.ultraAttackRadius = ultraAttackRadius;
    }

   public abstract void kombatAttack();

    @Override
    public Kombat deepCopy() {
        Kombat copy = (Kombat) super.deepCopy(); // Invoke deep copy of SquadLeader
        return copy;
    }
}