package net.homenet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({"Duplicates", "WeakerAccess"})
public class ConcurrentUtils {
    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void stop(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Termination interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.out.println("Killing non-finished task");
            }
            executor.shutdownNow();
        }
    }
}
