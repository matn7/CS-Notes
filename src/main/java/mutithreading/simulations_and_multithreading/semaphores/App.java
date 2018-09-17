package mutithreading.simulations_and_multithreading.semaphores;

public class App {

    public static void main(String[] args) {

        // Semaphore maintains set of permits
        // acquire() - if permits is available then takes it
        // release() - adds a permit
        // Semaphore(int permits, boolean fair)

    }

    // Semaphores
    // Used to controlling access to common resources
    // Semaphores - are a records of how many units of a particular resources are available
    // Binary Semaphores - Semaphores restricted to values 0 and 1

    // Semaphores tracks only how many resources are free. It does not keep track of which resources are free
    // Semaphores can be used as trigger for different actions.
    // Producer - Consumer problem can be implemented using Semaphores

    // Mutexes
    // Mutex is essentially a binary semaphore
    // - Mutex has a concept of an owner. Only process that locked the mutex is supposed to unlock it.
    // - Can provide deletion safety
    // - May provide priority inversion safety. If the mutex knows its current owner, it is possible
    // to promote the priority of the owner whenever a higher priority task starts waiting on the mutex.

}
