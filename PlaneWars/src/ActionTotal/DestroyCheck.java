package ActionTotal;
/**
 * 销毁检查类：定义了检查对象是否达到销毁条件（life<0且爆炸时间足够）的静态方法
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
		//调用父类的destroy方法进行销毁检查
		for(FlyingObject f:game.flyingObjects)
		{
			f.destroy();
			//对僚机计时销毁
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
