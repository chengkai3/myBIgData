package Object;
/**
 * Ó¢ÐÛ»ú×Óµ¯C
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import basic.Path;
public class HeroBulletC extends FlyingObject
{

	public HeroBulletC(Point point, ArrayList<BufferedImage> images, int life,int speedX,int speedY) 
	{
		super(point, images, life, speedX, speedY);
	}
	public void move(Point p1, Point p2) 
	{
		point = Path.pathC(p1, p2, speedX, speedY,-10);
	}
	public void move()
	{
		point = Path.pathB(point, speedX, speedY);
	}

}
