package cn.Yogaguo.tank.chainofresponsibility;

import cn.Yogaguo.tank.AbstractObjejct;

import java.io.Serializable;

@SuppressWarnings("all")
public interface Collider extends Serializable {
    // return true:chain go on.return false,chain break
    public boolean collide(AbstractObjejct o1,AbstractObjejct o2);
}
