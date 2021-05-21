package be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities;

public abstract class Playership extends PlayerEntity
{
    private boolean visible;
    private boolean dying;
    private int health=3;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    abstract public void visualize() ;
}
