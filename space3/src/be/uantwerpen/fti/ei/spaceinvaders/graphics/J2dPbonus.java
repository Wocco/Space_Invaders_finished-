package be.uantwerpen.fti.ei.spaceinvaders.graphics;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.Pbonus;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class J2dPbonus extends Pbonus
{

    private Graphics g;
    private BufferedImage J2dPbonusimg;

    /**
     * initialises the J2d Player Bonus
     * @param g g An instantion of the Graphics class
     */
    J2dPbonus(Graphics g)
    {
        this.g=g;
        try
        {
            this.J2dPbonusimg = ImageIO.read(new File("src/be/uantwerpen/fti/ei/spaceinvaders/resources/heart.png"));
        }

        catch(IOException e)
        {
            System.out.println("Error file not loaded correctly");
        }
        this.J2dPbonusimg=g.resizeImage(this.J2dPbonusimg,g.getWidthIcon(),g.getHeighthIcon());
    }

    /**
     * Visualize the player bonus on screen
     */
    @Override
    public void visualize()
    {
        Graphics2D g2d=g.getG2d();
        g2d.drawImage(J2dPbonusimg,this.getX()*g.getWidthIcon(),getY()* g.getWidthIcon(),null);
    }
}
