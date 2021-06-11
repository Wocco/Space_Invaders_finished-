package be.uantwerpen.fti.ei.spaceinvaders.graphics;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.AbstractFactory;
import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.AbstractInput;
import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.EnemyBullet;
import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.Pbonus;
import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.PlayerBullet;
import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.Playership;

import java.awt.*;

public class J2dFactory extends AbstractFactory {

    private Graphics g;

    /**
     * Instantiates a 2D game
     * @param g an instantion of the Graphics class
     */
    public J2dFactory(Graphics g) {
        this.g = g;
        g.setGameDimensions(800, 800);
        g.render();

    }

    /**
     * Updates the screen
     */
    public void update(){
        g.render();
    }

    /**
     * Creating a new playerbullet
     * @return J2dPlayerBullet this is an extension of PLayer Bullet
     */
    @Override
    public PlayerBullet newPlayerBullet() {
        return new J2dPlayerBullet(this.g);
    }

    /**
     * Write text to the status bar
     * @param text the to be written text to the status bar
     */

    public void setText(String text){g.setStatusbar(text);}

    /**
     * create an instantion of the 2D input
     * @return J2DInput
     */
    @Override
    public AbstractInput createInput() {

        return new J2DInput(g.getFrame());
    }

    /**
     * create a new enemyship instantion
     * @return J2dEnemyShip this is an extension of Enemy ship
     */
    @Override
    public J2dEnemyShip newEnemyShip(){
        return new J2dEnemyShip(this.g);
    }

    /**
     * create a new Playership instantion
     * @return J2dPlayership is an extension of Playership
     */
    @Override
    public Playership newPlayership() { return new J2dPlayership(this.g); }

    /**
     * create a new Enemybullet instantion
     * @return JédEnemyBullet is an extension of Enemybullet
     */
    @Override
    public EnemyBullet newEnemyBullet() { return new J2dEnemyBullet(this.g);}

    @Override
    public Pbonus newPbonus() {
        return new J2dPbonus(this.g);
    }
}
