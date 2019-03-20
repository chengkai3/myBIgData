package ActionTotal;
/**
 * 碰撞类:定义了静态的飞行物碰撞方法
 * @author anwu
 *
 */
import Main.MainGame;
import Object.Buff;
import Object.Enemy;
import Object.FlyingObject;
import Object.UniqueSkillA;
import Object.UniqueSkillB;
import basic.AwardType;
public class Crash 
{
	public static void crash(MainGame game)
	{
		for(FlyingObject b:game.bullets)
		{
			//敌机与必杀技的碰撞
			if(b instanceof UniqueSkillA||b instanceof UniqueSkillB)
			{
				for(FlyingObject f:game.flyingObjects)
				{
					f.crash(b);
				}
			}
			for(FlyingObject f :game.flyingObjects)
			{
				//子弹与敌机的碰撞
				if(f instanceof Enemy)
				{
					if(b.crash(f)&&game.heroPower<MainGame.HEROPOWERMAX)
					{
						game.heroPower++;
					}
				}
			}
		}
		for(FlyingObject f :game.flyingObjects)
		{
			//buff与英雄机的碰撞
			if(f instanceof Buff)
			{
				f.crash(game.hero);
			}
			//敌机与英雄机的碰撞
			if(f instanceof Enemy)
			{
				if(f.crash(game.hero)&&game.hero.getImages()!=Load.heroPlaneDefender)
					//碰撞完后子弹类型重置为普通子弹
					game.awdType = AwardType.NONE;
			}
		}
		//敌方子弹与英雄机的碰撞
		for(FlyingObject f : game.enemyBullets)
		{
			if(f.crash(game.hero)&&game.hero.getHitted() == 0)
			{
				game.awdType = AwardType.NONE;
			}
		}
	}
}
