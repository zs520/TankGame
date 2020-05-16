package cn.Yogaguo.callable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFuture  {
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Long> future = service.submit(new MyTask());
        //wait until there is a result -->Blocked
        long l = future.get();
        System.out.println(l);
        System.out.println("go on");

    }
}
