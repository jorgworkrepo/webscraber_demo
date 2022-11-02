package webscraper;

import java.util.ArrayList;
import java.util.List;

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

    public static List<TagCounter> runParallel()  {

        List<TagCounter> urls = new ArrayList<>();

        // TODO:

        return urls;
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
