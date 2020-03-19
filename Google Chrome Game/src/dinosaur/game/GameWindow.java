package dinosaur.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import static dinosaur.game.Variables.*;

/**
 * Main Game Window Class.
 * Implements KeyListener and Runnable.
 */
public class GameWindow extends JPanel implements KeyListener, Runnable{

    private int gameScore;

    private boolean onGround;
    private boolean isRunning;
    private boolean isGameOver;


    private Dinosaur dino;
    private Ground ground;
    private Cactus cactus;

    private Thread animateDino;


    /**
     * Initalise the window and other game objects like ground, dinosaur and obstacles.
     * @throws IOException
     */
    public  GameWindow() throws IOException
    {
        gameScore = 0;
        onGround = true;
        isRunning = false;
        isGameOver = false;

        setSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        addKeyListener(this);

        ground = new Ground();
        dino = new Dinosaur();
        cactus = new Cactus();

    }

    /**
     * Paints the sprites on Panel.
     * @param graphics
     */
    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        // graphics.setFont(new Font("Courier New", Font.BOLD, 25));
        graphics.drawString(Integer.toString(gameScore), getWidth()/2 - 5, 100);
        ground.draw(graphics);
        dino.updateDino(graphics);
        cactus.draw(graphics);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Keys Handler
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyPressed = e.getKeyCode();


        System.out.println(onGround);

        if(keyPressed == KeyEvent.VK_SPACE)
        {
            if(isGameOver == true)
            {
                restartGame();
            }
            if (animateDino == null || !isRunning)
            {

                animateDino = new Thread(this);
                animateDino.start();
                dino.run();
            }
            else
            {
                dino.jump();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Restart the game.
     */
    public void restartGame()
    {
        gameScore = 0;
        isGameOver = false;
        isRunning = false;
        onGround = true;
        cactus.resume();
    }

    /**
     * Handles game thread.
     */
    @Override
    public void run()
    {
        isRunning = true;

        while(isRunning)
        {
            update();
            repaint();
            try
            {
                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    }

    /**
     * Update the game at each frame.
     */
    public void update()
    {

        gameScore += 1;

        ground.update();
        cactus.update();

        if(ground.hasCollided())
        {
            onGround = true;
        }
        else onGround = false;

        if(cactus.hasCollided())
        {
            dino.die();
            repaint();
            isRunning = false;
            isGameOver = true;

        }

    }

    /**
     * Check if user is on ground or not.
     * @return true or false.
     */
    public boolean getOnGround()
    {
        return onGround;
    }
}
