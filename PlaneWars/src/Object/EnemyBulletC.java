package Object;
/**
 * µÐ·½×Óµ¯C
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import basic.Path;
public class EnemyBulletC  extends FlyingObject
{

	public EnemyBulletC(Point point, ArrayList<BufferedImage> images, int life,int speedX,int speedY)
	{
		super(point, images, life, speedX, speedY);
	}

	@Override
	public void move(Point p1, Point p2) 
	{
		if(this.timerAct.timerAction(10, 200, 1000, 1, 0))
		{
			System.out.println("ck");
			this.speedX*=-1;
		}
		point = Path.pathB(point, speedX, speedY);
	}

}
