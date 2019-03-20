package Object;
/**
 * Ó¢ÐÛ»ú
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class HeroPlane extends FlyingObject
{
	private int hitted;
	private int maxLife;
	public HeroPlane(Point point, ArrayList<BufferedImage> images, int life,int speedX,int speedY)
	{
		super(point, images, life, speedX, speedY);
		this.boomTime = 20;
		hitted = 0;
		maxLife = life;
	}
	public void move(int x,int y) 
	{
		this.point.x = x;
		this.point.y = y;
	}
	public void move(Point p1, Point p2){}

	public void setLife(int life) 
	{
		this.life = life;
	}

	public int getHitted()
	{
		return hitted;
	}

	public void setHitted(int hitted) 
	{
		this.hitted = hitted;
	}
	public int getMaxLife() 
	{
		return maxLife;
	}
	public void setMaxLife(int maxLife) 
	{
		this.maxLife = maxLife;
	}
	
}
