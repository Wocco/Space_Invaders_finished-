package be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities;

import java.awt.image.BufferedImage;

public abstract class Entity {
    int x=0;     //place x from 0 to 9
    int y=0;     //place y from 0 to 9
    int dx = 0;  //movement x
    int dy=0;    //movement y

//getters
    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    //setters

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setX(int x) {
        if(x>9){
            //do nothing
        }
        else if(x<0){

        }
        else{
        this.x = x;}
    }

    public void setY(int y) {
        this.y = y;
    }

    abstract String getEntity();
    abstract int getDirection();
    abstract public void visualize() ;
    abstract public int getWidth();
    abstract public int getHeight();
    abstract public BufferedImage giveBufferedImage();


}
