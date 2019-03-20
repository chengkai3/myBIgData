package Object;
/**
 * boss机对象类
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import basic.Path;
import basic.TimerAct;
public class BossPlane extends FlyingObject implements Enemy
{
	private boolean moveAble = false;
	public BossPlane(Point point, ArrayList<BufferedImage> images, int life,int speedX,int speedY) 
	{
		super(point, images, life, speedX, speedY);
		this.boomTime = 200;
	}
	public void move(Point p1,Point p2) 
	{
		if(TimerAct.bossMoveTime())
		{
			moveAble = !moveAble;
		}
		if(moveAble&&p1.x<=point.x&&p2.x>=point.x)
		{
			Path.pathAx(point, speedX);
			if(p1.x>point.x||p2.x<point.x)
			{
				Path.pathAx(point, -speedX);
				speedX*=-1;
			}
		}
	}
	public int getScore() 
	{
		return 300;
	}
}
