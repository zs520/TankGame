package cn.Yogaguo.tank;

import cn.Yogaguo.tank.chainofresponsibility.BulletTankCollider;
import cn.Yogaguo.tank.chainofresponsibility.BulletWallCollider;
import cn.Yogaguo.tank.chainofresponsibility.Collider;
import cn.Yogaguo.tank.chainofresponsibility.ColliderChain;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("all")
public class TankFrame extends Frame {

    public static final TankFrame INSTANCE = new TankFrame();
    GameMode mode = new GameMode();
	   public static final int GAME_WIDTH = 800;
	   public static final int GAME_HEIGHT = 600;
       private TankFrame() {
    	     this.setTitle("tank war");
             this.setLocation(50, 50);
             this.setSize(GAME_WIDTH,GAME_HEIGHT);
             this.setVisible(true);
             this.addKeyListener(new TankKeyListener());
             addWindowListener(new WindowAdapter() {

     			@Override
     			public void windowClosing(WindowEvent e) {
     				 System.exit(0);
     			}

     		});

     }


    @Override
    public void paint(Graphics g) {
         mode.paint(g);
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
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


    private class TankKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            mode.getMyTank().keyPressed(e);

        }

        @Override
        public void keyReleased(KeyEvent e) {
            mode.getMyTank().KeyReleased(e);
        }

    }
    public GameMode getMode(){
        return mode;
    }
}
