// Second Entity in the triple inheritance hierarchy

package entities.base;

import utils.Vector2;

public abstract class SquadLeader extends Infantry {
    // Entity features
    protected int megaAttackDamage;
    protected int megaAttackRadius;


    public SquadLeader(Vector2<Double> position) { super(position); }

    public void initializeSquadLeaderStats(int megaAttackDamage, int megaAttackRadius) {
        this.megaAttackDamage = megaAttackDamage;
        this.megaAttackRadius = megaAttackRadius;
    }

    @Override
    public SquadLeader deepCopy() {
        SquadLeader copy = (SquadLeader) super.deepCopy(); // Invoke deep copy of Infantry
        return copy;
    }
}
