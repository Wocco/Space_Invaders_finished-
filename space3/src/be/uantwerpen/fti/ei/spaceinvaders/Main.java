package be.uantwerpen.fti.ei.spaceinvaders;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.AbstractFactory;
import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.Game;
import be.uantwerpen.fti.ei.spaceinvaders.graphics.Graphics;
import be.uantwerpen.fti.ei.spaceinvaders.graphics.J2dFactory;

public class Main {

    public static void main(String[] args) {

        AbstractFactory f=new J2dFactory();
        Game g =new Game(f);
        //f.createInput();
        g.init();
        g.run();

    }
}
