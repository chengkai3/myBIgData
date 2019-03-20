package basic;
/**
 * 轨迹类：包括敌机和各种子弹的移动轨迹
 * @author anwu
 *
 */
import java.awt.Point;
public class Path 
{
	//横向移动
	public static Point pathAx(Point p,int speedX)
	{
		p.x+=speedX;
		return p;
	}
	//纵向移动
	public static Point pathAy(Point p,int speedY)
	{
		p.y+=speedY;
		return p;
	}
	//斜向移动
	public static Point pathB(Point p,int speedX,int speedY)
	{
		p.x+=speedX;
		p.y+=speedY;
		return p;
	}
	//跟踪移动(p1为被跟踪目标位置，p2为跟踪目标位置)
	public static Point pathC(Point p1,Point p2,int speedX,int speedY,int range)
	{
		if((range>0&&(p1.y-p2.y)<range)||(range<0&&(p1.y-p2.y)>range))
		{
			p2.x+=speedX;
			p2.y+=speedY;
			return p2;
		}
		else
		{
			if((p1.y-p2.y)!=0)
			{
				p2.x = (p2.x+(p1.x-p2.x)*speedY/(p1.y-p2.y));
			}
			p2.y+=speedY;
			return p2;
		}
	}

}
