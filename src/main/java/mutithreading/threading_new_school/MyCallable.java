package mutithreading.threading_new_school;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

    // The callable interface is the direct descendant of Runnable interface
    // Callable is the way to specify what action should be performed on another thread
    // in this way it's exactly like Runnable, but Callable (which is part of new
    // concurrency libraries in Java) has 2 important advantages over Runnable.

    // 1. In Callable a return value of the thread communication can be specified using a template
    // parameter. In Runnable the .run() method does not return a value,
    // so the calling code has to relay on shared member variables to examine the result.
    // As we saw previously shared memory leads to all kind of complications around memory errors.

    // 2. In Callable, it is possible for the .call method to throw exception and for these
    // exceptions to be caught and handled by the main thread. This is a major advantage - otherwise it is difficult
    // for the main thread to catch exception from threading issues.

    private static int m_myCount = 0;

    public static int getM_myCount() {
        return m_myCount;
    }

    public static void setM_myCount(int m_myCount) {
        MyCallable.m_myCount = m_myCount;
    }

    @Override
    public Integer call() throws Exception {
        if (getM_myCount() <= 1000) {
            setM_myCount(getM_myCount() + 1);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        return getM_myCount();
    }
}
