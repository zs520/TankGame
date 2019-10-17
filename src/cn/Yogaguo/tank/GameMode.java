package cn.Yogaguo.tank;

import cn.Yogaguo.tank.chainofresponsibility.ColliderChain;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("all")
public class GameMode implements Serializable {
    private  Player myTank = null ;
    private List<AbstractObjejct> objejcts;
    private ColliderChain chain ;
    public GameMode(){
       initGameObjects();
    }
    private void initGameObjects() {
        chain = new ColliderChain();
        myTank = new Player(100, 100, Direct.R, Group.GOOD);
        objejcts = new ArrayList<>();
        int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            objejcts.add(new Tank(100 + 50 * i, 200, Direct.D, Group.BAD));
        }
        objejcts.add(new Wall(200, 300, 100, 50));
    }
    public void paint(Graphics g) {
        myTank.paint(g);
        for (int i = 0; i < objejcts.size(); i++) {
            AbstractObjejct objejct = objejcts.get(i);
            if (!objejcts.get(i).isLive()) {
                objejcts.remove(objejct);
                break;
            }
        }
        for (int i = 0; i < objejcts.size(); i++) {

            AbstractObjejct o1 = objejcts.get(i);
            for (int j = 0; j < objejcts.size(); j++) {
                AbstractObjejct o2 = objejcts.get(j);
                chain.collide(o1, o2);
            }
            if (objejcts.get(i).isLive()) {
                objejcts.get(i).paint(g);
            }
        }
    }

    public void add(AbstractObjejct obj) {
        objejcts.add(obj);
    }
    public Player getMyTank(){
        return myTank;
    }
}
