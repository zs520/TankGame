package cn.Yogaguo.callable;

import java.util.concurrent.FutureTask;
@SuppressWarnings("all")
public class FutureTaskTest {
    public static void main(String[] args)  throws Exception{
        FutureTask<Long> ft = new FutureTask(new MyTask());
        new Thread(ft).start();
        Long l = ft.get();
        System.out.println(l);
    }
}
