package ActionTotal;
/**
 * �����ƶ��ࣺ�����˾�̬�ı����ƶ�����backMove
 * @author anwu
 *
 */
import Main.MainGame;
public class BackMove 
{
	public static int num = 0;
	public static void backMove(MainGame game)
	{
		//�����ͼ��y�������ͼ��y�����Ϊ����ͼ�ĸ߶ȣ���ʼ״̬ʱ��Ϊ�߶ȵĸ��������ͽ�����y������Ϊ��ʼ״̬
		if((game.backPointY[1]+game.backPointY[0]) ==game.backGround.getHeight())
		{
			game.backPointY[0] = 0;
			game.backPointY[1] = -game.backGround.getHeight();
		}
		if(num++%game.backGroundFre == 0)
		{
			//ʹ����ͼƬ��y���겻������
			game.backPointY[0] ++;
			game.backPointY[1] ++;
		}
	}
}
