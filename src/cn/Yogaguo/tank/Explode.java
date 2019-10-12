package cn.Yogaguo.tank;

import java.awt.*;

@SuppressWarnings("all")
public class Explode extends AbstractObjejct {
    private int x, y;
    private int height;
    private int width;
    private boolean live = true;
    private int step = 0;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        if (!live){
            return;
        }
            g.drawImage(ResourceMgr.explodes[step], x, y, null);
        step++;
        if (step >= ResourceMgr.explodes.length) {
            this.die();
        }
    }

    private void die() {
        this.live = false;
    }

}
