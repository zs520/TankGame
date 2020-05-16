package cn.Yogaguo.net;

import cn.Yogaguo.tank.TankFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Cilent {
       public static final Cilent INSTANCE = new Cilent();
        public void connect(){
            EventLoopGroup workGroup = new NioEventLoopGroup(1);
            Bootstrap b = new Bootstrap();
            b.group(workGroup);
            try {

                b.channel(NioSocketChannel.class);
                b.handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new MsgEncoder())
                                .addLast(new MsgDecoder())
                                .addLast(new MyHandler());
                    }
                });
                ChannelFuture future = b.connect("localhost", 8888).sync();
                System.out.println("go on");
                future.channel().closeFuture().sync();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                workGroup.shutdownGracefully();
            }
        }
}
class MyHandler extends  SimpleChannelInboundHandler<TankMsg>{
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
         ctx.writeAndFlush(new TankMsg(TankFrame.INSTANCE.getMode().getMyTank()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TankMsg msg) throws Exception {
        System.out.println(msg);
        msg.handle();
    }
}