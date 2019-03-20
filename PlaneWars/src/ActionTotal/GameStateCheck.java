package ActionTotal;
/**
 * ��Ϸ��������ࣺ�����˼����Ϸ�Ƿ�����ľ�̬������
 */
import Main.MainGame;
import Object.BossPlane;
import Object.FlyingObject;
import basic.GameState;
import sun.audio.AudioPlayer;

public class GameStateCheck 
{
	public static boolean gameOverCheck(MainGame game)
	{
		//���Ӣ�ۻ�����С�ڵ���0����Ϸ״̬��ΪGAME_OVER����������
		if(game.hero.getLife()<=0)
		{
			AudioPlayer.player.stop(game.ap);
			game.gState = GameState.GAME_OVER;
			game.score = 0;
			return true;
		}
		return false;
	}
	//���boss���Ѿ����ֹ������ѱ����٣���ͨ��
	public static boolean levelUpCheck(MainGame game)
	{
		boolean bossAlive = false;
		if(game.bossTime == true)
		{
			for(FlyingObject f :game.flyingObjects)
			{
				if(f instanceof BossPlane)
				{
					bossAlive = true;
					break;
				}
			}
			if(bossAlive)
			{
				return false;
			}
			else
			{
				game.gState = GameState.LEVEL_UP;
				AudioPlayer.player.stop(game.ap);
				game.ap = MusicPlay.music("music//levelUp.wav");
				if(game.level !=3)
					game.level++;
				return true;
			}
		}
		else 
		{
			return false;
		}
	}
}
