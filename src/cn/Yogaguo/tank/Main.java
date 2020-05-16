package cn.Yogaguo.tank;

import cn.Yogaguo.net.Cilent;

@SuppressWarnings("all")
public class Main {
            public static void main(String[] args) {
             new Thread(()->{
				 for(;;) {
					 try {
						 Thread.sleep(25);
					 } catch (InterruptedException e) {
						 e.printStackTrace();
					 }
					 TankFrame.INSTANCE.repaint();
				 }
			 }).start();
				Cilent.INSTANCE.connect();
			}
}
