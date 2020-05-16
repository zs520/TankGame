package cn.Yogaguo.net;

import cn.Yogaguo.tank.Direct;
import cn.Yogaguo.tank.Group;
import cn.Yogaguo.tank.Player;

import java.io.*;
import java.util.UUID;

public class TankMsg {
    private int x;
    private int y;
    private Group group;
    private UUID id;
    private boolean moving;
    private Direct dir;

    @Override
    public String toString() {
        return "TankMsg{" +
                "x=" + x +
                ", y=" + y +
                ", group=" + group +
                ", id=" + id +
                ", moving=" + moving +
                ", dir=" + dir +
                '}';
    }

    public TankMsg(){

    }
    public TankMsg(Player p){
        this.x =p.getX();
        this.y = p.getY();
        this.group=p.getGroup();
        this.moving=p.isMoving();
        this.dir=p.getDir();
        this.id=p.getId();
    }
    public byte[] toBytes(){
        //往一个字节数组插上输出流
        ByteArrayOutputStream baos = null;
        //往外写数据
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeBoolean(moving);
            dos.writeInt(group.ordinal());
            //UUID是两个 long 类型 ，所以先把高位写出去，在写低位
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.flush();
            bytes = baos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                if(baos != null){
                    try {
                        baos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            if(dos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

          return bytes;
    }

    public void prase(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Direct.values()[dis.readInt()];
            this.moving = dis.readBoolean();
            this.group = Group.values()[dis.readInt()];
            this.id = new UUID(dis.readLong(),dis.readLong());

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                dis.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void handle() {

    }
}
