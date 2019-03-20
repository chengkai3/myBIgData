package Object;
/**
 * µÐ»úC
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import basic.Path;
public class EnemyPlaneC extends FlyingObject implements Enemy
{

	public EnemyPlaneC(Point point, ArrayList<BufferedImage> images, int life,int speedX,int speedY)
	{
		super(point, images, life, speedX, speedY);
		this.boomTime = 20;
	}
	public int getScore() 
	{
		return 20;
	}
	public void move(Point p1, Point p2)
	{
		point = Path.pathC(p1, p2, speedX, speedY,100);
	}

}
