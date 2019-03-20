package basic;
/**
 * 模型类:用来定义各种对象间的碰撞方法
 * @author anwu
 *
 */
import Object.BossPlane;
import Object.FlyingObject;
import Object.HeroPlane;
import Object.UniqueSkillA;
import Object.UniqueSkillB;
public class Model 
{
	public static final double PI = 3.141592653;
	//获取模型坐标数组
	public static double[] getMod(FlyingObject o)
	{
		double[] arr = new double[]{o.getWidth()<=o.getHeight()?o.getWidth():o.getHeight()};
		if(o instanceof HeroPlane)
		{
			arr = new double[]{o.getWidth()<=o.getHeight()?(double)o.getWidth()*3/4:(double)o.getHeight()*3/4};
		}
		if(o instanceof BossPlane)
		{
			arr = new double[2];
			arr[0] = (double)o.getWidth()*5;
			arr[1] = (double)o.getHeight()/2;
		}
		return arr;
	}
	//发生碰撞的判断
	public static boolean crash(FlyingObject o1,FlyingObject o2)
	{
		double[] mod2 = getMod(o2);
		double l = (o1.getPoint().x-o2.getPoint().x)*(o1.getPoint().x-o2.getPoint().x)+(o1.getPoint().y-o2.getPoint().y)*(o1.getPoint().y-o2.getPoint().y);
		if(o2 instanceof BossPlane)
		{
			double k = (double)(o1.getPoint().x-o2.getPoint().x)/(o1.getPoint().y-o2.getPoint().y);
			double sq = mod2[0]*mod2[0]*mod2[1]*mod2[1]/((k*k+1)+(mod2[1]*mod2[1]*k*k+mod2[0]*mod2[0]));
			if(l<sq)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(o2 instanceof UniqueSkillB)
		{
			if(o2.getPoint().y<o1.getPoint().y+o1.getHeight()/4&&o2.getPoint().y>o1.getPoint().y-o1.getHeight()/4)
			{
				return true;
			}
		}
		else if(o2 instanceof UniqueSkillA)
		{
			if(Math.abs(o1.getPoint().x-o2.getPoint().x)<o2.getWidth()/2)
			{
				return true;
			}
		}
		else
		{
			if(l<(mod2[0])*(mod2[0]))
			{
				return true;
			}
		}
		return false;
	}
}
