package cn.Yogaguo.tank;

import java.util.Random;

@SuppressWarnings("all")
public enum Direct {
    L,U,R,D;
    private static Random r = new Random();
    public static Direct randomDir(){
        return values()[r.nextInt(Direct.values().length)];
    }
}
