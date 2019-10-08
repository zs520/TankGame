package cn.Yogaguo.tank;

import java.awt.Frame;
import java.util.concurrent.TimeUnit;

public class Main {
            public static void main(String[] args) {

				 for(;;) {
					 try {
						 Thread.sleep(25);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					 TankFrame.INSTANCE.repaint();
				 }
			}
}
