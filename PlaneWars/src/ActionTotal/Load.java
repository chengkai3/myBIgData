package ActionTotal;
/**
 * 图片加载类：定义了图片加载的静态方法。
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
public class Load 
{
	//使用集合存储图片，保证图片数量的灵活性
	public static ArrayList<BufferedImage> heroPlane ;
	public static ArrayList<BufferedImage> heroPlaneDefender;
	public static ArrayList<BufferedImage> heroBoom;
	public static ArrayList<BufferedImage> bulletBoom;
	public static ArrayList<BufferedImage> bossBoom;
	public static ArrayList<BufferedImage> enemyPlaneA;
	public static ArrayList<BufferedImage> enemyPlaneB;
	public static ArrayList<BufferedImage> enemyPlaneC;
	public static ArrayList<BufferedImage> enemyPlaneD;
	public static ArrayList<BufferedImage> bossPlaneA;
	public static ArrayList<BufferedImage> bossPlaneB;
	public static ArrayList<BufferedImage> bossPlaneC;
	public static ArrayList<BufferedImage> friendPlane;
	public static ArrayList<BufferedImage> buffA;
	public static ArrayList<BufferedImage> buffB;
	public static ArrayList<BufferedImage> buffC;
	public static ArrayList<BufferedImage> buffD;
	public static ArrayList<BufferedImage> buffE;
	public static ArrayList<BufferedImage> heroBulletA;
	public static ArrayList<BufferedImage> heroBulletB;
	public static ArrayList<BufferedImage> heroBulletC;
	public static ArrayList<BufferedImage> enemyBulletA;
	public static ArrayList<BufferedImage> enemyBulletB;
	public static ArrayList<BufferedImage> enemyBulletC;
	public static ArrayList<BufferedImage> backGround;
	public static ArrayList<BufferedImage> heroState;
	public static ArrayList<BufferedImage> bossLife;
	public static ArrayList<BufferedImage> bossBlood;
	public static ArrayList<BufferedImage> powerValue;
	public static ArrayList<BufferedImage> power;
	public static ArrayList<BufferedImage> powerWord;
	public static ArrayList<BufferedImage> uniqueSkillA;
	public static ArrayList<BufferedImage> uniqueSkillB;
	public static ArrayList<BufferedImage> uniqueSkillC;
	public static ArrayList<BufferedImage> friendPlaneA;
	public static ArrayList<BufferedImage> friendPlaneB;
	public static ArrayList<BufferedImage> friendBulletA;
	public static ArrayList<BufferedImage> warning;
	public static ArrayList<BufferedImage> numbers;
	public static ArrayList<BufferedImage> heroLife;
	public static ArrayList<BufferedImage> heroLifeMax;
	public static ArrayList<BufferedImage> heroLifeWord;
	public static ArrayList<BufferedImage> score;
	public static ArrayList<BufferedImage> start;
	public static ArrayList<BufferedImage> pause;
	public static ArrayList<BufferedImage> levelUp;
	static
	{
		try
		{
			heroPlane = loadImages(new File("picture\\heroPlane"));
			heroPlaneDefender = loadImages(new File("picture\\heroPlaneDefender"));
			heroBoom = loadImages(new File("picture\\boom\\heroBoom"));
			bulletBoom = loadImages(new File("picture\\boom\\bulletBoom"));
			bossBoom = loadImages(new File("picture\\boom\\bossBoom"));
			enemyPlaneA = loadImages(new File("picture\\enemyPlane\\enemyPlaneA"));
			enemyPlaneB = loadImages(new File("picture\\enemyPlane\\enemyPlaneB"));
			enemyPlaneC = loadImages(new File("picture\\enemyPlane\\enemyPlaneC"));
			enemyPlaneD = loadImages(new File("picture\\enemyPlane\\enemyPlaneD"));
			bossPlaneA = loadImages(new File("picture\\bossPlane\\bossPlaneA"));
			bossPlaneB = loadImages(new File("picture\\bossPlane\\bossPlaneB"));
			bossPlaneC = loadImages(new File("picture\\bossPlane\\bossPlaneC"));
			friendPlane = loadImages(new File("picture\\friendPlane"));
			buffA = loadImages(new File("picture\\buff\\buffA"));
			buffB = loadImages(new File("picture\\buff\\buffB"));
			buffC = loadImages(new File("picture\\buff\\buffC"));
			buffD = loadImages(new File("picture\\buff\\buffD"));
			buffE = loadImages(new File("picture\\buff\\buffE"));
			heroBulletA = loadImages(new File("picture\\bullet\\heroBulletA"));
			heroBulletB = loadImages(new File("picture\\bullet\\heroBulletB"));
			heroBulletC = loadImages(new File("picture\\bullet\\heroBulletC"));
			enemyBulletA = loadImages(new File("picture\\enemyBullet\\enemyBulletA"));
			enemyBulletB = loadImages(new File("picture\\enemyBullet\\enemyBulletB"));
			enemyBulletC = loadImages(new File("picture\\enemyBullet\\enemyBulletC"));
			backGround = loadImages(new File("picture\\backGround"));
			heroState = loadImages(new File("picture\\heroState"));
			bossLife = loadImages(new File("picture\\bossLife\\bossLifeA"));
			bossBlood = loadImages(new File("picture\\bossLife\\bossBlood"));
			powerValue = loadImages(new File("picture\\powerValue\\powerValue"));
			power = loadImages(new File("picture\\powerValue\\power"));
			powerWord = loadImages(new File("picture\\powerValue\\powerWord"));
			uniqueSkillA = loadImages(new File("picture\\uniqueSkill\\uniqueSkillA"));
			uniqueSkillB = loadImages(new File("picture\\uniqueSkill\\uniqueSkillB"));
			uniqueSkillC = loadImages(new File("picture\\uniqueSkill\\uniqueSkillC"));
			friendPlaneA = loadImages(new File("picture\\friendPlane\\friendPlaneA"));
			friendPlaneB = loadImages(new File("picture\\friendPlane\\friendPlaneB"));
			friendBulletA = loadImages(new File("picture\\bullet\\friendBulletA"));
			warning = loadImages(new File("picture\\backGround\\warning"));
			numbers = loadImages(new File("picture\\numbers"));
			heroLife = loadImages(new File("picture\\heroLife\\heroLife"));
			heroLifeMax = loadImages(new File("picture\\heroLife\\heroLifeMax"));
			heroLifeWord = loadImages(new File("picture\\heroLife\\heroLifeWord"));
			score = loadImages(new File("picture\\score"));
			start = loadImages(new File("picture\\process\\start"));
			pause = loadImages(new File("picture\\process\\pause"));
			levelUp = loadImages(new File("picture\\process\\levelUp"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//定义获取图片集合的方法（便于反复调用）
	public static ArrayList<BufferedImage> loadImages(File file) throws IOException
	{
		ArrayList<BufferedImage> buffer = new ArrayList<BufferedImage>();
		File[] files = file.listFiles();
		for(int i = 0;i<files.length;i++)
		{
			if(files[i].isFile()&&files[i].getName().contains(".png"))
			{
				buffer.add(ImageIO.read(files[i]));
			}
		}
		return buffer;
	}
}
