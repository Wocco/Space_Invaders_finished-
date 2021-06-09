package be.uantwerpen.fti.ei.spaceinvaders.graphics;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.EnemyBullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class J2dEnemyBullet extends EnemyBullet {
    private Graphics g;
    private BufferedImage enemyBullet;
    /**
     * Initialises the J2d Bullet. By reading the image file.
     * @param  g An instantion of the Graphics class
     * */
    J2dEnemyBullet(Graphics g){
        this.g=g;
        try{
            this.enemyBullet = ImageIO.read(new File("src/be/uantwerpen/fti/ei/spaceinvaders/resources/bullet.png"));}

        catch(IOException e)
        {
            System.out.println("Error file not loaded correctly");
        }
        this.enemyBullet=g.resizeImage(this.enemyBullet,g.getWidthIcon(),g.getHeighthIcon());
    }


    /**
     * Visualises the enemy bullet on screen
     */
    @Override
    public void visualize() {
        Graphics2D g2d=g.getG2d();
        g2d.drawImage(enemyBullet,this.getX()*g.getWidthIcon(),getY()* g.getWidthIcon(),null);
    }
}
