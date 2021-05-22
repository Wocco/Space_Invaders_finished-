package be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities;

public abstract class PlayerEntity extends Entity{
    @Override
    public String getEntity(){ return "PlayerEntity";}

    abstract public void visualize();
}
