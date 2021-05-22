package be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities;

public abstract class EnemyBullet extends EnemyEntity{
    @Override
    public String getEntity() {
        return "EnemyEntity";
    }



    abstract public void visualize() ;
}
