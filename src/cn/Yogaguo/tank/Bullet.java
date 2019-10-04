package cn.Yogaguo.tank;

import java.awt.*;

public class Bullet {
    private int x, y;
    private Direct dir;
    public static final int SPEED = 6;
    private Group group;

    public Bullet(int x, int y, Direct dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }

    public void paint(Graphics g) {

        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            default:
        }
        move();
    }

    private void move() {
        switch (dir) {
            case L:
                x-=SPEED;
                break;
            case R:
                x+=SPEED;
                break;
            case U:
                y-=SPEED;
                break;
            case D:
                y+=SPEED;
                break;
            default:
        }
    }
}
