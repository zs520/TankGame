package cn.Yogaguo.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

   public class TankFrame extends Frame {
	   Tank myTank ;
      public TankFrame() {
    	     this.setTitle("tank war");
             this.setLocation(50, 50);	  
             this.setSize(800,600);
             this.setVisible(true);
             this.addKeyListener(new TankKeyListener());
             myTank = new Tank(100,100,Direct.R);
             addWindowListener(new WindowAdapter() {
     			@Override
     			public void windowClosing(WindowEvent e) {
     				 System.exit(0);
     			}
     			 
     		});
     }
      @Override
    public void paint(Graphics g) {
    	 myTank.paint(g);
    	
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
