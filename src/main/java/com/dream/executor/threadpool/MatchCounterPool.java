package com.dream.executor.threadpool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Created by dream on 29/05/2017.
 */
public class MatchCounterPool implements Callable<Integer> {
    private File directory;
    private String keyword;
    private ExecutorService pool;
    private int count;

    public MatchCounterPool(File directory, String keyword, ExecutorService pool) {
        this.directory = directory;
        this.keyword = keyword;
        this.pool = pool;
    }

    public Integer call() throws Exception {
        count = 0;
        File[] files = directory.listFiles();
        List<Future<Integer>> results = new ArrayList<Future<Integer>>();

        for (File file : files) {
            if (file.isDirectory()) {
                MatchCounterPool counter = new MatchCounterPool(file, keyword, pool);
                Future<Integer> result = pool.submit(counter);
                results.add(result);
            } else {
               if (search(file)) {
                   count++;
               }
            }
        }
        for (Future<Integer> result : results) {
            try {
                count += result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public boolean search(File file) {
        try {
            Scanner in = new Scanner(file);
            boolean found = false;
            while (!found && in.hasNextLine()) {
                String line = in.nextLine();
                if (line.contains(keyword)) {
                    found = true;
                }
                return found;
            }

        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
