package ActionTotal;
/**
 * 删除对象类：定义了删除对象的静态方法；
 */
import java.util.Iterator;

import Main.MainGame;
import Object.Award;
import Object.BossPlane;
import Object.Enemy;
import Object.FlyingObject;
import basic.AwardType;
import basic.GameState;
import basic.ObjectState;
public class Destroy 
{
	public static synchronized void detroy(MainGame game)
	{
		//遍历敌机和buff，删除标记为destroyed的对象
		Iterator<FlyingObject> it = game.flyingObjects.iterator();
		while(it.hasNext())
		{
			FlyingObject f = (FlyingObject)it.next();
			if(f.getState() == ObjectState.DESTROYED)
			{
				if(f instanceof Enemy)
				{
					//销毁敌机对象，获取相应的分数
					Enemy e = (Enemy)f;
					game.score+=e.getScore();
					if(f instanceof BossPlane)
					{
						game.gState = GameState.GAME_OVER;
					}
				}
				if(f instanceof Award)
				{
					//销毁buff对象，获取相应的奖励类型
					Award a = (Award)f;
					//加生命
					if(a.getAward() == AwardType.ADD_LIFE)
					{
						game.hero.setLife(game.hero.getLife()+3);
					}
					//加防护罩
					else if(a.getAward() == AwardType.DEFENDER)
					{
						game.hero.setImages(Load.heroPlaneDefender);
					}
					//换子弹
					else
					{
						game.awdType = a.getAward();
					}
				}
				it.remove();
			}
		}
		//遍历子弹类，销毁标记为destroyed的子弹
		Iterator<FlyingObject> ti = game.bullets.iterator();
		while(ti.hasNext())
		{
			FlyingObject f = (FlyingObject)ti.next();
			if(f.getState() == ObjectState.DESTROYED)
			{
				ti.remove();
			}
		}
		//遍历敌军子弹类，销毁标记为destroyed的敌军子弹
		Iterator<FlyingObject> tii = game.enemyBullets.iterator();
		while(tii.hasNext())
		{
			FlyingObject f = (FlyingObject)tii.next();
			if(f.getState() == ObjectState.DESTROYED)
			{
				tii.remove();
			}
		}
	}
}
