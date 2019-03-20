package Object;
//…¡µÁÃÿ–ß
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import basic.Path;
public class UniqueSkillA extends FlyingObject
{

	public UniqueSkillA(Point point, ArrayList<BufferedImage> images, int life, int speedX, int speedY) 
	{
		super(point, images, life, speedX, speedY);
	}
	public void move(Point p1, Point p2) 
	{
		point = Path.pathAx(point, speedX);
	}

}
