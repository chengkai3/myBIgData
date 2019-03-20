package Object;
/**
 * ÁÅ»úA
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class FriendPlaneA extends FlyingObject
{
	public FriendPlaneA(Point point, ArrayList<BufferedImage> images, int life,int speedX,int speedY) 
	{
		super(point, images, life, speedX, speedY);
	}
	public void move(Point p1, Point p2) 
	{
	}
	public void move(HeroPlane f) 
	{
		point.x = f.getPoint().x-f.getWidth()/2-this.getWidth()/2;
		point.y = f.getPoint().y;
	}
}
