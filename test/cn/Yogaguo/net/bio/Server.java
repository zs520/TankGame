package cn.Yogaguo.net.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
@SuppressWarnings("all")
public class Server {
    public static void main(String[] args) throws Exception {

        ServerSocket s = new ServerSocket();
        s.bind(new InetSocketAddress("localhost",8888));
        boolean started = true;
        while (started) {
            //阻塞
            Socket ss = s.accept();
            new Thread(()->{
                try {
                BufferedReader br = new BufferedReader(new InputStreamReader(ss.getInputStream()));
                //阻塞
                String str = null;
                str = br.readLine();
                System.out.println(str);
                br.close();
                ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();
        }
        s.close();
    }
}
