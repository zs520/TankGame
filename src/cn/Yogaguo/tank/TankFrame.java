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

	   private  Player myTank = null ;
	   List<AbstractObjejct> objejcts;
	   ColliderChain chain = new ColliderChain();
	   public static final int GAME_WIDTH = 800;
	   public static final int GAME_HEIGHT = 600;
       private TankFrame() {
    	     this.setTitle("tank war");
             this.setLocation(50, 50);
             this.setSize(GAME_WIDTH,GAME_HEIGHT);
             this.setVisible(true);
             this.addKeyListener(new TankKeyListener());
             initGameObjects();
             addWindowListener(new WindowAdapter() {

     			@Override
     			public void windowClosing(WindowEvent e) {
     				 System.exit(0);
     			}

     		});

     }

    private void initGameObjects() {
        myTank = new Player(100, 100, Direct.R, Group.GOOD);
        objejcts = new ArrayList<>();
        int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            objejcts.add(new Tank(100 + 50 * i, 200, Direct.D, Group.BAD));
        }
        objejcts.add(new Wall(200, 300, 100, 50));
    }



    public void add(AbstractObjejct obj) {
        objejcts.add(obj);
    }

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
        for (int i = 0; i < objejcts.size(); i++) {
            if (!objejcts.get(i).isLive()) {
                objejcts.remove(i);
                break;
            }
            AbstractObjejct o1 = objejcts.get(i);
            for (int j = 0; j < objejcts.size(); j++) {
                AbstractObjejct o2 = objejcts.get(j);
                 chain.collide(o1,o2);
            }
            if (objejcts.get(i).isLive()) {
                objejcts.get(i).paint(g);
            }
        }
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
            myTank.keyPressed(e);

        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.KeyReleased(e);
        }

    }
}
