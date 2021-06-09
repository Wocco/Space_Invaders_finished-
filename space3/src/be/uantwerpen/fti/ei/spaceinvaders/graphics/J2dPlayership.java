package be.uantwerpen.fti.ei.spaceinvaders.graphics;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.PlayerEntity;
import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.Playership;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class J2dPlayership extends Playership
{
    private Graphics g;
    private BufferedImage playership;

    /**
     * THis constructor of the J2d playership loads the images
     * @param g an instantion of the graphics class needs to be provided
     */
    J2dPlayership(Graphics g){
        this.g=g;
        try{
            this.playership = ImageIO.read(new File("src/be/uantwerpen/fti/ei/spaceinvaders/resources/Playership.png"));}

        catch(IOException e)
        {
            System.out.println("Error file not loaded correctly");
        }
        this.playership=g.resizeImage(this.playership,g.getWidthIcon(),g.getHeighthIcon());
    }

    /**
     * This method overrides the visualize from AbstractFactory.
     * Visualize draws the playership to the screen.
     */
    @Override
    public void visualize() {
        Graphics2D g2d=g.getG2d();
        g2d.drawImage(playership,this.getX()*g.getWidthIcon(),getY()* g.getWidthIcon(),null);
    }
}
