package Object;
/**
 * Ӣ�ۻ��ӵ�A
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import basic.Path;
public class HeroBulletA extends FlyingObject
{
	//�ӵ�����
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
