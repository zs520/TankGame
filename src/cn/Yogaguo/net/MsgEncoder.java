package cn.Yogaguo.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgEncoder extends MessageToByteEncoder<TankMsg> {


    @Override
    protected void encode(ChannelHandlerContext  ctx, TankMsg msg, ByteBuf buf) throws Exception {
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }
}
