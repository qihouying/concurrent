package com.dream.executor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by dream on 17/05/2017.
 */
public class TaskExecutionWebServer {
    private static final int NTHREADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws IOException{
        int count = 0;
        ServerSocket socket = new ServerSocket(8080);
        while (true) {
            final Socket connnection = socket.accept();
            count++;
            Runnable task = new Runnable(){
                public void run() {
                    System.out.println("execute");
                }
            };
            exec.execute(task);

        }
    }
}
