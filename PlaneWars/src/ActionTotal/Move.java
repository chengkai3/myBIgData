package ActionTotal;
/**
 * 移动类：定义了对象移动的静态方法。
 */
import java.awt.Point;
import java.util.Collections;
import java.util.Comparator;
import Main.MainGame;
import Object.BossPlane;
import Object.Buff;
import Object.Enemy;
import Object.EnemyPlaneA;
import Object.EnemyPlaneB;
import Object.EnemyPlaneC;
import Object.EnemyPlaneD;
import Object.FlyingObject;
import Object.FriendPlaneA;
import Object.FriendPlaneB;
import Object.HeroBulletC;
import basic.FlyingObjectComparator;
import basic.ObjectState;
public class Move 
{
	public static void move(MainGame game)
	{
		game.hero.glint();
		for(FlyingObject f:game.flyingObjects)
		{
			if(f.getState() == ObjectState.ALIVE)
			{
				if(f instanceof Buff)
				{
					Buff b = (Buff)f;
					b.move(game.p1,game.p2);
				}
				else if(f instanceof EnemyPlaneA)
				{
					f.move(new Point(100,100),new Point(412,100));
				}
				else if(f instanceof EnemyPlaneD)
				{
					f.move(new Point(100,100),new Point(412,100));
				}
				else if(f instanceof EnemyPlaneB)
				{
					f.move(new Point(180,100),new Point(332,100));
				}
				else if(f instanceof EnemyPlaneC)
				{
					f.move(game.hero.getPoint(),f.getPoint());
				}
				else if(f instanceof BossPlane)
				{
					f.move(new Point(100,100),new Point(412,100));
				}
				else if(f instanceof FriendPlaneA)
				{
					FriendPlaneA p = (FriendPlaneA)f;
					p.move(game.hero);
				}
				else if(f instanceof FriendPlaneB)
				{
					FriendPlaneB p = (FriendPlaneB)f;
					p.move(game.hero);
				}
			}
			f.glint();
		}
		for(FlyingObject f:game.bullets)
		{
			Comparator<FlyingObject> compare = new FlyingObjectComparator(f);
			Collections.sort(game.flyingObjects, compare);
			FlyingObject p1 = null;
			if(f instanceof HeroBulletC)
			{
				for(FlyingObject ff: game.flyingObjects)
				{
					if(ff instanceof Enemy)
					{
						p1 = ff;
						break;
					}
				}
				if(p1!=null)
				{
					f.move(p1.getPoint(), f.getPoint());
				}
				else
				{
					f.getPoint().y+=f.getSpeedY();
				}
			}
			else
			{
				f.move(game.p1,game.p2);
			}
			f.glint();
		}
		for(FlyingObject f:game.enemyBullets)
		{
			f.move(game.p1,game.p2);
			f.glint();
		}
	}
}
