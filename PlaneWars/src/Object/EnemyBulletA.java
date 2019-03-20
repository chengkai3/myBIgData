package Object;
/**
 * µÐ·½×Óµ¯A
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import basic.Path;
public class EnemyBulletA  extends FlyingObject
{

	public EnemyBulletA(Point point, ArrayList<BufferedImage> images, int life,int speedX,int speedY) 
	{
		super(point, images, life, speedX, speedY);
		this.life = 1;
		this.boomTime = 1;
	}

	@Override
	public void move(Point p1, Point p2) 
	{
		point = Path.pathAy(point, speedY);
	}

}
