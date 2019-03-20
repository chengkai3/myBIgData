package Object;
/**
 * µÐ»úA
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class EnemyPlaneA extends FlyingObject implements Enemy
{
	
	public EnemyPlaneA(Point point, ArrayList<BufferedImage> images, int life,int speedX,int speedY) 
	{
		super(point, images, life, speedX, speedY);
		this.boomTime = 20;
	}
	public void move(Point p1, Point p2) 
	{
		this.point.y+=speedY;
	}
	public int getScore() 
	{
		return 20;
	}
}
