package be.uantwerpen.fti.ei.spaceinvaders.gamelogic;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.*;
import be.uantwerpen.fti.ei.spaceinvaders.graphics.Graphics;

//abstract factorie maakt factories
public abstract class AbstractFactory {
    private Graphics w;
    public abstract AbstractInput createInput();
    public abstract EnemyShip newEnemyShip();
    public abstract Playership newPlayership();
    public abstract EnemyBullet newEnemyBullet();
    public abstract PlayerBullet newPlayerBullet();
    public abstract void setText(String text);
    public abstract Pbonus newPbonus();
    public abstract void update();
}
