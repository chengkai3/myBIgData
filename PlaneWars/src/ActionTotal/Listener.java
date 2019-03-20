package ActionTotal;
/**
 *事件监听类：定义了事件监听的静态方法（包括鼠标和键盘的监听）
 */
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import Main.MainGame;
import Object.FlyingObject;
import Object.FriendPlaneA;
import Object.FriendPlaneB;
import Object.HeroPlane;
import Object.UniqueSkillA;
import Object.UniqueSkillB;
import basic.AwardType;
import basic.GameState;
import basic.TimerAct;
import sun.audio.AudioPlayer;
public class Listener 
{
	public static KeyAdapter getKeyAdapter(MainGame game)
	{
		KeyAdapter k = new KeyAdapter()
		{
			Timer timer = new Timer();
			int intervel = 2;
			boolean flag = false;
			public void keyPressed(KeyEvent e) 
			{
				//游戏处在运行状态下才进行监听
				if(game.gState == GameState.RUNNING&&flag == false)
				{
					timer = new Timer();
					flag = true;
					//监听上下左右方向键的点击和弹开事件，并用计时器控制其移动速度
					switch(e.getKeyCode())
					{
					case KeyEvent.VK_LEFT:
						timer.schedule(new TimerTask()
						{
							public void run() 
							{
								if(game.hero.getPoint().x>game.hero.getWidth()/2)
								{
									game.hero.getPoint().x--;
								}
							}
						},0,intervel);
						break;
					case KeyEvent.VK_RIGHT:
						timer.schedule(new TimerTask()
						{
							public void run() 
							{
								if(game.hero.getPoint().x<game.backGround.getWidth()-game.hero.getWidth()/2)
								{
									game.hero.getPoint().x++;
								}
							}
						},0,intervel);
						break;
					case KeyEvent.VK_UP:
						timer.schedule(new TimerTask()
						{
							public void run() 
							{
								if(game.hero.getPoint().y>game.hero.getHeight()/2)
								{
									game.hero.getPoint().y--;
								}
							}
						},0,intervel);
						break;
					case KeyEvent.VK_DOWN:
						timer.schedule(new TimerTask()
						{
							public void run() 
							{
								if(game.hero.getPoint().y<game.backGround.getHeight()-game.hero.getHeight()/2)
								{
									game.hero.getPoint().y++;
								}
							}
						},0,intervel);
						break;
					}
				}
			}
			public void keyReleased(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_LEFT||e.getKeyCode() == KeyEvent.VK_RIGHT||e.getKeyCode() == KeyEvent.VK_UP||e.getKeyCode() == KeyEvent.VK_DOWN)
				{
					timer.cancel();
					flag = false;
				}
			}
		};
		return k;
	}
	public static MouseAdapter getMouseAdapter(MainGame game)
	{
		Thread t = new Thread()
		{
			public void run()
			{

			}
		};
		t.start();
		//监听鼠标的点击、移动事件
		MouseAdapter l = new MouseAdapter()
		{
			public void mouseMoved(MouseEvent e)
			{
				if(game.gState == GameState.RUNNING)
				{
					game.hero.getPoint().x = e.getX();
					game.hero.getPoint().y = e.getY();
				}
			}
			public void mouseClicked(MouseEvent e)
			{
				if(game.gState == GameState.START)
				{
					game.warningTime++;
					game.ap = MusicPlay.music("music//fight.wav");
					game.gState = GameState.RUNNING;
				}
				if(game.gState == GameState.GAME_OVER||game.gState == GameState.LEVEL_UP)
				{
					game.gState = GameState.START;
					resetGame(game);
				}
				if(game.gState == GameState.RUNNING)
				{
					if(game.heroPower>=MainGame.HEROPOWERMAX/3&&game.heroPower<MainGame.HEROPOWERMAX*2/3)
					{
						game.heroPower-=MainGame.HEROPOWERMAX/3;
						game.bullets.add(new UniqueSkillA(new Point(256,0),Load.uniqueSkillA,20,1,0));
						game.bullets.add(new UniqueSkillA(new Point(256,0),Load.uniqueSkillA,20,-1,0));
					}
					else if(game.heroPower>=MainGame.HEROPOWERMAX*2/3&&game.heroPower<MainGame.HEROPOWERMAX)
					{
						//生成必杀2
						boolean flag = true;
						game.heroPower-=MainGame.HEROPOWERMAX*2/3;
						for(FlyingObject f : game.flyingObjects)
						{
							if(f instanceof FriendPlaneA)
							{
								flag = false;
								break;
							}
						}
						if(flag)
						{
							Point p1 = new Point(game.hero.getPoint().x-game.hero.getWidth()/2,game.hero.getPoint().y);
							Point p2 = new Point(game.hero.getPoint().x+game.hero.getWidth(),game.hero.getPoint().y);
							game.flyingObjects.add(new FriendPlaneA(p1,Load.friendPlaneA,1,0,0));
							game.flyingObjects.add(new FriendPlaneB(p2,Load.friendPlaneB,1,0,0));
						}
					}
					else if(game.heroPower == MainGame.HEROPOWERMAX)
					{
						//生成必杀3
						game.heroPower = 0;
						game.bullets.add(new UniqueSkillB(new Point(256,768),Load.uniqueSkillB,50,0,-10));
					}

				}
			}
			public void mouseEntered(MouseEvent e)
			{
				if(game.gState == GameState.PAUSE)
				{
					if(!game.bossExist)
					{
						game.ap = MusicPlay.music("music//fight.wav");
					}
					if(game.bossExist)
					{
						game.ap = MusicPlay.music("music//boss.wav");
					}
					game.gState = GameState.RUNNING;
				}
			}
			public void mouseExited(MouseEvent e)
			{
				if(game.gState == GameState.RUNNING)
				{
					AudioPlayer.player.stop(game.ap);
					game.gState = GameState.PAUSE;
				}
			}
		};
		return l;
	}
	public static void resetGame(MainGame game)
	{
		game.tAct = new TimerAct();
		game.flyingObjects.clear();
		game.bullets.clear();
		game.enemyBullets.clear();
		game.hero = new HeroPlane(new Point(Load.backGround.get(0).getWidth()/2,Load.backGround.get(0).getHeight()/2),Load.heroPlane,game.hero.getMaxLife(),0,0);
		game.backPointY = new int[2];
		game.score = 0;
		game.awdType = AwardType.NONE;
		game.backGroundFre = 1;
		game.bossTime = false;
		game.backPointY[0] = 0;
		game.backPointY[1] = -game.backGround.getHeight();
		game.backGround = Load.backGround.get(game.level-1);
		game.flyingObjectFre = 40;
		game.bossExist = false;
		game.heroPower = 0;
		game.warningTime = 0;
	}
}
