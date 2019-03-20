package ActionTotal;
/**
 * ɾ�������ࣺ������ɾ������ľ�̬������
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
		//�����л���buff��ɾ�����Ϊdestroyed�Ķ���
		Iterator<FlyingObject> it = game.flyingObjects.iterator();
		while(it.hasNext())
		{
			FlyingObject f = (FlyingObject)it.next();
			if(f.getState() == ObjectState.DESTROYED)
			{
				if(f instanceof Enemy)
				{
					//���ٵл����󣬻�ȡ��Ӧ�ķ���
					Enemy e = (Enemy)f;
					game.score+=e.getScore();
					if(f instanceof BossPlane)
					{
						game.gState = GameState.GAME_OVER;
					}
				}
				if(f instanceof Award)
				{
					//����buff���󣬻�ȡ��Ӧ�Ľ�������
					Award a = (Award)f;
					//������
					if(a.getAward() == AwardType.ADD_LIFE)
					{
						game.hero.setLife(game.hero.getLife()+3);
					}
					//�ӷ�����
					else if(a.getAward() == AwardType.DEFENDER)
					{
						game.hero.setImages(Load.heroPlaneDefender);
					}
					//���ӵ�
					else
					{
						game.awdType = a.getAward();
					}
				}
				it.remove();
			}
		}
		//�����ӵ��࣬���ٱ��Ϊdestroyed���ӵ�
		Iterator<FlyingObject> ti = game.bullets.iterator();
		while(ti.hasNext())
		{
			FlyingObject f = (FlyingObject)ti.next();
			if(f.getState() == ObjectState.DESTROYED)
			{
				ti.remove();
			}
		}
		//�����о��ӵ��࣬���ٱ��Ϊdestroyed�ĵо��ӵ�
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
