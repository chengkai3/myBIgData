package Object;
/**
 * buff¿‡
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import ActionTotal.Load;
import basic.AwardType;
public class Buff extends FlyingObject implements Award
{
	private static Random rand = new Random();
	private AwardType awdType;
	public Buff(Point point, ArrayList<BufferedImage> images, int life,int speedX,int speedY) 
	{
		super(point, images, life, speedX, speedY);
		this.speedX = rand.nextInt(2)==1?1:-1;
		this.speedY = 2;
		this.awdType = generateAward();
		this.images = getImages(awdType);
		this.life = 1;
		this.boomTime = 10;
	}
	public void move(Point p1,Point p2) 
	{
		this.point.x+=this.speedX;
		this.point.y+=this.speedY;
		if(point.x>p2.x||point.x<p1.x)
		{
			this.speedX*=-1;
		}
	}
	public AwardType getAward() 
	{
		return awdType;
	}
	public static AwardType generateAward()
	{
		int i = rand.nextInt(7);
		switch(i)
		{
		case 0:
			return AwardType.ADD_LIFE;
		case 1:
			return AwardType.DOUBLE_FIRE;
		case 2:
			return AwardType.DEFENDER;
		case 3:
			return AwardType.FRIENDPLANE;
		case 5:
			return AwardType.ROCK;
		case 6:
			return AwardType.ROCKET;
		default:
			return AwardType.SHOT_GUN;
		}
	}
	public AwardType getAwdType() 
	{
		return awdType;
	}
	public ArrayList<BufferedImage> getImages(AwardType type)
	{
		switch(type)
		{
		case ADD_LIFE:
			return Load.buffA;
		case DOUBLE_FIRE:
			return Load.buffC;
		case DEFENDER:
			return Load.buffB;
		case ROCK:
			return Load.buffD;
		//case awardType.FRIENDPLANE
		case ROCKET:
			return Load.buffE;
		default:
			return Load.buffE;
		}
	}
}
