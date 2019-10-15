package cn.Yogaguo.tank.chainofresponsibility;

import cn.Yogaguo.tank.AbstractObjejct;
@SuppressWarnings("all")
public interface Collider {
    // return true:chain go on.return false,chain break
    public boolean collide(AbstractObjejct o1,AbstractObjejct o2);
}
