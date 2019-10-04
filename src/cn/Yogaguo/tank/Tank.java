package cn.Yogaguo.tank;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
@SuppressWarnings("all")
public class Tank {
	 private int x, y;
	 private Direct dir ;
	 public static final int SPEED = 5;
	 private boolean bL,bR,bU,bD;
	 private boolean moving;
	 private Group group;
	 //持有对方的引用
	 TankFrame tf;
	 public Tank(int x,int y,Direct dir,Group group,TankFrame tf) {
          this.x = x;
          this.y = y;
          this.dir = dir;
          this.group = group;
          this.tf = tf;
	 }
	 
	public void paint(Graphics g) {
	     if(this.group == Group.GOOD) {
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
         }
         if(this.group == Group.BAD){
             switch (dir) {
                 case L:
                     g.drawImage(ResourceMgr.badTankL, x, y, null);
                     break;
                 case R:
                     g.drawImage(ResourceMgr.badTankR, x, y, null);
                     break;
                 case U:
                     g.drawImage(ResourceMgr.badTankU, x, y, null);
                     break;
                 case D:
                     g.drawImage(ResourceMgr.badTankD, x, y, null);
                     break;
                 default:
             }
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
	     tf.add(new Bullet(x,y,this.dir,this.group));
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


}
