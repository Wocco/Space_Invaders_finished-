package be.uantwerpen.fti.ei.spaceinvaders.graphics;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.AbstractFactory;
import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.AbstractInput;
import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.EnemyBullet;
import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.Playership;

import java.awt.*;

public class J2dFactory extends AbstractFactory {

    private Graphics g;
    public J2dFactory(Graphics g) {
        this.g = g;
        g.setGameDimensions(800, 800);
        g.render();

    }
    public void update(){
        g.render();
    }




    @Override
    public AbstractInput createInput() {

        return new J2DInput(g.getFrame());
    }
    @Override
    public J2dEnemyShip newEnemyShip(){
        return new J2dEnemyShip(this.g);
    }

    @Override
    public Playership newPlayership() { return new J2dPlayership(this.g); }

    @Override
    public EnemyBullet newEnemyBullet() { return new J2dEnemyBullet(this.g);}
}
