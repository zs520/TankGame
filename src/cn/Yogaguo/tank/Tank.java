package cn.Yogaguo.tank;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Tank {
	 private int x, y;
	 private Direct dir ;
	 public static final int SPEED = 5;
	 private boolean bL,bR,bU,bD; 
	 public Tank(int x,int y,Direct dir) {
          this.x = x;
          this.y = y;
          this.dir = dir;
	 }
	 
	public void paint(Graphics g) {
		 g.fillRect(x, y, 50, 50);
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
		}
		setMainDir();
	}

	private void setMainDir() {
		 if(!bL && !bR && !bU && !bD) {
			 dir = Direct.STOP;
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
		}
	}

}
