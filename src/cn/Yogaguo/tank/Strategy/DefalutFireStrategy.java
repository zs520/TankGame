package cn.Yogaguo.tank.Strategy;

import cn.Yogaguo.tank.Bullet;
import cn.Yogaguo.tank.Player;
import cn.Yogaguo.tank.ResourceMgr;
import cn.Yogaguo.tank.TankFrame;

public class DefalutFireStrategy implements FireStrategy {
    @Override
    public void fire(Player p) {
        int bx = p.getX() + ResourceMgr.goodTankU.getWidth()/2 - ResourceMgr.bulletU.getWidth();
        int by = p.getY() + ResourceMgr.goodTankU.getHeight()/2 - ResourceMgr.bulletU.getHeight();
        TankFrame.INSTANCE.getMode().add(new Bullet(bx+7,by-5,p.getDir(),p.getGroup()));
    }
}
