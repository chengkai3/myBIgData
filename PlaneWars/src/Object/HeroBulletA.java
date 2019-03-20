package Object;
/**
 * 英雄机子弹A
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import basic.Path;
public class HeroBulletA extends FlyingObject
{
	//子弹类型
	public HeroBulletA(Point point, ArrayList<BufferedImage> images, int life,int speedX,int speedY) 
	{
		super(point, images, life, speedX, speedY);
		this.boomTime = 1;
	}
	public void move(Point p1,Point p2)
	{
		point = Path.pathB(point, -speedX, -speedY);
	}
}
