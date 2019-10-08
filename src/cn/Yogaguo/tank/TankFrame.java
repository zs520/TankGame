package cn.Yogaguo.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
   public class TankFrame extends Frame {

	public static final TankFrame INSTANCE = new TankFrame();

	   private  Tank myTank = null ;
	   private  Tank enemy = null;
	   private List<Bullet> bullets;
	   public static final int GAME_WIDTH = 800;
	   public static final int GAME_HEIGHT = 600;
       private TankFrame() {
    	     this.setTitle("tank war");
             this.setLocation(50, 50);	  
             this.setSize(GAME_WIDTH,GAME_HEIGHT);
             this.setVisible(true);
             this.addKeyListener(new TankKeyListener());
             myTank = new Tank(100,100,Direct.R,Group.GOOD);
             enemy = new Tank(200,200,Direct.D,Group.BAD);
             bullets = new ArrayList<>();
             addWindowListener(new WindowAdapter() {
     			@Override
     			public void windowClosing(WindowEvent e) {
     				 System.exit(0);
     			}
     			 
     		});
     }
     public void add(Bullet bullet){
      	 this.bullets.add(bullet);
	 }
      @Override
    public void paint(Graphics g) {
       	Color c = g.getColor();
       	g.setColor(Color.white);
       	g.drawString("bullets:"+bullets.size(),10,50);
       	g.setColor(c);
    	 myTank.paint(g);
    	 enemy.paint(g);
    	  for(int i = 0;i <bullets.size();i++){
    	  	bullets.get(i).collideWithTank(enemy);
    	  	if(!bullets.get(i).isLive()){
    	  		bullets.remove(i);
			}else {
				bullets.get(i).paint(g);
			}
		  }
    	
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
