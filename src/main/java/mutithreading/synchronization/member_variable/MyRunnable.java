package mutithreading.synchronization.member_variable;

public class MyRunnable implements Runnable {

    private int m_myCount = 0;

    public int getM_myCount() {
        return m_myCount;
    }

    public void setM_myCount(int m_myCount) {
        this.m_myCount = m_myCount;
    }

    // synchronized prevent bug that is very difficult to find as it change every time
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
