package cn.Yogaguo.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class Server {
    public static void main(String[] args) throws  Exception {
        //阻塞式IO为单向通道。而Channel为双向，同时进行读写，非阻塞的都是Channel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost",8888));
        //配置为非阻塞
        ssc.configureBlocking(false);
       //大管家，负责轮询，专业叫法：选择器
       Selector selector =  Selector.open();
       //注册特定的事件。告诉大管家，在有一个客户端要连接时的这个事件发生时，处理该事件
       ssc.register(selector, SelectionKey.OP_ACCEPT);
       //轮训一边，是否有件事发生，假如没事件发生，阻塞
       selector.select();
       handle();
       
    }

    private static void handle() {
    }
}
