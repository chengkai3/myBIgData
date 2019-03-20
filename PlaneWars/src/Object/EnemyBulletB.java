package Object;
/**
 * µÐ·½×Óµ¯B
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import basic.Path;
public class EnemyBulletB  extends FlyingObject
{

	public EnemyBulletB(Point point, ArrayList<BufferedImage> images, int life,int speedX,int speedY) 
	{
		super(point, images, life, speedX, speedY);
	}

	@Override
	public void move(Point p1, Point p2) 
	{
		point = Path.pathB(point, speedX, speedY);
	}

}
