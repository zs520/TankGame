package cn.Yogaguo.tank.chainofresponsibility;

import cn.Yogaguo.tank.AbstractObjejct;
import cn.Yogaguo.tank.Tank;
import cn.Yogaguo.tank.Wall;

public class TankWallCollider implements Collider {
    @Override
    public boolean collide(AbstractObjejct o1, AbstractObjejct o2) {
        if(o1 instanceof Tank && o2 instanceof Wall){
            Tank t = (Tank)o1;
            Wall w =(Wall)o2;
            if(t.isLive()){
                if(t.getRect().intersects(w.getRect())){
                    t.back();
                }
            }
            else if(o1 instanceof Wall && o2 instanceof Tank){
                collide(o2,o1);
            }
        }
        return true;
    }
}
