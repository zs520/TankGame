package cn.Yogaguo.tank.chainofresponsibility;

import cn.Yogaguo.tank.AbstractObjejct;
import cn.Yogaguo.tank.Bullet;
import cn.Yogaguo.tank.Tank;

public class BulletTankCollider implements Collider {

    @Override
    public boolean collide(AbstractObjejct o1, AbstractObjejct o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;

            if (!t.isLive() || !b.isLive()) {
                return false;
            }
            if (b.getGroup() == t.getGroup()) {
                return true;
            }
            if (b.getRect().intersects(t.getRect())) {
                b.die();
                t.die();
                return false;
            }

        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
             return  collide(o2, o1);
        }
        return true;
    }

}
