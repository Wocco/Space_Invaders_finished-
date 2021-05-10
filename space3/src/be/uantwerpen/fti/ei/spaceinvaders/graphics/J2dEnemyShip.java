package be.uantwerpen.fti.ei.spaceinvaders.graphics;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.EnemyShip;

import java.awt.*;
import java.awt.image.BufferedImage;

public class J2dEnemyShip extends EnemyShip {
    int width=64;
    int height=64;
    int widthEnemyShip=54;
    int heightEnemyship=54;
    Graphics2D g;
    Sprite sprite = new Sprite("alien.png", widthEnemyShip, heightEnemyship);
    J2dEnemyShip(Graphics2D g){
        this.g=g;
    }



    @Override
    public void visualize() {
        Sprite.drawArray(g, this.giveBufferedImage(), this.getWidthEnemyShip(), this.getHeightEnemyship(), this.getX() * this.getWidth(), this.getY() * this.getHeight());;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public int getWidthEnemyShip(){
        return widthEnemyShip;
    }
    public int getHeightEnemyship(){
        return heightEnemyship;
    }



    public void setWidth(int newWidth){
        this.width=newWidth;
    }
    public void setHeight(int newHeight){
        this.height=newHeight;
    }
    public void setwidthEnemyShip(int widthEnemyShip){
        this.widthEnemyShip=widthEnemyShip;
    }
    public void setheightEnemyship(int heightEnemyship){
        this.heightEnemyship=heightEnemyship;
    }


    public BufferedImage giveBufferedImage(){
        BufferedImage bufferedImage=null;
        return bufferedImage=sprite.loadSprite("alien.png");
    }
}
