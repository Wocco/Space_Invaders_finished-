package be.uantwerpen.fti.ei.spaceinvaders.gamelogic;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.EnemyShip;
import be.uantwerpen.fti.ei.spaceinvaders.graphics.Graphics;

import javax.swing.*;

public class Game extends JPanel implements Runnable {
    AbstractFactory factory;

    private EnemyShip enemyShip;

    boolean running;

    private Thread thread;
    //KeyHandler key;

    public Game(AbstractFactory f){
        this.factory=f;
        f.createInput();

    }
    public void init(){
        running=true;
        enemyShip=factory.newEnemyShip();


    }


    /*public void input(KeyHandler Key) {

        if(Key.up.down){
            System.out.println("W is being pressed");

        }
        if(Key.left.down){//this is A
            System.out.println("A is being pressed");

        }
        if(Key.right.down){//this is d
            System.out.println("d is being pressed down");
        }
        if(Key.shoot.down){//this is space bar
            System.out.println("spacebar");
        }
    }*/
    public void addNotify(){
        super.addNotify();
        if(thread==null)
        {
            thread =new Thread(this,"gamethread");
            thread.start();
        }
    }
    @Override
    public void run() {
        final double GAME_HERTZ=1;
        final double TBU= 2_00_000_000 /GAME_HERTZ;//time before update

        final int MUBR=1; //most updates before render

        double lastUpdateTime=System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS=30;
        final double TTBR=1000000000/TARGET_FPS;//total time before render

        int frameCount=0;
        int lastSecondTime=(int) (lastUpdateTime/1000000000);
        int oldFrameCount=0;

        while(running){
            double now=System.nanoTime();
            int updateCount=0;



            while((now-lastUpdateTime)>TBU&&(updateCount<MUBR)){
                //update();

                //input(key);
                lastUpdateTime+=TBU;
                updateCount++;
            }
            if(now-lastUpdateTime>TBU){
                lastUpdateTime=now-TBU;
            }

            //factory.graphicsRender();



            //draw();
            lastRenderTime=now;
            frameCount++;

            int thisSecond=(int) (lastUpdateTime/1000000000);
            if(thisSecond>lastSecondTime){
                if(frameCount!=oldFrameCount){
                    System.out.println("NEW SECOND "+thisSecond+" "+frameCount);
                    System.out.println("enemy drawn");
                    enemyShip.visualize();
                    oldFrameCount=frameCount;
                }
                frameCount=0;
                lastSecondTime=thisSecond;
            }

            while(now-lastRenderTime<TTBR&& now-lastUpdateTime<TBU){
                Thread.yield();
                try{
                    Thread.sleep(1);
                }catch(Exception e){
                    System.out.println("Error yielding thread");
                }
                now=System.nanoTime();

            }
        }
    }
}
