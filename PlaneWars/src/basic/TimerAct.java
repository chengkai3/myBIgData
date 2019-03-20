package basic;
/**
 * 计时器类：定义了游戏运行中各种计时操作的静态方法。
 */
import ActionTotal.Load;
import Main.MainGame;
public class TimerAct 
{
	//背景翻转一定次数后，出现boss
	private int[] startTime;
	private int[] actTimes;
	private int[] actTime;
	public TimerAct()
	{
		//开始计时时间
		startTime = new int[8];
		//出发间隔时间
		actTime = new int[8];
		//触发次数
		actTimes = new int[8];
	}
	public boolean timerAction(int outInteval,int targetInteval,int actInteval,int num,int timeNum)
	{
		if(actTimes[timeNum]<num&&actTimes[timeNum]!=0)
		{
			if(actTime[timeNum]>=actInteval-10)
			{
				actTime[timeNum] = 0;
				actTimes[timeNum]++;
				return true;
			}
			else
			{
				actTime[timeNum]+=10;
				return false;
			}
		}
		if(actTimes[timeNum]>=num)
		{
			actTimes[timeNum] = 0;
			startTime[timeNum] = 2;
			return false;
		}
		if(startTime[timeNum]!=0&&startTime[timeNum]%(targetInteval/outInteval)==0)
		{
			
			actTimes[timeNum]++;
			return true;
		}
		else
		{
			startTime[timeNum]+=1;
		}
		return false;
	}
	private static long start = 0;
	private static long end = 0;
	private static int boss_start = 0;
	//防护罩计时器
	public static void timer(MainGame game)
	{
		if(game.hero.getImages() == Load.heroPlaneDefender&&start==0)
		{
			start = System.currentTimeMillis();
		}
		if(game.hero.getImages() == Load.heroPlaneDefender&&start!=0)
		{
			end = System.currentTimeMillis();
		}
		if(end-start>=5*1000)
		{
			game.hero.setImages(Load.heroPlane);
			start = 0;
			end = 0;
		}
	}
	public static boolean bossMoveTime()
	{
		boss_start++;
		if(boss_start%250 == 0)
		{
			boss_start = 0;
			return true;
		}
		else
		{
			return false;
		}
	}
}
