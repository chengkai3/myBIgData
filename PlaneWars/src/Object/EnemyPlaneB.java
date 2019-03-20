package Object;
/**
 * µÐ»úB
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import basic.Path;
public class EnemyPlaneB extends FlyingObject implements Enemy
{
	private boolean back = false;
	public EnemyPlaneB(Point point, ArrayList<BufferedImage> images, int life,int speedX,int speedY)
	{
		super(point, images, life, speedX, speedY);
		this.boomTime = 20;
	}

	@Override
	public int getScore() 
	{
		return 40;
	}

	@Override
	public void move(Point p1, Point p2)
	{
		if(point.x<=p1.x&&!back)
		{
			point = Path.pathAx(point, speedX);
			if(point.x>=p1.x)
			{
				point = Path.pathAx(point, -speedX);
				back = true;
			}
		}
		else if(point.x>=p2.x&&!back)
		{
			point = Path.pathAx(point, -speedX);
			if(point.x<=p2.x)
			{
				point = Path.pathAx(point, speedX);
				back = true;
			}
		}
		else if(point.x<=p1.x&&back)
		{
			point = Path.pathAx(point, -speedX);
		}
		else if(point.x>=p2.x&&back)
		{
			point = Path.pathAx(point, speedX);
		}
	}

}
