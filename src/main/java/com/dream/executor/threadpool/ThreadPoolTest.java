package com.dream.executor.threadpool;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Created by dream on 29/05/2017.
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter base directory (e.g. /usr/local/Cellar): ");
        String directory = in.nextLine();
        System.out.println("Enter keyword (e.g. volatile): ");
        String keyword = in.nextLine();

        ExecutorService pool = Executors.newCachedThreadPool();

        MatchCounterPool counter = new MatchCounterPool(new File(directory), keyword, pool);
        Future<Integer> result = pool.submit(counter);


        try {
            System.out.println(result.get() + " matching files.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();

        int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
        System.out.println("Largest pool size=" + largestPoolSize);
    }
}
