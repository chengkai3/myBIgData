package ActionTotal;
/**
 * ���ټ���ࣺ�����˼������Ƿ�ﵽ����������life<0�ұ�ըʱ���㹻���ľ�̬����
 */
import Main.MainGame;
import Object.FlyingObject;
import Object.FriendPlaneA;
import Object.FriendPlaneB;
import basic.ObjectState;
public class DestroyCheck 
{
	public static void destroyCheck(MainGame game)
	{
		//���ø����destroy�����������ټ��
		for(FlyingObject f:game.flyingObjects)
		{
			f.destroy();
			//���Ż���ʱ����
			if((f instanceof FriendPlaneA||f instanceof FriendPlaneB)&&f.getTimerAct().timerAction(10, 6000, 100, 1, 0))
			{
				f.setState(ObjectState.DESTROYED);
			}
		}
		for(FlyingObject f:game.bullets)
		{
			f.destroy();
		}
		for(FlyingObject f:game.enemyBullets)
		{
			f.destroy();
		}
	}
}
