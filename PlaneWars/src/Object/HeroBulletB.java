package Object;
/**
 * Ó¢ÐÛ»ú×Óµ¯B
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import basic.Path;
public class HeroBulletB extends FlyingObject
{
	public HeroBulletB(Point point, ArrayList<BufferedImage> images, int life, int speedX, int speedY) 
	{
		super(point, images, life, speedX, speedY);
	}

	@Override
	public void move(Point p1, Point p2)
	{
		if(this.timerAct.timerAction(10, 200, 1000, 1, 0))
		{
			this.speedX*=-1;
		}
		point = Path.pathB(point, speedX, speedY);
	}

}
