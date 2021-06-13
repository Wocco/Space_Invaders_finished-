package be.uantwerpen.fti.ei.spaceinvaders.gamelogic;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.entities.*;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

import java.util.ArrayList;
import java.util.Random;


public class Game extends JPanel implements Runnable {
    AbstractFactory factory;

    private final int PLAYINGFIELD =16;
    private final int ROWS =3;
    private final int COLUMNS =8;
    private int moveForward;
    boolean running;
    private int slowcount=0;
    private boolean gameover=false;
    private boolean gamewon=false;

    Random rand = new Random();

    private Thread thread;

    private ArrayList<EnemyShip> wave = new ArrayList<EnemyShip>();
    private Playership playership;
    private ArrayList <EnemyBullet> enemyBullets = new ArrayList<EnemyBullet>();
    private ArrayList <PlayerBullet> playerBullets = new ArrayList<PlayerBullet>();
    private Pbonus playerBonus;

    //input
    private AbstractInput input;

    /**
     * starts the game by setting the factory that is provided to the game to it's own abstract factory.
     * @param f Abstract factory
     */
    public Game(AbstractFactory f){
        this.factory=f;
        playMusic();
    }

    /**
     * Initialises the game by setting all the parameters to a working starting position
     * Also creates a horde of enemies to start the game of.
     */
    public void init()
    {
        running = true;
        playerBonus = factory.newPbonus();
        playerBonus.setVisible(false);
        gameover = false;
        input = factory.createInput();
        for(int j = 0; j< ROWS; j++)
        {
            for(int i = 0; i< COLUMNS; i++)
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

    /**
     * Plays baker street once called and keeps looping the song until the game is closed
     */
    public static void playMusic()
    {
       try
       {
            File musicPath = new File("src/be/uantwerpen/fti/ei/spaceinvaders/resources/bakerstreet.wav");
            if(musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);

            }
            else
            {
                System.out.println("File not found with given path");
            }
       }
       catch (Exception e)
       {
            e.printStackTrace();
       }

    }

    /**
     *If there is no thread running a new thread is started named gamethread
     */
    public void addNotify(){
        super.addNotify();
        if(thread == null)
        {
            thread = new Thread(this,"gamethread");
            thread.start();
        }
    }

    /**
     * The game loop. This loop makes the game run smoothly by having a game refresh frequency and a graphics refresh rate.
     * The game is also implemented in this method. Checking if bullet's hit, moving the enemies and moving the player ship is al done in this method.
     * This method also makes use of nano.time instead of milliseconds because it's more accurate and reliable.
     */
    @Override
    public void run()
    {
        final double GAME_HERTZ = 4; // the game refresh rate
        final double TBU = 2_00_000_000 /GAME_HERTZ;//time before update

        final int MUBR = 1; //most updates before render

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 10;
        final double TTBR = 1000000000/TARGET_FPS; //total time before render

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime/1000000000);
        int oldFrameCount=0;

        while(running){

            double now=System.nanoTime();
            int updateCount=0;

            //check for input (if a key is pressed)
            if(input.inputAvailable())
            {
                switch (input.getInput())
                {
                    case L:
                        if(playership.getX()>0)
                        {
                            playership.setX(playership.getX()-1);
                        }
                        //else do nothing
                    break;
                    case S:
                        playerBullets.add(factory.newPlayerBullet());
                        playerBullets.get(playerBullets.size()-1).setX(playership.getX());
                        playerBullets.get(playerBullets.size()-1).setY(playership.getY()-1);
                        playerBullets.get(playerBullets.size()-1).setDx(0);
                        playerBullets.get(playerBullets.size()-1).setDy(-1);

                    break;
                    case R:
                        if(playership.getX()<= PLAYINGFIELD)
                        {
                            playership.setX(playership.getX()+1);
                        }

                        break;
                    case ESC:
                        //pause the game
                        if(gameover==true)
                        {
                            gamewon=false;
                            gameover=false;
                            playerBullets.clear();
                            playership.setHealth(3);
                            wave.clear();
                            playerBullets.clear();
                            enemyBullets.clear();
                            moveForward=0;


                            running=true;
                            for(int j = 0; j< ROWS; j++)
                            {
                                for(int i = 0; i< COLUMNS; i++)
                                {
                                    wave.add(factory.newEnemyShip());
                                    wave.get(wave.size()-1).setDx(1);
                                    wave.get(wave.size()-1).setX(1+i);
                                    wave.get(wave.size()-1).setY(j);
                                    wave.get(wave.size()-1).setVisible(true);
                                }
                            }
                            playership.setX(8);
                            playership.setY(16);
                        }

                        break;
                }

            }

            while((now-lastUpdateTime) > TBU&&(updateCount<MUBR))
            {
                if(playership.getHealth()<=0)
                {
                    gameover=true;
                }
                else
                {
                    int numberOfAliveEnemys=0;
                        for (int i = 0; i < wave.size(); i++)
                        {
                            if (wave.get(i).isVisible() == true)
                            {
                                numberOfAliveEnemys = numberOfAliveEnemys+1;
                                if(wave.get(i).getY()==16)
                                {
                                    gameover=true;
                                    gamewon=false;
                                }

                            }
                        }
                        if (numberOfAliveEnemys == 0)
                        {
                            gamewon=true;
                            gameover=true;
                        }

                }

                if(gameover!=true) {
                    if (slowcount == 10)
                    {
                        //enemys shooting back
                        int rand_int1 = rand.nextInt(wave.size());
                        if (wave.get(rand_int1).isVisible() && rand_int1 % 2 == 0) {
                            enemyBullets.add(factory.newEnemyBullet());
                            enemyBullets.get(enemyBullets.size() - 1).setY(wave.get(rand_int1).getY() + 1);
                            enemyBullets.get(enemyBullets.size() - 1).setX(wave.get(rand_int1).getX());
                        }


                        for (int i = 0; i < enemyBullets.size(); i++)
                        {
                            enemyBullets.get(i).setY(enemyBullets.get(i).getY() + 1);
                        }



                        if ((wave.get(wave.size() - 1).getX() > PLAYINGFIELD) && moveForward != -2)//if the outer right wall is hit
                        {
                            System.out.println("if the outer right wall is hit");
                            for (int i = 0; i < wave.size(); i++) {
                                wave.get(i).setDx(0);
                                moveForward = -1;
                                wave.get(i).setY(wave.get(i).getY() + 1);
                            }
                        }

                        if ((wave.get(0).getX() == 0) && moveForward != 2) {
                            System.out.println("if the outer left wall is hit");
                            for (int i = 0; i < wave.size(); i++) {
                                wave.get(i).setDx(0);
                                wave.get(i).setY(wave.get(i).getY() + 1);
                                moveForward = 1;

                            }
                        }
                        for (int i = 0; i < wave.size(); i++) {
                            wave.get(i).setX(wave.get(i).getX() + wave.get(i).getDx());
                            if (moveForward == -1) {

                                wave.get(i).setDx(-1);

                            }
                            if (moveForward == 1) {
                                wave.get(i).setDx(1);

                            }
                        }
                        if (moveForward == -1) {
                            moveForward = -2;
                        }
                        if (moveForward == 1) {
                            moveForward = 2;
                        }
                        slowcount = 0;

                        int rand_int2 = rand.nextInt(PLAYINGFIELD);

                        if(rand_int2==8 && playerBonus.isVisible() != true)
                        {
                            int rand_int3 = rand.nextInt(PLAYINGFIELD);
                            playerBonus.setX(rand_int3 );
                            playerBonus.setY(0);
                            playerBonus.setDx(0);
                            playerBonus.setDy(1);
                            playerBonus.setVisible(true);

                        }
                        if(playerBonus.isVisible() == true)
                        {
                            if(playerBonus.getY()<16)
                            {
                                playerBonus.setY(playerBonus.getY() + playerBonus.getDy());
                            }
                            else
                            {
                                playerBonus.setVisible(false);
                            }
                        }
                    }
                    else
                    {
                        slowcount = slowcount + 1;
                    }

                    int count = 0;
                    while (count < enemyBullets.size())
                    {
                        if (enemyBullets.get(count).getY() > PLAYINGFIELD)
                        {
                            enemyBullets.remove(count);
                        }
                        else
                        {
                            if (enemyBullets.get(count).getX() == playership.getX() && enemyBullets.get(count).getY() == playership.getY())
                            {
                                playership.setHealth(playership.getHealth() - 1);
                                enemyBullets.remove(count);
                                break;
                            }

                        }
                        count++;
                    }
                    if(playerBonus.getY() == playership.getY() && playerBonus.getX() == playership.getX() && playerBonus.isVisible()==true)
                    {
                        playership.setHealth(playership.getHealth()+1);
                        playerBonus.setVisible(false);
                    }

                    int index = 0;
                    while (index < playerBullets.size()) {
                        if (playerBullets.get(index).getY() < 0) {
                            playerBullets.remove(index);
                        } else {
                            for (int i = 0; i < wave.size(); i++) {
                                if (playerBullets.get(index).getX() == wave.get(i).getX() && playerBullets.get(index).getY() == wave.get(i).getY() && wave.get(i).isVisible() == true) {
                                    wave.get(i).setVisible(false);
                                    playerBullets.remove(index);
                                    break;
                                }
                            }
                        }
                        index++;
                    }


                    for (int i = 0; i < playerBullets.size(); i++) {
                        playerBullets.get(i).setY(playerBullets.get(i).getY() - 1);
                    }
                }


                lastUpdateTime+=TBU;
                updateCount++;
            }
            if(now-lastUpdateTime>TBU){
                lastUpdateTime=now-TBU;
            }

            lastRenderTime=now;
            frameCount++;

            if(gameover!=true) {
                //visualize the enemy players
                for (int i = 0; i < wave.size(); i++) {
                    if (wave.get(i).isVisible() == true) {
                        wave.get(i).visualize();
                    }
                }
                if (playerBullets.size() != 0) {
                    for (int i = 0; i < playerBullets.size(); i++) {
                        playerBullets.get(i).visualize();
                    }
                }
                if (enemyBullets.size() != 0) {
                    for (int i = 0; i < enemyBullets.size(); i++) {
                        enemyBullets.get(i).visualize();
                    }
                }
                if(playerBonus.isVisible()==true)
                {
                    playerBonus.visualize();
                }
                factory.setText("Health:  " + playership.getHealth());
                playership.visualize();

            }
            else
            {
                if(gamewon==true)
                {
                    factory.setText(" Goodgame! Press escape to restart");

                }
                else
                {
                    factory.setText("   !!!!GAME OVER!!!! press escape to restart");
                }
            }
            factory.update();

            int thisSecond=(int) (lastUpdateTime/1000000000);
            if(thisSecond>lastSecondTime){
                if(frameCount!=oldFrameCount){

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
