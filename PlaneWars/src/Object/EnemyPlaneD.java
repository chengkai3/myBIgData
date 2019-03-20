package Object;
/**
 * µÐ»úD
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import basic.Path;
public class EnemyPlaneD extends FlyingObject implements Enemy
{

	public EnemyPlaneD(Point point, ArrayList<BufferedImage> images, int life,int speedX,int speedY)
	{
		super(point, images, life, speedX, speedY);
		this.boomTime = 20;
	}

	@Override
	public int getScore() 
	{
		return 20;
	}

	public void move(Point p1, Point p2) 
	{
		if(point.y>p1.y&&point.x<p1.x)
		{
			point = Path.pathB(point, speedX, -speedY);
		}
		else if(point.y>p2.y&&point.x>p2.x)
		{
			point = Path.pathB(point, -speedX, -speedY);
		}
		else
		{
			point = Path.pathAy(point, speedY);
		}
	}

}
