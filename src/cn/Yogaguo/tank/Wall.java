package cn.Yogaguo.tank;

import java.awt.*;

public class Wall extends AbstractObjejct {
    private int x,y,Width,Height;
    private Rectangle rect = null;
    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        Width = width;
        Height = height;
        rect = new Rectangle(x,y,width,height);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
         g.setColor(Color.GRAY);
         g.fillRect(x,y,Width,Height);
         g.setColor(c);
    }

    @Override
    public boolean isLive() {
        return true;
    }

    public Rectangle getRect() {
        return rect;
    }
}
