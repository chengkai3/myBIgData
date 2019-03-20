package Object;
/**
 * ±ÿ…±ººB
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import basic.Path;
public class UniqueSkillB extends FlyingObject
{

	public UniqueSkillB(Point point, ArrayList<BufferedImage> images, int life, int speedX, int speedY) 
	{
		super(point, images, life, speedX, speedY);
	}

	public void move(Point p1, Point p2) 
	{
		point = Path.pathAy(point, speedY);
	}

}
