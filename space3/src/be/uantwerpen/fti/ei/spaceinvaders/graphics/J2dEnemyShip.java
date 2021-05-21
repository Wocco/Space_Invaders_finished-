package be.uantwerpen.fti.ei.spaceinvaders.graphics;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.EnemyShip;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class J2dEnemyShip extends EnemyShip {
    private Graphics g;
    private BufferedImage enemyshipimg;
    J2dEnemyShip(Graphics g){
        this.g=g;
        try{
            this.enemyshipimg = ImageIO.read(new File("src/be/uantwerpen/fti/ei/spaceinvaders/resources/alien.png"));}

        catch(IOException e)
        {
            System.out.println("Error file not loaded correctly");
        }
        this.enemyshipimg=g.resizeImage(this.enemyshipimg,g.getWidthIcon(),g.getHeighthIcon());
    }




    @Override
    public void visualize() {
        Graphics2D g2d=g.getG2d();
        g2d.drawImage(enemyshipimg,this.getX()*g.getWidthIcon(),getY()* g.getWidthIcon(),null);


    }



}
