package ActionTotal;
/**
 * 越界类：定义了对象超出窗体后删除的静态方法。
 */
import java.util.Iterator;

import Main.MainGame;
import Object.FlyingObject;
import Object.FriendPlaneA;
import Object.FriendPlaneB;
public class ObjOut 
{
	public static void objOut(MainGame game)
	{
		Iterator<FlyingObject> it = game.flyingObjects.iterator();
		while(it.hasNext())
		{
			FlyingObject f = (FlyingObject)it.next();
			if(f.outOfBound(game.p1, game.p2)&&!(f instanceof FriendPlaneA||f instanceof FriendPlaneB))
			{
				it.remove();
			}
		}
		Iterator<FlyingObject> ti = game.bullets.iterator();
		while(ti.hasNext())
		{
			FlyingObject f = (FlyingObject)ti.next();
			if(f.outOfBound(game.p1, game.p2))
			{
				ti.remove();
			}
		}
		Iterator<FlyingObject> tii = game.enemyBullets.iterator();
		while(tii.hasNext())
		{
			FlyingObject f = (FlyingObject)tii.next();
			if(f.outOfBound(game.p1, game.p2))
			{
				tii.remove();
			}
		}
	}
}
