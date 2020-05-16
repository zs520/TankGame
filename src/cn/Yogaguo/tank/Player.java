package cn.Yogaguo.tank;

import cn.Yogaguo.tank.Strategy.DefalutFireStrategy;
import cn.Yogaguo.tank.Strategy.FireStrategy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.UUID;

@SuppressWarnings("all")
public class Player extends AbstractObjejct {
	 private int x, y;
	 private Direct dir ;
	 public static final int SPEED = 5;
	 private boolean bL,bR,bU,bD;
	 private boolean moving;
	 private boolean live = true;
	 private Group group;
     private Bullet bullet;
     private UUID id = UUID.randomUUID();
	 public Player(int x, int y, Direct dir, Group group) {
          this.x = x;
          this.y = y;
          this.dir = dir;
          this.group = group;
	 }

	public boolean isMoving() {
		return moving;
	}

	public UUID getId() {
		return id;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Direct getDir() {
		return dir;
	}

	public void setDir(Direct dir) {
		this.dir = dir;
	}
    @Override
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
    @Override
	public void paint(Graphics g) {
	 	if(!this.isLive()){
	 		return;
		}
          Color c = g.getColor();
	 	     g.setColor(Color.yellow);
	 	     g.drawString(id.toString(),x,y-10);
	 	     g.setColor(c);
             switch (dir) {
                 case L:
                     g.drawImage(ResourceMgr.goodTankL, x, y, null);
                     break;
                 case R:
                     g.drawImage(ResourceMgr.goodTankR, x, y, null);
                     break;
                 case U:
                     g.drawImage(ResourceMgr.goodTankU, x, y, null);
                     break;
                 case D:
                     g.drawImage(ResourceMgr.goodTankD, x, y, null);
                     break;
                 default:
             }
		  move();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			  bL = true;
			break;
		case KeyEvent.VK_UP:
			  bU = true;
			break;
		case KeyEvent.VK_RIGHT:
			  bR = true;
			break;
		case KeyEvent.VK_DOWN:
			 bD = true;
			break;
			default:
		}
		setMainDir();
	}

	public void KeyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		    case KeyEvent.VK_LEFT:
			  bL = false;
			break;
		    case KeyEvent.VK_UP:
			 bU = false;
			break;
		    case KeyEvent.VK_RIGHT:
			  bR = false;
			break;
		    case KeyEvent.VK_DOWN:
			  bD = false;
			break;
			case KeyEvent.VK_CONTROL:
              fire();
              break;
		default:
		}
		setMainDir();
	}

    private void fire() {
	 	String classname = PropertyMgr.get("tankFireStrategy");
		FireStrategy strategy = null;
		try {
			Class clazz = Class.forName("cn.Yogaguo.tank.Strategy."+classname);
			strategy =(FireStrategy) clazz.getDeclaredConstructor().newInstance();
			strategy.fire(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    private void setMainDir() {
	 	// all dir keys are raleased,tank should be stop
		 if(!bL && !bR && !bU && !bD) {
			 moving = false;
		 }
		 //any dir key is pressed,tank should be moving
		else {
			 moving = true;
		 }
		 if(bL && !bR && !bU && !bD) {
			 dir = Direct.L;
		 }
		 if(!bL && bR && !bU && !bD) {
			 dir = Direct.R;
		 }
		 if(!bL && !bR && bU && !bD) {
			 dir = Direct.U;
		 }
		 if(!bL && !bR && !bU && bD) {
			 dir = Direct.D;
		 }
	}
        private void move() {
                if(!moving){
                    return;
                }
                switch (dir) {
                    case L:
                        x-=SPEED;
                        break;
                    case R:
                        x+=SPEED;
                        break;
                    case U:
                        y-=SPEED;
                        break;
                    case D:
                        y+=SPEED;
                        break;
                    default:
                }
	 }


	public void die() {
	 	this.setLive(false);
	}
}
