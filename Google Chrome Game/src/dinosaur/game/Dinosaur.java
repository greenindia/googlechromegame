package dinosaur.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static dinosaur.game.Variables.*;

/**
 * Main Dinosaur Class.
 */
public class Dinosaur
{

    private static BufferedImage dinoStand;
    private BufferedImage dinoLeftUp;
    private BufferedImage dinoRightUp;
    private BufferedImage dinoDie;

    private static int dinoState;
    private int foot;

    private static int jumpForce = 20;

    private static int  dinoTopY, dinoStartX;
    private static int dinoTop, topPoint;

    private static boolean topReached;


    /**
     * Initialise the Dinosaur
     * @throws IOException
     */
    public Dinosaur() throws IOException
    {
        /**
         * Four differnt states of Dinosaur.
         */
        dinoStand = ImageIO.read(getClass().getResource("/images/Dino-stand.png"));
        dinoLeftUp = ImageIO.read(getClass().getResource("/images/Dino-left-up.png"));
        dinoRightUp = ImageIO.read(getClass().getResource("/images/Dino-right-up.png"));
        dinoDie = ImageIO.read(getClass().getResource("/images/Dino-dead.png"));


        dinoTopY = Ground.GROUND_Y - dinoStand.getHeight() + 5;
        dinoStartX = 100;

        topPoint = dinoTopY - 120;

        dinoState = STILL;
        foot = NO_FOOT;
    }

    /**
     * Update the Dinosaur and handle the Key Event.
     * @param graphics
     */
    public void updateDino(Graphics graphics) {


        switch(dinoState) {

            case STILL:
                System.out.println("stand");
                graphics.drawImage(dinoStand, dinoStartX, dinoTopY, null);
                break;

            case RUNNING:
                if(foot == NO_FOOT)
                {
                    foot = LEFT_UP;
                    graphics.drawImage(dinoLeftUp, dinoStartX, dinoTopY, null);
                }
                else if(foot == LEFT_UP)
                {
                    foot = RIGHT_UP;
                    graphics.drawImage(dinoRightUp, dinoStartX, dinoTopY, null);
                }
                else
                {
                    foot = LEFT_UP;
                    graphics.drawImage(dinoLeftUp, dinoStartX, dinoTopY, null);
                }
                break;

            case JUMP:
                if(dinoTop > topPoint && !topReached)
                {
                    graphics.drawImage(dinoStand, dinoStartX, dinoTop -= jumpForce, null);
                    break;
                }
                if(dinoTop >= topPoint && !topReached)
                {
                    topReached = true;
                    graphics.drawImage(dinoStand, dinoStartX, dinoTop += jumpForce, null);
                    break;
                }
                if(dinoTop > topPoint && topReached)
                {
                    if(dinoTopY == dinoTop && topReached)
                    {
                        dinoState = RUNNING;
                        topReached = false;
                        break;
                    }
                    graphics.drawImage(dinoStand, dinoStartX, dinoTop += jumpForce, null);
                    break;
                }
            case DEAD:
                graphics.drawImage(dinoDie, dinoStartX, dinoTop, null);
                break;
        }
    }

    /**
     * Run State.
     */
    public void run()
    {
        dinoTop = dinoTopY;
        dinoState = RUNNING;
    }

    /**
     * Dead state.
     */
    public void die()
    {
        dinoState = DEAD;
    }

    /**
     * Jump state.
     */
    public void jump()
    {
        dinoTop = dinoTopY;
        dinoState = JUMP;
    }

    /**
     * Get Dinosaur model to check for collision.
     * @return dino
     */
    public static Rectangle getDino()
    {
        Rectangle dino = new Rectangle();
        dino.x = dinoStartX;

        if(dinoState == JUMP && !topReached) dino.y = dinoTop - jumpForce;
        else if(dinoState == JUMP && topReached) dino.y = dinoTop + jumpForce;
        else if(dinoState != JUMP) dino.y = dinoTop;

        dino.width = dinoStand.getWidth();
        dino.height = dinoStand.getHeight();

        return dino;
    }

}
