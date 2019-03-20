package basic;
/**
 * �켣�ࣺ�����л��͸����ӵ����ƶ��켣
 * @author anwu
 *
 */
import java.awt.Point;
public class Path 
{
	//�����ƶ�
	public static Point pathAx(Point p,int speedX)
	{
		p.x+=speedX;
		return p;
	}
	//�����ƶ�
	public static Point pathAy(Point p,int speedY)
	{
		p.y+=speedY;
		return p;
	}
	//б���ƶ�
	public static Point pathB(Point p,int speedX,int speedY)
	{
		p.x+=speedX;
		p.y+=speedY;
		return p;
	}
	//�����ƶ�(p1Ϊ������Ŀ��λ�ã�p2Ϊ����Ŀ��λ��)
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
