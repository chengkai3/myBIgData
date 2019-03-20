package Main;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ActionTotal.BackMove;
import ActionTotal.Crash;
import ActionTotal.Destroy;
import ActionTotal.DestroyCheck;
import ActionTotal.GameStateCheck;
import ActionTotal.Generate;
import ActionTotal.Listener;
import ActionTotal.Load;
import ActionTotal.Move;
import ActionTotal.MusicPlay;
import ActionTotal.ObjOut;
import ActionTotal.PaintObj;
import Object.FlyingObject;
import Object.HeroPlane;
import basic.AwardType;
import basic.GameState;
import basic.TimerAct;
import sun.audio.AudioStream;
public class MainGame extends JPanel
{
	private static final long serialVersionUID = 1L;
	//创建飞行物集合
	public ArrayList<FlyingObject> flyingObjects;
	//创建英雄机集合
	public ArrayList<FlyingObject> bullets;
	//创建敌机子弹集合
	public ArrayList<FlyingObject> enemyBullets;
	//创建英雄机对象
	public HeroPlane hero;
	//当前背景图
	public BufferedImage backGround;

	public int[] backPointY;
	//背景左上角顶点坐标
	public Point p1;
	//背景右下角顶点坐标
	public Point p2;
	public GameState gState;
	//分数
	public int score;
	public AwardType awdType;
	//敌机产出频率
	public int flyingObjectFre;
	//背景移动频率
	public int backGroundFre;
	//boss等待警告时间
	public int warningTime;
	//boss时间
	public boolean bossTime;
	//计时器
	public TimerAct tAct;
	//关卡
	public int level;
	//boss机存在与否标志
	public boolean bossExist;
	//boss机各关卡生命值数组
	public int[] bossLifeValue = {600,700,800};
	//英雄机能量值
	public int heroPower;
	//英雄机最大能量值
	public final static int HEROPOWERMAX = 100;
	//boss机出场时间控制（背景循环周数）
	public static int[] boss_Out = new int[]{3,3,3};
	public AudioStream ap = null;
	public AudioStream ap1 = null;
	public MainGame()
	{
		tAct = new TimerAct();
		flyingObjects = new ArrayList<FlyingObject>();
		bullets = new ArrayList<FlyingObject>();
		enemyBullets = new ArrayList<FlyingObject>();
		hero = new HeroPlane(new Point(Load.backGround.get(0).getWidth()/2,Load.backGround.get(0).getHeight()/2),Load.heroPlane,25,0,0);
		backGround = Load.backGround.get(0);
		backPointY = new int[2];
		backPointY[0] = 0;
		backPointY[1] = -backGround.getHeight();
		p1 = new Point();
		p2 = new Point(backGround.getWidth(),backGround.getHeight());
		gState = GameState.START;
		score = 0;
		flyingObjectFre = 40;
		awdType = AwardType.NONE;
		backGroundFre = 1;
		level = 1;
		warningTime = 0;
		bossTime = false;
		bossExist = false;
		heroPower = 0;
	}
	//背景图绘制，并调用其他绘图方法
	public void paint(Graphics g)
	{
		//移动背景的绘制
		g.drawImage(backGround, 0,backPointY[0], null);
		g.drawImage(backGround, 0,backPointY[1], null);
		if(this.gState == GameState.START&&level == 1)
		{
			g.drawImage(Load.start.get(0), 0,0, null);
		}
		//绘制英雄机
		PaintObj.paintHero(g,this);
		//绘制飞行物（buff和敌机）
		PaintObj.paintFlyingObject(g,this);
		//绘制英雄机子弹
		PaintObj.paintBullet(g,this);
		//绘制boss机生命值
		PaintObj.paintBossLifeValue(g, this);
		//绘制能量槽
		PaintObj.paintPowerValue(g, this);
		//绘制分数
		PaintObj.paintScore(g, score);
		//绘制英雄机生命值
		PaintObj.paintHeroLife(g, this);
		//定时绘制boss警告界面
		if(this.gState !=GameState.PAUSE&&tAct.timerAction(10, 7680*boss_Out[level-1]-2400, 10, 230, 7)&&!this.bossTime)
		{
			if(warningTime!=0)
			{
				warningTime++;
				if(warningTime<40)
				{
					g.drawImage(Load.warning.get(warningTime%2), 0,0, null);
				}
				if(warningTime==80)
				{
					ap1 = MusicPlay.music("music//warning.wav");
					warningTime = 1;
				}
			}
		}
		if(this.gState == GameState.PAUSE)
		{
			g.drawImage(Load.pause.get(0), 0,0, null);
		}
		if(this.gState == GameState.LEVEL_UP)
		{
			g.drawImage(Load.levelUp.get(0), 0,0, null);
			
			String str = score+"";
			char[] c = str.toCharArray();
			int width = Load.numbers.get(0).getWidth();
			int pos = 220;
			for(int i = 0;i<c.length;i++)
			{
				g.drawImage(Load.numbers.get(Integer.parseInt(c[i]+"")), pos+i*width, 605,25,31,null);
			}
		}
	}
	//主方法
	public void action(MainGame game)
	{
		Timer timer = new Timer();
		int intervel = 10;
		timer.schedule(new TimerTask()
		{
			public void run() 
			{
				if(game.gState == GameState.RUNNING)
				{
					//调用飞行物生成行为
					Generate.generateFlyingObject(game);
					//调用飞行物移动行为
					Move.move(game);
					//调用背景移动行为
					BackMove.backMove(game);
					//调用飞行物越界销毁行为
					ObjOut.objOut(game);
					//调用飞行物碰撞行为
					Crash.crash(game);
					//调用防护罩计时行为
					TimerAct.timer(game);
					//调用对象销毁检查行为
					DestroyCheck.destroyCheck(game);
					//调用对象销毁行为
					Destroy.detroy(game);
					//调用有些结束检查行为
					GameStateCheck.gameOverCheck(game);
					//调用游戏升级检查行为
					GameStateCheck.levelUpCheck(game);
				}
				//给重画创建一个单独的线程，保证绘图与计算同步进行
				Thread t1 = new Thread()
				{
					public void run()
					{
						repaint();
					}
				};
				t1.start();
			}
		}, intervel, intervel);
	}
	public static void main(String[] args)
	{
		MainGame game = new MainGame();
		JFrame frame = new JFrame("ShootGame");
		frame.add(game);
		frame.addMouseListener(Listener.getMouseAdapter(game));
		frame.addMouseMotionListener(Listener.getMouseAdapter(game));
		frame.addKeyListener(Listener.getKeyAdapter(game));
		frame.setSize(Load.backGround.get(0).getWidth()+6,750);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		game.action(game);
	}
}
