package dinosaur.game;

/**
 * Class to store all static variable used in the game.
 */
public class Variables
{
    /**
     * Window Parameters.
     */
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 300;

    /**
     * Dinosaur Game States.
     */
    public static final int STILL = 1;
    public static final int RUNNING = 2;
    public static final int  JUMP = 3;
    public static final int DEAD = 4;


    /**
     * Diosaur Sprite/Image States.
     */
    public static final int LEFT_UP = 1;
    public static final int RIGHT_UP = 2;
    public static final int NO_FOOT = 3;

    /**
     * Obstacle generation ranges.
     */

    public static final int MAX_RANGE = 400;
    public static final int MIN_RANGE = 250;


}
