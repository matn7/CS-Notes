## Concurrency

- Handling many tasks at the same time span.

## Parallelism

- Processing many time at the same time.

***

## Single processor machine with time-sliced scheduling

- Time Span (TS) = Start to finish.

```
Start time |-----------------------------------------| Finish time
            \****Task1****/\**Task2**/\****Task1****/
```

- `*` is one system-clock tick.
- Task1 and Task2 share the processor.

- **Task1 and Task2 are processed concurrently during time span TS, their processing overlaps during TS.**
- Task1 and Task2 are not processed in parallel, single processor must be shared.
- Example shows concurrency, but not true parallelism.

## Multi processor machine

- Better throughput.

```
Start time |-----------------------| Finish time
            \********Task1********/         Processor 1
                    \**Task2**/             Processor 2
```

- Task2 can happen anywhere within TS.

- **Task1 and Task2 are processed concurrently (during the same TS).**
- Task1 and Task2 are processed in parallel.

***

## C programming language concurrency/parallelism

- Most libraries are written in C.
- C multiprocessing library functions:
    - fork()
    - execv()
    - kill()
- C multithreading library functions::
    - pthread_create()
    - pthread_exit()
    - pthread_join()
    - pthread_exit()
    - pthread_mutex_lock()
    - pthread_mutex_unlock()
- C non-blocking I/O functions:
    - fcntl()
    - epoll_create1()
    - epoll_wait()

- **Java (C#) favor multithreading for concurrency**
- Go have emphasized to multithreading
- **JavaScript, Ruby, Perl, Python favor multiprocessing**

***

## System call

### The system context: user-space and kernel-space code

- **User-space code does not control shared physical resources (processors, memory, I/O devices)**
- Applications execute in user space.
- Kernel-space code comprises the core OS routines that control shared physical resources.
- A **system-call** originates in user-space, but results in execution of a kernel space routine:
    - Standard library functions (user-space) mediate between ordinary app code and the core OS routines (kernel-space)
    that some library functions call.
    - Standard library goes by various names: on Unix-type systems, (libc) or variants thereof (glibc on Ubuntu) on
    Windows (Win32 API)

### Node.js example

```
                                                        system call
        calls                calls           user space <--+--> kernel space
Node.js ----> cluster.fork() ----> library function fork() | ---> kernel routine (fork in Unix, clone in Linux)
```

- Standard libraries and kernel routines are written in C with some assembly language.

***

## Sum up

- Concurrency and parallelism are different:
    - Concurrency involves handling multiple tasks in the same time span.
    - Parallelism involves processing multiple tasks literally at the same time:
        - True parallelism requires multiple processors.
- The classic approaches to concurrency are multiprocessing and multithreading:
    - Non blocking I/O concurrency without parallelism.
- Programming languages:
    - C is close to the metal, the system libraries and core OS routines at work under the hood.
    - Whatever app language, multiprocessing, multithreading and non-blocking I/O calls in the language hit
    the C libraries and, from there, kernel-space routines.
    - Java, Go, Node.js have particular styles and strengths with respect to concurrency in particular.

***

## Processes and threads

- A **process** is a 'program in execution', each with its own address space.
- The address space comprises the memory location accessible to the process (segmentation failed).
- Separate address spaces effectively partition memory among the processes.
- A **thread** is a sequence of executable instructions within a process.
- Threads within a process share the same address space--they have access to exactly the same memory location.
- This is a root cause of **race condition**.
- A race condition arises when two or more threads concurrently access the same memory location and at least one of the
threads tries to update the location.

```c
n = random_num();
```

- n is shared memory location among the threads, the result is unpredictable.
- Scheduling on modern systems: to schedule a process on a processor is to schedule one of its threads.

```
Process1    scheduled      +------------+
Thread11 ----------------> | processor3 |
Thread12                   +------------+



Process2    scheduled      +------------+
Thread21 ----------------> | processor7 |
Thread22                   +------------+
Thread23

```

## Concurrency & Parallelism: Multithreading

```console
cat names.txt | sort
```

- **cat** and **sort** are executing processes.
- sort performs blocking I/O it waits for all the bytes from 'cat' before doing the sorting.
- If multiprocessors machine, 'cat' and 'sort' can execute on different processors.
- Pipe operator `|` performs automatic multithreading:
    - A pipe is a mechanism for inter-process communication (IPC).
- Task (producing the bytes in the file and then sorting the lines) are divided between two processed.
- General approaches in code:
    - A parent process 'forks' (clones, spawns) a child process, and both execute code from the same program:
        - If-else separates the parent code from the child code.
    - A parent process forks a child, which then executes a separate program.

### Tomcat

- Tomcat implements the 'one-thread-per-request' model for handling client requests.
- Tomcat runs as a single process, which is multithreaded.
- Tomcat delegates each request for any WAR file to a thread, the 'one-thread-per-request' model.
- Tomcat creates a thread pool at start-up: indeed, two thread pools one for requests over HTTP,
another for HTTPS if enables:
    - Thread creation and destruction is relatively expensive process.
- Other Java web services like Jetty, implement the one-thread-per-request model as well.

### Non-blocking-io

- print('Hello World') is blocking.

### Review

- Multiprocessing - dispatch each task to a separate process ('program in execution'):
    - **prefork** - the processes by building a pool of these at start-up. Then grab a process from the pool
    to act as the 'task handler'.
    - When the task-handling process finishes its work, put it to sleep.
    - Apache2, nginx, IIS are production example.
- Multithreading - dispatch each task to a separate thread within process:
    - For efficiency, build a thread-pool at start-up, and grab a thread from the pool to act as the 'task handler'.
    - When the task-handling thread finishes its work, put it to sleep.
    - Tomcat, Jetty examples.
- Non-blocking I/O - a single-threaded process that jumps quickly from one task to another, doing partial
processing of each task:
    - Read from cache by several Task1 and Task2 doing the same things.
    - Node.js example.

### Hybrid

- Multiprocessing + multithreading - IIS (Windows web server) and AspNet runtime:
    - Each worker process is multithreaded (10 thread per worker process), each thread handle single request
    ('one-thread-per-request' model).
- Multiprocessing + non-blocking I/O - nginx:
    - nginx has a 'master process' to read conf file and to watch over worker processes.
    - A 'worker-process' handles a client request.
    - Other processes cache loader and manager.
- Non-blocking I/O + multithreading - Node.js:
    - a single threaded process managing an event loop.
    - 'workers' are JavaScript functions reads and handles a clients requests uning non-blocking I/O and
    callbacks to signal task completion.
    - Long running tasks (DB accessing) are delegated to other threads, with callbacks to signal task completion.

***

## Multiprocessing

- The processes communicate through a 'named pipe' (aka 'FIFO').












