package be.uantwerpen.fti.ei.spaceinvaders.gamelogic;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.EnemyShip;
import be.uantwerpen.fti.ei.spaceinvaders.graphics.Graphics;

//abstract factorie maakt factories
public abstract class AbstractFactory {
    private Graphics w;
    public abstract AbstractInput createInput();
    public abstract EnemyShip newEnemyShip();
    //public abstract void graphicsRender();
}
