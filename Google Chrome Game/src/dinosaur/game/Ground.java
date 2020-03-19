package dinosaur.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import static dinosaur.game.Variables.*;

/**
 * Ground Class. Handles the ground.
 */
public class Ground
{

  /**
   * Class to store ground image and its position
   */
  private class GroundImage
  {
    BufferedImage image;
    int posX;

    Rectangle getGround()
    {

      Rectangle groundObj = new Rectangle();
      groundObj.x = posX;
      groundObj.width = image.getWidth();
      groundObj.height = image.getHeight();

      return groundObj;
    }
  }
  
  public static int GROUND_Y;
  
  private BufferedImage groundImage;
  
  private ArrayList<GroundImage> groundImageSet;


  /**
   * Initialise the ground.
   * @throws IOException
   */
  public Ground() throws IOException
  {

    GROUND_Y = (int)(GAME_HEIGHT - 0.25 * GAME_HEIGHT);

    groundImage = ImageIO.read(getClass().getResource("/images/Ground.png"));

    
    groundImageSet = new ArrayList<GroundImage>();
    
    //first ground image:
    for(int i=0; i<3; i++)
    {
      GroundImage obj = new GroundImage();
      obj.image = groundImage;
      obj.posX = 0;
      groundImageSet.add(obj);
    }

  }

  /**
   * Update the ground state. Gives ground moving effect.
   */
  public void update()
  {

    Iterator<GroundImage> iterator = groundImageSet.iterator();
    GroundImage first = iterator.next();
    
    first.posX -= 10;
    
    int previousX = first.posX;
    while(iterator.hasNext())
    {
      GroundImage next = iterator.next();
      next.posX = previousX + groundImage.getWidth();
      previousX = next.posX;
    }
    
    if(first.posX < - groundImage.getWidth())
    {
      groundImageSet.remove(first);
      first.posX = previousX + groundImage.getWidth();
      groundImageSet.add(first);
    }
    
  }

  public boolean hasCollided()
  {
    for(GroundImage ob : groundImageSet)
    {
      if(Dinosaur.getDino().intersects(ob.getGround()))
      {
        return true;
      }
    }
    return false;

  }

  /**
   * Paints the ground
   * @param graphics
   */
  public void draw(Graphics graphics)
  {
		for(GroundImage img : groundImageSet)
		{
          graphics.drawImage(img.image, img.posX, GROUND_Y, null);
		}
	}
}
