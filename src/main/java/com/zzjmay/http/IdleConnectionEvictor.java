package com.zzjmay.http;

import org.apache.http.conn.HttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zzjmay on 2019/2/28.
 */
@Component
public class IdleConnectionEvictor extends Thread {

    @Autowired
    private HttpClientConnectionManager httpClientConnectionManager;

    private volatile boolean shutdown;

    public IdleConnectionEvictor(){
        super();
        super.start();
    }

    @Override
    public void run(){
        try{

            while (!shutdown){
                synchronized (this){
                    //进入等待池中，5s后执行一直处理失效连接
                    wait(5000);
                    //如果被唤醒，则关闭失效的连接
                    httpClientConnectionManager.closeExpiredConnections();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 该方法的作用是手动调用关闭线程的时候，将定时
     */
    public void shutdown(){
        shutdown = true;

        synchronized (this){
            notifyAll();
        }
    }


}
