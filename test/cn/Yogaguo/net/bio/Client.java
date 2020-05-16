package cn.Yogaguo.net.bio;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main  (String[] args) throws Exception {

        Socket s = new Socket("localhost", 8888);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        bw.write("Yogaguo");
        bw.newLine();
        bw.flush();
        BufferedReader  reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str = reader.readLine();
        System.out.println(str);
        reader.close();
        bw.close();
    }

}
