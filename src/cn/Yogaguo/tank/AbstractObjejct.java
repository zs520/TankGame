package cn.Yogaguo.tank;

import java.awt.*;
import java.io.Serializable;
public  abstract class AbstractObjejct implements Serializable {
    public abstract void paint(Graphics g);
    public abstract boolean isLive();
}
