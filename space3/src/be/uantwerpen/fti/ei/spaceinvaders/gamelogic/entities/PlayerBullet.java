package be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities;

public abstract class PlayerBullet extends PlayerEntity{
    @Override
    public String getEntity(){ return "PlayerBullet";}
    abstract public void visualize();
}
