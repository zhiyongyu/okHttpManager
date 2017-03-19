package hl.jaron.httpurlconnectioncustom.test;

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jaron Yu on 2017/3/19.
 */
public class ThreadPoolTest {

    public void creatThreadPool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.DiscardOldestPolicy());
        threadPoolExecutor.execute(new ThreadTask());//往线程池放入一个任务。

    }

    class ThreadTask implements Runnable, Serializable {

        public ThreadTask() {

        }

        @Override
        public void run() {
            //执行任务
        }
    }


}
