package cn.Yogaguo.tank;

import java.awt.*;
@SuppressWarnings("all")
public class Bullet extends AbstractObjejct {
    private int x, y;
    private Direct dir;
    public static final int SPEED = 6;
    private Group group;
    private boolean live = true;
    private Rectangle rect = null;
    private  int Width = ResourceMgr.bulletU.getWidth();
    private int Height = ResourceMgr.bulletU.getHeight();

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

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
    @Override
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
        rect = new Rectangle(x,y,Width,Height);
    }
    @Override
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
        //update the rect
        rect.x = x;
        rect.y = y;
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

    public Rectangle getRect(){
        return rect;
    }

    public void die() {
        this.setLive(false);
    }

    private void boundsCheck() {
        if (x < 0 || y < 30 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
              live = false;
        }
    }

}
