package Object;
/**
 * 飞行物父类
 */
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import ActionTotal.Load;
import ActionTotal.MusicPlay;
import basic.Model;
import basic.ObjectState;
import basic.TimerAct;
public abstract class FlyingObject
{
	protected Point point;
	protected ObjectState state;
	protected int life;
	protected int boomTime;
	protected int glintTime1;
	protected int speedX;
	protected int speedY;
	protected int imageNum;
	protected TimerAct timerAct;
	protected ArrayList<BufferedImage> images;
	private BufferedImage image;
	public FlyingObject(Point point, ArrayList<BufferedImage> images,int life,int speedX,int speedY)
	{
		super();
		this.point = point;
		this.images = images;
		this.image = images.get(0);
		this.state = ObjectState.ALIVE;
		this.speedX = speedX;
		this.speedY = speedY;
		this.timerAct = new TimerAct();
		this.life = life;
		this.glintTime1 = 0;
		this.boomTime = 3;
		imageNum = 0;
	}
	//飞行物碰撞方法
	public boolean crash(FlyingObject o)
	{
		if(state == ObjectState.ALIVE&&o.state == ObjectState.ALIVE&&Model.crash(this, o))
		{
			if((this instanceof HeroBulletA||this instanceof HeroBulletB||this instanceof HeroBulletC)&&o instanceof Enemy)
			{
				this.life--;
				o.life--;
			}
			if(o instanceof UniqueSkillA&&this instanceof Enemy)
			{
				this.life--;
			}
			if(o instanceof UniqueSkillB&&this instanceof Enemy)
			{
				this.life-=20;
			}
			if(this instanceof Enemy&&o instanceof HeroPlane)
			{
				this.life-=3;
				if(o.images != Load.heroPlaneDefender||this instanceof BossPlane)
				{
					o.life--;
				}
			}
			if((this instanceof EnemyBulletA||this instanceof EnemyBulletB||this instanceof EnemyBulletC)&&o instanceof HeroPlane)
			{
				this.life--;
				if(o.images != Load.heroPlaneDefender)
				{
					HeroPlane h = (HeroPlane)o;
					h.setHitted(h.getHitted()+1);
					if(h.getHitted()==3)
					{
						h.setHitted(0);
						o.life--;
					}
				}
				this.boom();
				o.boom();
			}
			if(this instanceof Buff&&o instanceof HeroPlane)
			{
				this.life--;
			}
			this.boom();
			o.boom();
			return true;
		}
		else
		{
			return false;
		}
	}
	//飞行物移动方法
	public abstract void move(Point p1,Point p2);
	//飞行物爆炸方法
	public void boom()
	{
		if(life<=0&&state == ObjectState.ALIVE)
		{
			state = ObjectState.BOOMING;
			if(this instanceof Enemy)
			{
				images = Load.bulletBoom;
			}
			if(this instanceof BossPlane)
			{
				images = Load.bossBoom;
			}
		}
	}
	//飞行物销毁方法
	public void destroy()
	{
		if(state == ObjectState.BOOMING&&boomTime--<0)
		{
			if(this instanceof EnemyPlaneA||this instanceof EnemyPlaneB||this instanceof EnemyPlaneC||this instanceof EnemyPlaneD||this instanceof HeroPlane||this instanceof BossPlane)
			{
				MusicPlay.music("music//boom.wav");
			}
			this.state = ObjectState.DESTROYED;
		}
	}
	//飞行物越界判断方法
	public boolean outOfBound(Point p1,Point p2)
	{
		if(point.y+image.getHeight()/2<p1.y||point.y-image.getHeight()/2>p2.y||point.x-image.getWidth()/2>p2.x||point.x+image.getWidth()/2<p1.x)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//飞行物闪烁方法
	public void glint()
	{
		if(glintTime1++%5==0)
		{
			image = images.get((++imageNum)%images.size());
		}
	}
	public BufferedImage getImage() 
	{
		return image;
	}
	public void setImage(BufferedImage image) 
	{
		this.image = image;
	}
	public Point getPoint()
	{
		return point;
	}
	public int getWidth()
	{
		return image.getWidth();
	}
	public int getHeight()
	{
		return image.getHeight();
	}
	public ObjectState getState() 
	{
		return state;
	}
	public void setState(ObjectState state) 
	{
		this.state = state;
	}
	public int getLife() 
	{
		return life;
	}
	public void setLife(int life) 
	{
		this.life = life;
	}
	public int getBoomTime() 
	{
		return boomTime;
	}
	public int getSpeedX() 
	{
		return speedX;
	}
	public int getSpeedY() 
	{
		return speedY;
	}
	public ArrayList<BufferedImage> getImages() 
	{
		return images;
	}
	public void setImages(ArrayList<BufferedImage> images) 
	{
		this.images = images;
	}
	public TimerAct getTimerAct() 
	{
		return timerAct;
	}
	public void setTimerAct(TimerAct timerAct) 
	{
		this.timerAct = timerAct;
	}
	

}
