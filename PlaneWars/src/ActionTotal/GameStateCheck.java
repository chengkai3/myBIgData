package ActionTotal;
/**
 * 游戏结束检查类：定义了检查游戏是否结束的静态方法。
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
		//如果英雄机生命小于等于0，游戏状态置为GAME_OVER，分数置零
		if(game.hero.getLife()<=0)
		{
			AudioPlayer.player.stop(game.ap);
			game.gState = GameState.GAME_OVER;
			game.score = 0;
			return true;
		}
		return false;
	}
	//如果boss机已经出现过，且已被销毁，则通关
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
