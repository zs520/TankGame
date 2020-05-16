package cn.Yogaguo.nettychat;

import cn.Yogaguo.netty.NettyClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

@SuppressWarnings("all")
public class Client {
    public static final Client INSTANCE = new Client();
    public Client(){

    }
    private Channel channel = null;
    public  void connect( )   {
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);
        Bootstrap b = new Bootstrap();
        try {

            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    channel = socketChannel;
                    socketChannel.pipeline()
                            .addLast(new MyHandler());
                }
            });
            //确认连接好 才可以继续往下执行，sync是阻塞的
            ChannelFuture future = b.connect("localhost", 8888).sync();
            //手动阻塞：等待关闭
            future.channel().closeFuture().sync();
            System.out.println("go on");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
        }
    }

    public void send(String text) {
       channel.writeAndFlush(Unpooled.copiedBuffer(text.getBytes()));
    }

    public void closeConnection() {
        send("bye!!");
        channel.close();
    }

    static class MyHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
             ByteBuf buf = (ByteBuf)msg;
             try {
                 byte[] bytes = new byte[buf.readableBytes()];
                 buf.getBytes(buf.readerIndex(),bytes);
                 String str = new String(bytes);
                 ClientFrame.INSTACE.updataText(str);
             }finally {
                 //释放自己所指向的内存
                 if(buf != null) {
                     ReferenceCountUtil.release(buf);
                 }
             }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }


    }
}
