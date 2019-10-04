package cn.Yogaguo.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
@SuppressWarnings("all")
   public class TankFrame extends Frame {
	   private  Tank myTank = null ;
	   private  Tank enemy = null;
	   private Bullet bullet = null;
	   public static final int GAME_WIDTH = 800;
	   public static final int GAME_HEIGHT = 600;
      public TankFrame() {
    	     this.setTitle("tank war");
             this.setLocation(50, 50);	  
             this.setSize(GAME_WIDTH,GAME_HEIGHT);
             this.setVisible(true);
             this.addKeyListener(new TankKeyListener());
             myTank = new Tank(100,100,Direct.R,Group.GOOD,this);
             enemy = new Tank(200,200,Direct.D,Group.BAD,this);
             bullet = new Bullet(100,100,Direct.D,Group.BAD);
             addWindowListener(new WindowAdapter() {
     			@Override
     			public void windowClosing(WindowEvent e) {
     				 System.exit(0);
     			}
     			 
     		});
     }
     public void add(Bullet bullet){
      	this.bullet = bullet;
	 }
      @Override
    public void paint(Graphics g) {
    	 myTank.paint(g);
    	 enemy.paint(g);
    	 bullet.paint(g);
    	
    }
	   Image offScreenImage = null;
	   @Override
	   public void update(Graphics g) {
		   if(offScreenImage == null) {
			   offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		   }
		   Graphics gOffScreen = offScreenImage.getGraphics();
		   Color c = gOffScreen.getColor();
		   gOffScreen.setColor(Color.BLACK);
		   gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		   gOffScreen.setColor(c);
		   paint(gOffScreen);
		   g.drawImage(offScreenImage, 0, 0, null);
	   }
  private class TankKeyListener extends KeyAdapter{
	 
	@Override
	public void keyPressed(KeyEvent e) {
		myTank.keyPressed(e);
		 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		 myTank.KeyReleased(e);
	}
	  
  }
   }
