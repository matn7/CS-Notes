
## 1. Concurrency - Multitasking

1. Responsiveness - Concurrency
2. Performance - Parallelism

- Responsiveness can be achieved by using multiple threads, separate thread for each task
- Achieved by multi-tasking between threads
- We don't need multiple cores to achieve concurrency
- We can create an illusion of multiple tasks executing in parallel using just a single core
- With multiple cores we can truly run tasks completely in parallel
- Completing a complex task much faster
- Finishing more work in the same period of time
- For high scale service:
    - Fewer machines
    - Less money spent on hardware

```
Core2 |   Task3   Task4   Task3   Task4   Task3   Task4
Core1 |   Task1   Task2   Task1   Task2   Task1   Task2
----------------------------------------------------------->
Thread
```

- Stack - Region in memory where local variable are stored, and passed into functions
- Instruction pointer - Address of the next instruction to execute

## OS

### Context Switch

- Stop thread 1
- Schedule thread 1 out
- Schedule thread 2 in
- Start thread 2

- Context switch is not cheap, and is the price of multitasking (concurrency)
- Each thread consumes resources in the CPU and memory
- Context switch involves storing data for one thread, and restoring data for another thread
- Too many threads - Thrashing, spending more time in management than real productive work
- Threads consume less resources than processes. Context switching between threads from
the same process is cheaper

### Thread scheduling

- First Come First Serve
    - Problem - Long thread can cause starvation
- Shortest Job first

- **Epochs**
- Dynamic Priority

```
Dynamic Priority = Static Priority + Bonus
```

- Static Priority is set by the developer programmatically
- Bonus is adjusted by the OS in every epoch, for each thread
- Using Dynamic Priority, the OS will give preference for Interactive threads (such as UI threads)
- OS will give preference to threads that did not complete in the last epochs, or did not get enough time
to run - Preventing Starvation

### Threads vs Processes

- When to prefer Multithreaded Architecture
    - Prefer if the tasks share a lot of data
    - Threads are much faster to create and destroy
    - Switching between threads of the same process is faster (shorted context switching)
- When to prefer Multi-Process Architecture
    - Security and stability are of high importance
    - Tasks are unrelated to each other