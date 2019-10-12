package cn.Yogaguo.tank;

import java.awt.*;
@SuppressWarnings("all")
public class Bullet extends AbstractObjejct {
    private int x, y;
    private Direct dir;
    public static final int SPEED = 6;
    private Group group;
    private boolean live = true;
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isLive() {
        return live;
    }
    public void setLive(boolean live) {
        this.live = live;
    }
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
                x -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case D:
                y += SPEED;
                break;
            default:
        }
        boundsCheck();
    }
    public   void collideWithTank(Tank tank){
        if(!this.isLive()||!tank.isLive()){
            return;
        }
        if(this.group == tank.getGroup()){
            return;
        }
        Rectangle rec1 = new Rectangle(x,y,ResourceMgr.bulletU.getWidth(),
                ResourceMgr.bulletU.getHeight());

        Rectangle rec2 = new Rectangle(tank.getX(),tank.getY(),
                ResourceMgr.goodTankU.getWidth(),ResourceMgr.goodTankU.getHeight());

        if(rec1.intersects(rec2)){
          this.die();
          tank.die();
        }
    }

    private void die() {
        this.setLive(false);
    }

    private void boundsCheck() {
        if (x < 0 || y < 30 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
              live = false;
        }
    }

}
