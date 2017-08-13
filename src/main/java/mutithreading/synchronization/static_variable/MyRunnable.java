package mutithreading.synchronization.static_variable;

/**
 * Created by Mati on 10.07.2017.
 */
public class MyRunnable implements Runnable {

    private static int m_myCount = 0;

    public static int getM_myCount() {
        return m_myCount;
    }

    public static void setM_myCount(int m_myCount) {
        MyRunnable.m_myCount = m_myCount;
    }

    @Override
    public synchronized void run() {
        while (m_myCount <= 1000) {
            m_myCount++;
            System.out.println(m_myCount);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
