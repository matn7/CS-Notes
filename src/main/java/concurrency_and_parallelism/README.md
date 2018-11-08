## Concurrency
- Handling many tasks at the same time span

## Parallelism
- Processing many time at the same time

***

## Single processor machine with time-sliced scheduling

- Time Span (TS) = Start to finish

```
Start time |-----------------------------------------| Finish time
            \****Task1****/\**Task2**/\****Task1****/
```

- * is one system-clock tick
- Task1 and Task2 share the processor

### Task1 and Task2 are processed concurrently during time span TS, their processing overlaps during TS
- Task1 and Task2 are not processed in parallel, single processor must be shared
- Example shows concurrency, but not true parallelism

## Multi processor machine

- Better throughput

```
Start time |-----------------------| Finish time
            \********Task1********/         Processor 1
                    \**Task2**/             Processor 2
```

- Task2 can happen anywhere within TS

### Task1 and Task2 are processed concurrently (during the same TS)
- Task1 and Task2 are processed in parallel

***

## C programming language concurrency/parallelism

- Most libraries are written in C

### C multiprocessing library functions:
    - fork()
    - execv()
    - kill()

### C multithreading library functions:
    - pthread_create()
    - pthread_exit()
    - pthread_join()
    - pthread_exit()
    - pthread_mutex_lock()
    - pthread_mutex_unlock()

### C non-blocking I/O functions
    - fcntl()
    - epoll_create1(), epoll_wait()

### Java (C#) favor multithreading for concurrency
    - Go have emphasized to multithreading

### JavaScript, Ruby, Perl, Python favor multiprocessing

***

## System call

### The system context: user-space and kernel-space code
#### User-space code does not control shared physical resources (processors, memory, I/O devices)
    - Applications execute in user space
#### Kernel-space code comprises the core OS routines that control shared physical resources
    - A **system-call** originates in user-space, but results in execution of a kernel space routine
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

- Concurrency and parallelism are different
    - Concurrency involves handling multiple tasks in the same time span
    - Parallelism involves processing multiple tasks literally at the same time
        - True parallelism requires multiple processors
- The classic approaches to concurrency are multiprocessing and multithreading
    - Non blocking I/O 'concurrency without parallelism'
- Programming languages
    - C is close to the metal, the system libraries and core OS routines at work under the hood
    - Whatever app language, multiprocessing, multithreading and non-blocking I/O calls in the language hit the C libraries
    and, from there, kernel-space routines
    - Java, Go, Node.js have particular styles and strengths with respect to concurrency in particular.

***

## Processes and threads

### A process is a 'program in execution', each with its own address space
- The address space comprises the memory location accessible to the process (segmentation failed)
- Separate address spaces effectively partition memory among the processes.

### A thread is a sequence of executable instructions within a process
- Threads within a process share the same address space--they have access to exactly the same memory location.
This is a root cause of **race condition**
- A race condition arises when two or more threads concurrently access the same memory location and at least one of the
threads tries to update the location.

```c
n = random_num();
```
- n is shared memory location among the threads, the result is unpredictable

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

```bash
$ cat names.txt | sort
```

- **cat** and **sort** are executing processes
- sort performs blocking I/O it waits for all the bytes from 'cat' before doing the sorting
- If multiprocessors machine, 'cat' and 'sort' can execute on different processors
- Pipe operator | performs automatic multithreading
    - A pipe is a mechanism for inter-process communication (IPC)
- Task (producing the bytes in the file and then sorting the lines) are divided between two processed

### General approaches in code:
    - A parent process 'forks' (clones, spawns) a child process, and both execute code from the same program
        - If-else separates the parent code from the child code
    - A parent process forks a child, which then executes a separate program
















