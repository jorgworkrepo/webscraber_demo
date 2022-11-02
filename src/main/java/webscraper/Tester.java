package webscraper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Tester {

    public static List<TagCounter> runSequential() {

        List<TagCounter> urls = new ArrayList<>();

        urls.add(new TagCounter("https://www.fck.dk"));
        urls.add(new TagCounter("https://www.google.com"));
        urls.add(new TagCounter("https://politiken.dk"));
        urls.add(new TagCounter("https://cphbusiness.dk"));

        for (TagCounter tc : urls) {
            tc.doWork();
        }
        return urls;
    }

    public static TagCounter getTagCounter(TagCounter tc) {
        tc.doWork();
        return tc;
    }

    public static List<TagCounter> runParallel() throws ExecutionException, InterruptedException {

        List<TagCounter> urls = new ArrayList<>();

        // TODO:
        urls.add(new TagCounter("https://www.fck.dk"));
        urls.add(new TagCounter("https://www.google.com"));
        urls.add(new TagCounter("https://politiken.dk"));
        urls.add(new TagCounter("https://cphbusiness.dk"));

//        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Future<TagCounter>> list = new ArrayList<>();

        for (TagCounter tc : urls) {
            Future<TagCounter> future = executorService.submit(() -> {
                tc.doWork();
                return tc;
            });

            list.add(future);
        }

        List<TagCounter> result = new ArrayList<>();
        for (Future<TagCounter> future : list) {
            result.add(future.get());
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        long timeSequential;
        long start = System.nanoTime();

        List<TagCounter> fetchedData = new Tester().runSequential();
        long end = System.nanoTime();
        timeSequential = end - start;
        System.out.println("Time Sequential: " + ((timeSequential) / 1_000_000) + " ms.");

        for (TagCounter tc : fetchedData) {
            System.out.println("Title: " + tc.getTitle());
            System.out.println("Div's: " + tc.getDivCount());
            System.out.println("Body's: " + tc.getBodyCount());
            System.out.println("----------------------------------");
        }

    }
}

//        for(TagCounter tc : urls) {
//            Future<TagCounter> future = executorService.submit(new Callable<TagCounter>() {
//                @Override
//                public TagCounter call() throws Exception {
//                    tc.doWork();
//                    return tc;
//                }
//            });
//            list.add(future);
//        }