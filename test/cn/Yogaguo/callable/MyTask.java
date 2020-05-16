package cn.Yogaguo.callable;

import java.util.concurrent.Callable;

public class MyTask implements Callable<Long> {
    @Override
    public Long call() throws Exception {
         long r = 0L;
         for(long i = 0L;i<10L;i++){
             r+=i;
             Thread.sleep(500);
             System.out.println(i+" add");
         }
         return r;
    }

}
