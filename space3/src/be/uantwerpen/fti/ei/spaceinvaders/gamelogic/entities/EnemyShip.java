package be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.EnemyEntity;

public abstract class EnemyShip extends EnemyEntity {
    private boolean visible;
    private boolean dying;


    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    abstract public void visualize() ;
}
