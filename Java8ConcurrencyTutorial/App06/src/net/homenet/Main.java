package net.homenet;

import sun.jvm.hotspot.ui.action.FindAction;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

@SuppressWarnings("Duplicates")
public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.runReentrantLockSample1();
        System.out.println();

        app.runReentrantLockSample2();
        System.out.println();

        app.runReadWriteLockSample();
        System.out.println();

        app.runStampedLockSample1();
        System.out.println();

        app.runStampedLockSample2();
        System.out.println();

        app.runStampedLockSample3();
    }

    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();
    private void incrementSync() {
        lock.lock();
        try {
            count = count + 1;
        } finally {
            lock.unlock();
        }
    }

    private void runReentrantLockSample1() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10000)
            .forEach(i -> executor.submit(this::incrementSync));

        ConcurrentUtils.stop(executor);
        System.out.println("Count=" + count);
    }

    private void runReentrantLockSample2() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ReentrantLock lock = new ReentrantLock();

        executor.submit(() -> {
            lock.lock();
            try {
                ConcurrentUtils.sleep(1);
            } finally {
                lock.unlock();
            }
        });

        executor.submit(() -> {
            System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            System.out.println("Lock acquired: " + lock.tryLock());
        });

        ConcurrentUtils.stop(executor);
    }

    private void runReadWriteLockSample() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        executor.submit(() -> {
            lock.writeLock().lock();
            try {
                ConcurrentUtils.sleep(1);
                map.put("foo", "bar");
            } finally {
                lock.writeLock().unlock();
            }
        });

        Runnable task = () -> {
            lock.readLock().lock();
            try {
                System.out.println(map.get("foo"));
                ConcurrentUtils.sleep(1);
            } finally {
                lock.readLock().unlock();
            }
        };

        //# After the writer lock released both read tasks are executed in parallel and print the result simultaneously
        //  to the console.
        executor.submit(task);
        executor.submit(task);

        ConcurrentUtils.stop(executor);
    }

    private void runStampedLockSample1() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                ConcurrentUtils.sleep(1);
                map.put("foo", "bar");
            } finally {
                lock.unlockWrite(stamp);
            }
        });

        Runnable task = () -> {
            long stamp = lock.readLock();
            try {
                System.out.println(map.get("foo"));
                ConcurrentUtils.sleep(1);
            } finally {
                lock.unlockRead(stamp);
            }
        };
        executor.submit(task);
        executor.submit(task);

        ConcurrentUtils.stop(executor);
    }

    private void runStampedLockSample2() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.tryOptimisticRead();
            try {
                System.out.println("Optimistic lock valid: " + lock.validate(stamp));
                ConcurrentUtils.sleep(1);
                System.out.println("Optimistic lock valid: " + lock.validate(stamp));
                ConcurrentUtils.sleep(2);
                System.out.println("Optimistic lock valid: " + lock.validate(stamp));
            } finally {
                lock.unlock(stamp);
            }
        });

        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                System.out.println("Write lock acquired");
                ConcurrentUtils.sleep(1);
            } finally {
                lock.unlock(stamp);
                System.out.println("Write done");
            }
        });

        ConcurrentUtils.stop(executor);
    }

    private void runStampedLockSample3() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        StampedLock lock = new StampedLock();

        count = 0;
        executor.submit(() -> {
            long stamp = lock.readLock();
            try {
                if (count == 0) {
                    stamp = lock.tryConvertToWriteLock(stamp);
                    if (stamp == 0) {
                        System.out.println("Could not convert to write lock");
                        stamp = lock.writeLock();
                    }
                    count = 23;
                }
            } finally {
                lock.unlock(stamp);
            }
        });

        ConcurrentUtils.stop(executor);
        System.out.println("Count=" + count);
    }
}































