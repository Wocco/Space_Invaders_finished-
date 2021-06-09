package be.uantwerpen.fti.ei.spaceinvaders.graphics;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.PlayerBullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class J2dPlayerBullet extends PlayerBullet {
    private Graphics g;
    private BufferedImage playerBullet;

    /**
     * This constructor reads in the imgage and resizes it to the right size
     * @param g
     */
    J2dPlayerBullet(Graphics g){
        this.g=g;
        try{
            this.playerBullet = ImageIO.read(new File("src/be/uantwerpen/fti/ei/spaceinvaders/resources/playerBullet.png"));}

        catch(IOException e)
        {
            System.out.println("Error file not loaded correctly");
        }
        this.playerBullet=g.resizeImage(this.playerBullet,g.getWidthIcon(),g.getHeighthIcon());
    }


    /**
     * This method visualisez the playerbullet to the screen
     */
    @Override
    public void visualize() {
        Graphics2D g2d=g.getG2d();
        g2d.drawImage(playerBullet,this.getX()*g.getWidthIcon(),getY()* g.getWidthIcon(),null);
    }
}
