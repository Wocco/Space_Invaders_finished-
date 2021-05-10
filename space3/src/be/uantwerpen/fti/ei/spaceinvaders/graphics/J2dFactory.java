package be.uantwerpen.fti.ei.spaceinvaders.graphics;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.AbstractFactory;
import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.AbstractInput;

import java.awt.*;

public class J2dFactory extends AbstractFactory {
    Graphics g;
    Graphics2D gr;
    public J2dFactory(){
        //this.g=new Graphics();
    }




    @Override
    public AbstractInput createInput() {
        return new J2DInput(g.getFrame());
    }

    public J2dEnemyShip newEnemyShip(){
        return new J2dEnemyShip(gr);
    };


}
