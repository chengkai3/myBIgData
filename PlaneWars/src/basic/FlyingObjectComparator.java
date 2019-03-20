package basic;
/**
 * 定义了flyingObjects的排序方法
 */
import java.util.Comparator;
import Object.FlyingObject;
public class FlyingObjectComparator implements Comparator<FlyingObject>
{
	private FlyingObject center;
	public FlyingObjectComparator(FlyingObject f)
	{
		center = f;
	}
	public int compare(FlyingObject o1, FlyingObject o2)
	{
		if(center.getPoint().y<o1.getPoint().y&&center.getPoint().y>o1.getPoint().y)
		{
			return -1;
		}
		else if(center.getPoint().y>o1.getPoint().y&&center.getPoint().y<o1.getPoint().y)
		{
			return 1;
		}
		else
		{
			int r1 = (o1.getPoint().x-center.getPoint().x)*(o1.getPoint().x-center.getPoint().x)+(o1.getPoint().y-center.getPoint().y)*(o1.getPoint().y-center.getPoint().y);
			int r2 = (o2.getPoint().x-center.getPoint().x)*(o2.getPoint().x-center.getPoint().x)+(o2.getPoint().y-center.getPoint().y)*(o2.getPoint().y-center.getPoint().y);
			return r1-r2;
		}
	}

}
