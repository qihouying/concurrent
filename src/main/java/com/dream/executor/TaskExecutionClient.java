package com.dream.executor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by dream on 17/05/2017.
 */
public class TaskExecutionClient {
    private static final int NTHREADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws IOException{
        int count = 0;
        Socket socket = new Socket("127.0.0.1",8080);
        while (true) {
            OutputStream os;

            try{
                os=socket.getOutputStream();
                os.write("hello".getBytes());

            }catch(IOException e){

                e.printStackTrace();
            }

        }
    }

}
