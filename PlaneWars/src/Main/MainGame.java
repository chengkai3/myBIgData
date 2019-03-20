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
	//���������Ｏ��
	public ArrayList<FlyingObject> flyingObjects;
	//����Ӣ�ۻ�����
	public ArrayList<FlyingObject> bullets;
	//�����л��ӵ�����
	public ArrayList<FlyingObject> enemyBullets;
	//����Ӣ�ۻ�����
	public HeroPlane hero;
	//��ǰ����ͼ
	public BufferedImage backGround;

	public int[] backPointY;
	//�������ϽǶ�������
	public Point p1;
	//�������½Ƕ�������
	public Point p2;
	public GameState gState;
	//����
	public int score;
	public AwardType awdType;
	//�л�����Ƶ��
	public int flyingObjectFre;
	//�����ƶ�Ƶ��
	public int backGroundFre;
	//boss�ȴ�����ʱ��
	public int warningTime;
	//bossʱ��
	public boolean bossTime;
	//��ʱ��
	public TimerAct tAct;
	//�ؿ�
	public int level;
	//boss����������־
	public boolean bossExist;
	//boss�����ؿ�����ֵ����
	public int[] bossLifeValue = {600,700,800};
	//Ӣ�ۻ�����ֵ
	public int heroPower;
	//Ӣ�ۻ��������ֵ
	public final static int HEROPOWERMAX = 100;
	//boss������ʱ����ƣ�����ѭ��������
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
	//����ͼ���ƣ�������������ͼ����
	public void paint(Graphics g)
	{
		//�ƶ������Ļ���
		g.drawImage(backGround, 0,backPointY[0], null);
		g.drawImage(backGround, 0,backPointY[1], null);
		if(this.gState == GameState.START&&level == 1)
		{
			g.drawImage(Load.start.get(0), 0,0, null);
		}
		//����Ӣ�ۻ�
		PaintObj.paintHero(g,this);
		//���Ʒ����buff�͵л���
		PaintObj.paintFlyingObject(g,this);
		//����Ӣ�ۻ��ӵ�
		PaintObj.paintBullet(g,this);
		//����boss������ֵ
		PaintObj.paintBossLifeValue(g, this);
		//����������
		PaintObj.paintPowerValue(g, this);
		//���Ʒ���
		PaintObj.paintScore(g, score);
		//����Ӣ�ۻ�����ֵ
		PaintObj.paintHeroLife(g, this);
		//��ʱ����boss�������
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
	//������
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
					//���÷�����������Ϊ
					Generate.generateFlyingObject(game);
					//���÷������ƶ���Ϊ
					Move.move(game);
					//���ñ����ƶ���Ϊ
					BackMove.backMove(game);
					//���÷�����Խ��������Ϊ
					ObjOut.objOut(game);
					//���÷�������ײ��Ϊ
					Crash.crash(game);
					//���÷����ּ�ʱ��Ϊ
					TimerAct.timer(game);
					//���ö������ټ����Ϊ
					DestroyCheck.destroyCheck(game);
					//���ö���������Ϊ
					Destroy.detroy(game);
					//������Щ���������Ϊ
					GameStateCheck.gameOverCheck(game);
					//������Ϸ���������Ϊ
					GameStateCheck.levelUpCheck(game);
				}
				//���ػ�����һ���������̣߳���֤��ͼ�����ͬ������
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
