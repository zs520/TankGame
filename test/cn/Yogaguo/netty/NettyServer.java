package cn.Yogaguo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
@SuppressWarnings("all")
public class NettyServer {
    public static void main(String[] args) throws Exception {
        //负责接客：不断的处理时间的线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        //负责服务
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);
        //启动Server的辅助类
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup,workerGroup);
        //异步的全双工:指定通什么通道
        b.channel(NioServerSocketChannel.class);
        //netty 帮我们处理的 accept 的过程，每接受一个客户端，都会调用ChannelInitializer里面写的内容
        //ChannelInitializer 相当于一个 Callback 这种设计模式成为：Listener 或者 Observer
        b.childHandler(new MyChildInitialLizer());
       ChannelFuture future =  b.bind(8888).sync();
         future.channel().closeFuture().sync();
         bossGroup.shutdownGracefully();
         workerGroup.shutdownGracefully();

    }

}
 class  MyChildInitialLizer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
         socketChannel.pipeline().addLast(new MyChildHandler());
    }
}
class MyChildHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(buf.toString());
         ctx.writeAndFlush(msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}