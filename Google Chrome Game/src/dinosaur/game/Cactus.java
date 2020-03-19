package dinosaur.game;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import static dinosaur.game.Variables.*;

/**
 * Cactus Class.
 */
public class Cactus
{

  private int firstX;
  private int obstacleInterval;
  private int movementSpeed;
  
  private ArrayList<BufferedImage> obstaclesList;
  private ArrayList<Obstacle> obList;

  private Obstacle blockedAt;

  /**
   * Initialise the cactus.
   * @throws IOException
   */
  public Cactus() throws IOException
  {

    int firstPos = (int) (GAME_WIDTH * 1.5);
    obList = new ArrayList<Obstacle>();
    obstaclesList = new ArrayList<BufferedImage>();
    
    firstX = firstPos;
    movementSpeed = 11;

    /**
     * Add Cactus to obstacle list.
     */
    obstaclesList.add(ImageIO.read(getClass().getResource("/images/Cactus-1.png")));
    obstaclesList.add(ImageIO.read(getClass().getResource("/images/Cactus-2.png")));

    obstaclesList.add(ImageIO.read(getClass().getResource("/images/Cactus-1.png")));
    obstaclesList.add(ImageIO.read(getClass().getResource("/images/Cactus-5.png")));
    
    int x = firstX;
    
    for(BufferedImage bi : obstaclesList)
    {

      obstacleInterval = (int) (Math.random()*((MAX_RANGE-MIN_RANGE)+1))+MIN_RANGE;

      Obstacle ob = new Obstacle();
      
      ob.image = bi;
      ob.x = x;
      ob.y = Ground.GROUND_Y - bi.getHeight() + 5;
      x += obstacleInterval;
      
      obList.add(ob);
    }
  }

  /**
   * Randomly generates the obstacles.
   */
  public void update()
  {

    Iterator<Obstacle> iterator = obList.iterator();

    Obstacle firstOb = iterator.next();
    firstOb.x -= movementSpeed;
    
    while(iterator.hasNext())
    {
      Obstacle ob = iterator.next();
      ob.x -= movementSpeed;
    }

    if(firstOb.x < -firstOb.image.getWidth())
    {
      obList.remove(firstOb);
      firstOb.x = obList.get(obList.size() - 1).x + obstacleInterval;
      obList.add(firstOb);
    }
  }

  /**
   * Paint graphics.
   * @param graphics Graphics Object.
   */
  public void draw(Graphics graphics)
  {
    for(Obstacle ob : obList)
    {
      graphics.setColor(Color.black);
      graphics.drawImage(ob.image, ob.x, ob.y, null);
    }
  }

  /**
   * Check collision with Dinosaur.
   * @return true or false.
   */
  public boolean hasCollided()
  {
    for(Obstacle ob : obList)
    {
      if(Dinosaur.getDino().intersects(ob.getObstacle()))
      {

        blockedAt = ob;
        return true;
      }   
    }
    return false;
  }

  /**
   * Restart the obstacle generation.
   */
  public void resume()
  {

    int x = firstX/2;   
    obList = new ArrayList<Obstacle>();
    
    for(BufferedImage bi : obstaclesList)
    {

      obstacleInterval = (int) (Math.random()*((MAX_RANGE-MIN_RANGE)+1))+MIN_RANGE;

      Obstacle ob = new Obstacle();
      
      ob.image = bi;
      ob.x = x;
      ob.y = Ground.GROUND_Y - bi.getHeight() + 5;
      x += obstacleInterval;
      
      obList.add(ob);
    }
  }

  /**
   * Private class to store obstacles (cactus).
   */
  private class Obstacle
  {
    BufferedImage image;
    int x;
    int y;

    Rectangle getObstacle()
    {

      Rectangle obstacle = new Rectangle();
      obstacle.x = x;
      obstacle.y = y;
      obstacle.width = image.getWidth();
      obstacle.height = image.getHeight();

      return obstacle;
    }
  }
  
}
