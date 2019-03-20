package ActionTotal;
/**
 * ��ײ��:�����˾�̬�ķ�������ײ����
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
			//�л����ɱ������ײ
			if(b instanceof UniqueSkillA||b instanceof UniqueSkillB)
			{
				for(FlyingObject f:game.flyingObjects)
				{
					f.crash(b);
				}
			}
			for(FlyingObject f :game.flyingObjects)
			{
				//�ӵ���л�����ײ
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
			//buff��Ӣ�ۻ�����ײ
			if(f instanceof Buff)
			{
				f.crash(game.hero);
			}
			//�л���Ӣ�ۻ�����ײ
			if(f instanceof Enemy)
			{
				if(f.crash(game.hero)&&game.hero.getImages()!=Load.heroPlaneDefender)
					//��ײ����ӵ���������Ϊ��ͨ�ӵ�
					game.awdType = AwardType.NONE;
			}
		}
		//�з��ӵ���Ӣ�ۻ�����ײ
		for(FlyingObject f : game.enemyBullets)
		{
			if(f.crash(game.hero)&&game.hero.getHitted() == 0)
			{
				game.awdType = AwardType.NONE;
			}
		}
	}
}
