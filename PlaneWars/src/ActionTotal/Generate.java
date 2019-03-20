package ActionTotal;
/**
 * 对象生成类：定义了生成各种飞行物对象的静态方法。
 */
import java.awt.Point;
import java.util.Random;
import Main.MainGame;
import Object.BossPlane;
import Object.Buff;
import Object.EnemyBulletA;
import Object.EnemyBulletB;
import Object.EnemyPlaneA;
import Object.EnemyPlaneB;
import Object.EnemyPlaneC;
import Object.EnemyPlaneD;
import Object.FlyingObject;
import Object.FriendPlaneA;
import Object.FriendPlaneB;
import Object.HeroBulletA;
import Object.HeroBulletB;
import Object.HeroBulletC;
import basic.GameState;
import sun.audio.AudioPlayer;
public class Generate 
{
	
	//buff出场时间控制（间隔秒数）
	private static int[] buff_Out =new int[]{4,5,6};
	//planeA出场时间控制（间隔秒数）
	private static int[] plane_A_Out =new int[]{1,1,1};
	//planeB出场时间控制（间隔秒数）
	private static int[] plane_B_Out =new int[]{5,4,3};
	//planeC出场时间控制（间隔秒数）
	private static int[] plane_C_Out =new int[]{8,7,6};
	//planeD出场时间控制（间隔秒数）
	private static int[] plane_D_Out =new int[]{4,4,4};
	private static int num2 = -4;
	private static int i = 1;
	public static void generateFlyingObject(MainGame game)
	{
		Random rand = new Random();
		if(!game.bossExist&&game.gState==GameState.RUNNING)
		{
			game.bossTime = game.tAct.timerAction(10, 7680*MainGame.boss_Out[game.level-1], 100, 1, 0);
		}
		//生成buff
		if(game.tAct.timerAction(10,1000*buff_Out[game.level-1],100, 1, 1))
		{
			int leftBound = Load.buffA.get(0).getWidth()/2;
			int rangeOfBound = Load.backGround.get(0).getWidth()-Load.buffA.get(0).getWidth();
			game.flyingObjects.add(new Buff(new Point(rand.nextInt(rangeOfBound)+leftBound,-Load.buffA.get(0).getHeight()/2),Load.buffA,1,rand.nextInt(2)==1?1:-1,2));
		}
		//生成boss
		if(game.bossTime)
		{
			if(!game.bossExist)
			{
				AudioPlayer.player.stop(game.ap);
				game.ap = MusicPlay.music("music//boss.wav");
				switch(game.level)
				{
				case 1:
					game.flyingObjects.add(new BossPlane(new Point(256,80),Load.bossPlaneA,game.bossLifeValue[0],1,0));
					break;
				case 2:
					game.flyingObjects.add(new BossPlane(new Point(256,80),Load.bossPlaneB,game.bossLifeValue[1],1,0));
					break;
				case 3:
					game.flyingObjects.add(new BossPlane(new Point(256,80),Load.bossPlaneC,game.bossLifeValue[2],1,0));
				}
				game.bossExist = true;
			}
		}
		else
		{
			//生成敌机A
			if(game.tAct.timerAction(10,1000*plane_A_Out[game.level-1],100, 1, 2))
			{
				int leftBound = Load.enemyPlaneA.get(0).getWidth()/2;
				int rangeOfBound = Load.backGround.get(0).getWidth()-Load.enemyPlaneA.get(0).getWidth();
				game.flyingObjects.add(new EnemyPlaneA(new Point(rand.nextInt(rangeOfBound)+leftBound,-Load.enemyPlaneA.get(0).getHeight()/2),Load.enemyPlaneA,3,0,3));
			}
			//生成敌机B
			if(game.tAct.timerAction(10,1000*plane_B_Out[game.level-1],100, 1, 3))
			{
				game.flyingObjects.add(new EnemyPlaneB(new Point(0,200),Load.enemyPlaneB,5,1,0));
				game.flyingObjects.add(new EnemyPlaneB(new Point(512,400),Load.enemyPlaneB,5,1,0));
			}
			//生成敌机C
			if(game.tAct.timerAction(10,1000*plane_C_Out[game.level-1],100, 1, 4))
			{
				game.flyingObjects.add(new EnemyPlaneC(new Point(20,0),Load.enemyPlaneC,3,0,3));
				game.flyingObjects.add(new EnemyPlaneC(new Point(492,0),Load.enemyPlaneC,3,0,3));
			}
			//生成敌机D
			if(game.tAct.timerAction(10,1000*plane_D_Out[game.level-1],500, 3, 5))
			{
				game.flyingObjects.add(new EnemyPlaneD(new Point(0,200),Load.enemyPlaneD,3,2,3));
				game.flyingObjects.add(new EnemyPlaneD(new Point(512,300),Load.enemyPlaneD,3,2,3));
			}
		}
		//根据子弹状态生成不同的友方子弹
		switch(game.awdType)
		{
		//双倍火力
		case DOUBLE_FIRE:
			if(game.hero.getTimerAct().timerAction(10, 100, 100, 1,0))
			{
				game.bullets.add(new HeroBulletA(new Point(game.hero.getPoint().x-game.hero.getWidth()/4,game.hero.getPoint().y-game.hero.getHeight()/2-Load.heroBulletA.get(0).getHeight()/2),Load.heroBulletA,1,0,5));
				game.bullets.add(new HeroBulletA(new Point(game.hero.getPoint().x+game.hero.getWidth()/4,game.hero.getPoint().y-game.hero.getHeight()/2-Load.heroBulletA.get(0).getHeight()/2),Load.heroBulletA,1,0,5));
			}
			break;
		case ROCK:
			if(game.hero.getTimerAct().timerAction(10, 200, 200, 1,0))
			{
				game.bullets.add(new HeroBulletB(new Point(game.hero.getPoint().x-game.hero.getWidth()/4,game.hero.getPoint().y-game.hero.getHeight()/2-Load.heroBulletB.get(0).getHeight()/2),Load.heroBulletB,1,4,-4));
				game.bullets.add(new HeroBulletB(new Point(game.hero.getPoint().x+game.hero.getWidth()/4,game.hero.getPoint().y-game.hero.getHeight()/2-Load.heroBulletB.get(0).getHeight()/2),Load.heroBulletB,1,-4,-4));
			}
			break;
		case ROCKET:
			if(game.hero.getTimerAct().timerAction(10, 100, 100, 1,0))
			{
				game.bullets.add(new HeroBulletC(new Point(game.hero.getPoint().x,game.hero.getPoint().y-game.hero.getHeight()/2-Load.heroBulletA.get(0).getHeight()/2),Load.heroBulletC,1,0,-3));
			}
			break;
			//散弹
		case SHOT_GUN:
			if(game.hero.getTimerAct().timerAction(10, 100, 100, 1,0))
			{
				game.bullets.add(new HeroBulletA(new Point(game.hero.getPoint().x,game.hero.getPoint().y-game.hero.getHeight()/2),Load.heroBulletA,1,1,5));
				game.bullets.add(new HeroBulletA(new Point(game.hero.getPoint().x,game.hero.getPoint().y-game.hero.getHeight()/2),Load.heroBulletA,1,0,5));
				game.bullets.add(new HeroBulletA(new Point(game.hero.getPoint().x,game.hero.getPoint().y-game.hero.getHeight()/2),Load.heroBulletA,1,-1,5));
			}
			break;
			//其他奖励，不改变子弹类型
		default:
			if(game.hero.getTimerAct().timerAction(10, 100, 100, 1,0))
			{
				game.bullets.add(new HeroBulletA(new Point(game.hero.getPoint().x,game.hero.getPoint().y-game.hero.getHeight()/2-Load.heroBulletA.get(0).getHeight()/2),Load.heroBulletA,1,0,5));
			}
		}
		//生成敌方子弹
		for(FlyingObject f:game.flyingObjects)
		{
			if(f instanceof FriendPlaneA)
			{
				if(f.getTimerAct().timerAction(10, 100, 100, 1,1))
				{
					game.bullets.add(new HeroBulletA(new Point(f.getPoint().x,f.getPoint().y-f.getHeight()/2-Load.heroBulletA.get(0).getHeight()/2),Load.friendBulletA,1,0,5));
				}
			}
			else if(f instanceof FriendPlaneB)
			{
				if(f.getTimerAct().timerAction(10, 100, 100, 1,1))
				{
					game.bullets.add(new HeroBulletA(new Point(f.getPoint().x,f.getPoint().y-f.getHeight()/2-Load.heroBulletA.get(0).getHeight()/2),Load.heroBulletA,1,0,5));
				}
			}
			else if(f instanceof EnemyPlaneA)
			{
				if(f.getTimerAct().timerAction(10, 1000, 1000, 1,0))
				{
					game.enemyBullets.add(new EnemyBulletA(new Point(f.getPoint().x,f.getPoint().y+f.getHeight()/2),Load.enemyBulletA,1,0,4));
				}
			}
			else if(f instanceof EnemyPlaneB)
			{
				if(f.getTimerAct().timerAction(10, 1000, 200, 1,0))
				{
					game.enemyBullets.add(new EnemyBulletB(new Point(f.getPoint().x,f.getPoint().y+f.getHeight()/2),Load.enemyBulletA,1,0,4));
					game.enemyBullets.add(new EnemyBulletB(new Point(f.getPoint().x,f.getPoint().y+f.getHeight()/2),Load.enemyBulletA,1,1,4));
					game.enemyBullets.add(new EnemyBulletB(new Point(f.getPoint().x,f.getPoint().y+f.getHeight()/2),Load.enemyBulletA,1,-1,4));
				}
			}
			else if(f instanceof BossPlane)
			{
				if(f.getTimerAct().timerAction(10, 3000, 100, 16,0))
				{
					game.enemyBullets.add(new EnemyBulletB(new Point(f.getPoint().x,f.getPoint().y),Load.enemyBulletA,1,num2+=i,3));
					if(num2 ==4||num2 == -4)
					{
						i *= -1;
					}
				}
				if(f.getTimerAct().timerAction(10, 4000, 200, 1,1))
				{
					game.enemyBullets.add(new EnemyBulletB(new Point(f.getPoint().x-f.getWidth()/5,f.getPoint().y),Load.enemyBulletB,1,0,3));
					game.enemyBullets.add(new EnemyBulletB(new Point(f.getPoint().x-f.getWidth()/3,f.getPoint().y),Load.enemyBulletB,1,0,3));
					game.enemyBullets.add(new EnemyBulletB(new Point(f.getPoint().x+f.getWidth()/3,f.getPoint().y),Load.enemyBulletB,1,0,3));
					game.enemyBullets.add(new EnemyBulletB(new Point(f.getPoint().x+f.getWidth()/5,f.getPoint().y),Load.enemyBulletB,1,0,3));
				}
				if(f.getTimerAct().timerAction(10, 2000, 200, 1,2))
				{
					game.enemyBullets.add(new EnemyBulletB(new Point(f.getPoint().x,f.getPoint().y),Load.enemyBulletC,1,0,3));
					game.enemyBullets.add(new EnemyBulletB(new Point(f.getPoint().x,f.getPoint().y),Load.enemyBulletC,1,-1,3));
					game.enemyBullets.add(new EnemyBulletB(new Point(f.getPoint().x,f.getPoint().y),Load.enemyBulletC,1,-2,3));
					game.enemyBullets.add(new EnemyBulletB(new Point(f.getPoint().x,f.getPoint().y),Load.enemyBulletC,1,1,3));
					game.enemyBullets.add(new EnemyBulletB(new Point(f.getPoint().x,f.getPoint().y),Load.enemyBulletC,1,2,3));
				}
			}
		}
	}
}

