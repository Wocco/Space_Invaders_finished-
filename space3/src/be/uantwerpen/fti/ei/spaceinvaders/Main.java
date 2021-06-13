package be.uantwerpen.fti.ei.spaceinvaders;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.AbstractFactory;
import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.Game;
import be.uantwerpen.fti.ei.spaceinvaders.graphics.Graphics;
import be.uantwerpen.fti.ei.spaceinvaders.graphics.J2dFactory;

public class Main
{
    /**
     * Static runnable main. This main creates one instantion of the game
     * @param args
     */
    public static void main(String[] args)
    {
        Graphics gr = new Graphics(800,800);
        AbstractFactory f = new J2dFactory(gr);
        Game g = new Game(f);
        g.init();
        g.run();
    }
}
