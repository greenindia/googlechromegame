package dinosaur.game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static dinosaur.game.Variables.*;

/**
 * Main Driver Class
 */
public class Main extends JFrame
{
    /**
     * Initialise the main game window
     * @throws IOException
     */
    public Main() throws IOException
    {

        setTitle("Dinosaur Game");

        Container container = getContentPane();

        GameWindow gameWindow = new GameWindow();
        gameWindow.setFocusable(true);

        container.add(gameWindow);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
    }

    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main game = null;
                try {
                    game = new Main();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                game.setVisible(true);
            }
        });
    }
}
