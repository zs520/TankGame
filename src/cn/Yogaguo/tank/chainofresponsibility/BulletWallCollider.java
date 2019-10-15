package cn.Yogaguo.tank.chainofresponsibility;

import cn.Yogaguo.tank.AbstractObjejct;
import cn.Yogaguo.tank.Bullet;
import cn.Yogaguo.tank.Wall;
@SuppressWarnings("all")
public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(AbstractObjejct o1, AbstractObjejct o2) {
        if(o1 instanceof Bullet && o2 instanceof Wall){
            Bullet b = (Bullet)o1;
            Wall w = (Wall)o2;
            if(b.isLive()){
                if(b.getRect().intersects(w.getRect())){
                    b.die();
                    return false;
                }
            }
        } else if(o1 instanceof Wall && o2 instanceof Bullet){
                return collide(o2,o1);
        }
        return true;
    }

}
