package cn.Yogaguo.tank.chainofresponsibility;

import cn.Yogaguo.tank.AbstractObjejct;
import cn.Yogaguo.tank.PropertyMgr;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("all")
public class ColliderChain implements Collider {
   private List<Collider> collidersList = new ArrayList<>();
   public ColliderChain(){
       initColider();
   }
    private void initColider() {
        String[] ColliderName = PropertyMgr.get("Collider").split(",");
        try {
            for (String name : ColliderName) {
                Class clazz = Class.forName("cn.Yogaguo.tank.chainofresponsibility." + name);
                Collider c= (Collider)clazz.getDeclaredConstructor().newInstance();
                collidersList.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean collide(AbstractObjejct o1,AbstractObjejct o2){
        for(Collider c :collidersList){
            if(!c.collide(o1,o2)){
                return false;
            }
        }
        return true;
    }
}
