package cn.Yogaguo.tank.Strategy;

import cn.Yogaguo.tank.*;

public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Player p) {
        int bx = p.getX() + ResourceMgr.goodTankU.getWidth()/2 - ResourceMgr.bulletU.getWidth();
        int by = p.getY() + ResourceMgr.goodTankU.getHeight()/2 - ResourceMgr.bulletU.getHeight();
        Direct[] dir = Direct.values();
        for(Direct d : dir) {
            TankFrame.INSTANCE.add(new Bullet(bx + 7, by - 5, d, p.getGroup()));
        }
    }
}
