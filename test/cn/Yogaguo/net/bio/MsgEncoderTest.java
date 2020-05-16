package cn.Yogaguo.net.bio;

import cn.Yogaguo.net.MsgEncoder;
import cn.Yogaguo.net.TankMsg;
import cn.Yogaguo.tank.Direct;
import cn.Yogaguo.tank.Group;
import cn.Yogaguo.tank.Player;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MsgEncoderTest {
    @Test
    void encoder(){
        EmbeddedChannel ch = new EmbeddedChannel();
        ch.pipeline().addLast(new MsgEncoder());
        Player p = new Player(50,100, Direct.D, Group.BAD);
        TankMsg tm = new TankMsg(p);
        ch.writeOutbound(tm);
        ByteBuf buf=ch.readOutbound();

        int length = buf.readInt();
        int x= buf.readInt();
        int y = buf.readInt();
        Direct dir = Direct.values()[buf.readInt()];
        boolean moving = buf.readBoolean();
        Group group = Group.values()[buf.readInt()];
        UUID id = new UUID(buf.readLong(),buf.readLong());

        assertEquals(33,length);
        assertEquals(50 ,x);
        assertEquals(100 ,y);
        assertEquals(Direct.D ,dir);
        assertFalse(moving);
        assertEquals(Group.BAD,group);
        assertEquals(p.getId(),id);
    }
}
