package ActionTotal;
/**
 * 背景移动类：定义了静态的背景移动方法backMove
 * @author anwu
 *
 */
import Main.MainGame;
public class BackMove 
{
	public static int num = 0;
	public static void backMove(MainGame game)
	{
		//如果上图的y坐标和下图的y坐标和为背景图的高度（初始状态时和为高度的负数），就将两个y坐标置为初始状态
		if((game.backPointY[1]+game.backPointY[0]) ==game.backGround.getHeight())
		{
			game.backPointY[0] = 0;
			game.backPointY[1] = -game.backGround.getHeight();
		}
		if(num++%game.backGroundFre == 0)
		{
			//使两张图片的y坐标不断下移
			game.backPointY[0] ++;
			game.backPointY[1] ++;
		}
	}
}
