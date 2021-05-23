package be.uantwerpen.fti.ei.spaceinvaders.gamelogic;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.EnemyBullet;
import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.EnemyShip;
import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.PlayerBullet;
import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.Playership;
import be.uantwerpen.fti.ei.spaceinvaders.graphics.Graphics;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements Runnable {
    AbstractFactory factory;

    private int playingfield=16;
    private int rows=3;
    private int columns=8;
    private int moveForward;
    boolean running;
    private int slowcount=0;

    Random rand = new Random();

    private Thread thread;
    //KeyHandler key;


    //Entitys
    private ArrayList<EnemyShip> wave = new ArrayList<EnemyShip>();
    private Playership playership;
    private ArrayList <EnemyBullet> enemyBullets = new ArrayList<EnemyBullet>();
    private ArrayList <PlayerBullet> playerBullets = new ArrayList<PlayerBullet>();

    //input
    private AbstractInput input;
    public Game(AbstractFactory f){
        this.factory=f;

    }
    public void init(){
        running=true;
        input= factory.createInput();
        for(int j=0; j<rows;j++)
        {
            for(int i=0;i<columns;i++)
            {
                wave.add(factory.newEnemyShip());
                wave.get(wave.size()-1).setDx(1);
                wave.get(wave.size()-1).setX(1+i);
                wave.get(wave.size()-1).setY(j);
                wave.get(wave.size()-1).setVisible(true);
            }
        }

        playership= factory.newPlayership();
        playership.setX(8);
        playership.setY(16);


    }

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
        final double GAME_HERTZ=4;
        final double TBU= 2_00_000_000 /GAME_HERTZ;//time before update

        final int MUBR=1; //most updates before render

        double lastUpdateTime=System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS=10;
        final double TTBR=1000000000/TARGET_FPS;//total time before render

        int frameCount=0;
        int lastSecondTime=(int) (lastUpdateTime/1000000000);
        int oldFrameCount=0;

        while(running){

            double now=System.nanoTime();
            int updateCount=0;


            if(input.inputAvailable())
            {
                switch (input.getInput())
                {
                    case LEFT:
                        if(playership.getX()>0)
                        {
                            playership.setX(playership.getX()-1);
                        }
                        //else do nothing
                    break;
                    case SPACE:
                        playerBullets.add(factory.newPlayerBullet());
                        playerBullets.get(playerBullets.size()-1).setX(playership.getX());
                        playerBullets.get(playerBullets.size()-1).setY(playership.getY()-1);
                        playerBullets.get(playerBullets.size()-1).setDx(0);
                        playerBullets.get(playerBullets.size()-1).setDy(-1);

                    break;
                    case RIGHT:
                        if(playership.getX()<=playingfield)
                        {
                            playership.setX(playership.getX()+1);
                        }
                        System.out.println("The right key is pressed");
                        break;
                    case ESCAPE:
                        //pause the game
                        System.out.println("The escape button is pressed");
                        break;
                }

            }

            while((now-lastUpdateTime)>TBU&&(updateCount<MUBR)){
                //update();
                if(slowcount==10)
                {
                    //enemys shooting back
                    int rand_int1 =rand.nextInt(wave.size());
                    System.out.println("the random int: " + rand_int1);
                    if(wave.get(rand_int1).isVisible()&& rand_int1%2==0)
                    {
                        enemyBullets.add(factory.newEnemyBullet());
                        enemyBullets.get(enemyBullets.size()-1).setY(wave.get(rand_int1).getY()+1);
                        enemyBullets.get(enemyBullets.size()-1).setX(wave.get(rand_int1).getX());
                    }



                    for(int i=0;i<enemyBullets.size();i++)
                    {
                        enemyBullets.get(i).setY(enemyBullets.get(i).getY()+1);
                    }


                    if((wave.get(wave.size()-1).getX()>playingfield)&&moveForward!=-2)//if the outer right wall is hit
                    {
                        System.out.println("if the outer right wall is hit");
                        for(int i = 0; i < wave.size();i++)
                        {
                            wave.get(i).setDx(0);
                            moveForward=-1;
                            wave.get(i).setY(wave.get(i).getY()+1);
                        }
                    }

                    if((wave.get(0).getX()==0)&&moveForward!=2)
                    {
                        System.out.println("/if the outer left wall is hit");
                        for (int i = 0;i<wave.size();i++)
                        {
                            wave.get(i).setDx(0);
                            wave.get(i).setY(wave.get(i).getY()+1);
                            moveForward=1;

                        }
                    }
                    for(int i=0;i< wave.size();i++)
                    {
                        //if(wave.get(i).getDx()==1)
                        wave.get(i).setX(wave.get(i).getX()+wave.get(i).getDx());
                        if (moveForward==-1)
                        {

                            wave.get(i).setDx(-1);

                        }
                        if (moveForward==1)
                        {
                            wave.get(i).setDx(1);

                        }
                    }
                    if(moveForward==-1)
                    {
                        moveForward=-2;
                    }
                    if (moveForward==1)
                    {
                        moveForward=2;
                    }
                    slowcount=0;
                }
                else
                {
                    slowcount=slowcount+1;
                }

                int count=0;
                while(count<enemyBullets.size())
                {
                    if(enemyBullets.get(count).getY()>playingfield)
                    {
                        enemyBullets.remove(count);
                    }
                    else
                    {
                        if (enemyBullets.get(count).getX()==playership.getX() && enemyBullets.get(count).getY()==playership.getY())
                        {

                            playership.setHealth(playership.getHealth()-1);
                            enemyBullets.remove(count);
                            break;
                        }
                    }
                    count++;
                }

                int index=0;
                while(index<playerBullets.size())
                {
                    if(playerBullets.get(index).getY()<0)
                    {
                        playerBullets.remove(index);
                    }
                    else
                    {
                        for(int i=0;i< wave.size();i++)
                        {
                            if (playerBullets.get(index).getX()==wave.get(i).getX() && playerBullets.get(index).getY()==wave.get(i).getY() && wave.get(i).isVisible()==true)
                            {
                                wave.get(i).setVisible(false);
                                playerBullets.remove(index);
                                break;
                            }
                        }
                    }
                    index++;
                }


                for(int i=0;i<playerBullets.size();i++)
                {
                    playerBullets.get(i).setY(playerBullets.get(i).getY()-1);
                }




                //input(key);
                lastUpdateTime+=TBU;
                updateCount++;
            }
            if(now-lastUpdateTime>TBU){
                lastUpdateTime=now-TBU;
            }

            lastRenderTime=now;
            frameCount++;

            //visualize the enemy players
            for(int i=0;i<wave.size();i++)
            {
                if (wave.get(i).isVisible()==true)
                {
                    wave.get(i).visualize();
                }
            }
            if (playerBullets.size()!=0)
            {
                for(int i=0;i<playerBullets.size();i++)
                {
                    playerBullets.get(i).visualize();
                }
            }
            if (enemyBullets.size()!=0)
            {
                for(int i=0;i<enemyBullets.size();i++)
                {
                    enemyBullets.get(i).visualize();
                }
            }

            playership.visualize();


            factory.update();

            int thisSecond=(int) (lastUpdateTime/1000000000);
            if(thisSecond>lastSecondTime){
                if(frameCount!=oldFrameCount){
                    System.out.println("NEW SECOND "+thisSecond+" "+frameCount);
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
