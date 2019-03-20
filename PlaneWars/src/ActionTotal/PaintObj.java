package ActionTotal;
/**
 * 绘图类：定义了除了主窗体以外对象的绘制的静态方法
 */
import java.awt.Graphics;
import Main.MainGame;
import Object.BossPlane;
import Object.FlyingObject;

public class PaintObj 
{
	private static int starGlint = 0;
	public static void paintHero(Graphics g,MainGame game)
	{
		g.drawImage(game.hero.getImage(), game.hero.getPoint().x-game.hero.getWidth()/2, game.hero.getPoint().y-game.hero.getHeight()/2, null);
	}
	public static void paintBullet(Graphics g,MainGame game)
	{
		try
		{
			for(FlyingObject b:game.bullets)
			{
				g.drawImage(b.getImage(), b.getPoint().x-b.getWidth()/2, b.getPoint().y, null);
			}
			for(FlyingObject b:game.enemyBullets)
			{
				g.drawImage(b.getImage(), b.getPoint().x-b.getWidth()/2, b.getPoint().y, null);
			}
		}
		catch(Exception e)
		{

		}
	}
	public static void paintFlyingObject(Graphics g,MainGame game)
	{
		try
		{
			for(FlyingObject f:game.flyingObjects)
			{
				if(f instanceof BossPlane)
				{
					g.drawImage(f.getImage(), f.getPoint().x-f.getWidth()/2, f.getPoint().y-f.getHeight()/4, null);
				}
				else
				{
					g.drawImage(f.getImage(), f.getPoint().x-f.getWidth()/2, f.getPoint().y-f.getHeight()/2, null);
				}
			}
		}
		catch(Exception e)
		{

		}
	}
	public static void paintBossLifeValue(Graphics g,MainGame game)
	{
		double life = 0;
		if(game.bossExist)
		{
			for(FlyingObject f :game.flyingObjects)
			{
				if(f instanceof BossPlane)
				{
					life = (double)f.getLife()/game.bossLifeValue[game.level-1]*240;
				}
			}
			g.drawImage(Load.bossBlood.get(0), 142, 18,(int)life,15, null);
			g.drawImage(Load.bossLife.get(0), 142, 16,240,17, null);
		}
	}
	public static void paintPowerValue(Graphics g, MainGame game)
	{
		double power = (double)game.heroPower/MainGame.HEROPOWERMAX*112;
		g.drawImage(Load.power.get(0), 33, 54 ,(int)power,15,null);
		g.drawImage(Load.powerValue.get(0), 27, 54 ,120,18,null);
		g.drawImage(Load.powerWord.get(0), 7, 40 ,41,31,null);
	}
	public static void paintHeroLife(Graphics g, MainGame game)
	{
		double life = (double)game.hero.getLife()/game.hero.getMaxLife()*110;
		g.drawImage(Load.heroLifeMax.get(0), 30, 20 ,110,16,null);
		g.drawImage(Load.heroLife.get(0), 30, 20 ,(int)life,16,null);
		g.drawImage(Load.heroLifeWord.get(0), 7, 5,40,30 ,null);
	}
	public static void paintScore(Graphics g,int num)
	{
		String str = num+"";
		char[] c = str.toCharArray();
		int width = Load.numbers.get(0).getWidth();
		int pos = 440;
		for(int i = 0;i<c.length;i++)
		{
			g.drawImage(Load.numbers.get(Integer.parseInt(c[i]+"")), pos+i*width, 11,20,26,null);
		}
		g.drawImage(Load.score.get(((starGlint++)/100)%6), 420, 11,20,26,null);
	}
}
