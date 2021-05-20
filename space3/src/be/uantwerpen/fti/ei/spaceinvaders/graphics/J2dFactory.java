package be.uantwerpen.fti.ei.spaceinvaders.graphics;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.AbstractFactory;
import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.AbstractInput;

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

    public J2dEnemyShip newEnemyShip(){
        return new J2dEnemyShip(this.g);
    };


}
