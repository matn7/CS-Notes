# Exceptions and exception handling.

- Object of type Throwable and its subtypes can be sent up the stack with the throw keyword and caught
with **try...catch** statement.

### Catching an exception.

```java
try {
    doSomething();
} catch (SomeException e) {
    handle(e);
}
// next statement
```

- The statements in the try block executed.
- If no exception thrown by the statements in the try block, then control passes to the next statement after
the **try...catch**.
- If an exception thrown in the try block:
    - The exception object tested to see if it is an instance of SomeException or a subtype.
    - If it is, then the catch block will catch the exception:
        - The variable **e** is bound to exception object.
        - The code within the catch block executed.
        - If that code throws an exception, then the newly thrown exception propagated in place to the original one.
        - Otherwise, control process to the next statement after the **try...catch**.
    - If it is not, the original exception continues to propagate.

### Try-catch with multiple catches.

```java
try {
    doSomething();
} catch (SomeException e) {
    handleOneWay(e)
} catch (SomeOtherException e) {
    handleAnotherWay(e);
}
// next statement
```

- If there are multiple catch blocks, they are tried one at a time starting with the first one, until a match found for
the exception.
- The corresponding handler executed, and then control passed to the next statement after **try...catch**.
- The catch blocks after the one matches always skipped, even if the handler code throws the exception.

```java
try {
    throw new RuntimeException("test");
} catch (Exception e) {
    System.out.println("Exception");
} catch (RuntimeException e) {
    System.out.println("RuntimeException");
}
```

- Will output "Exception". 
- Since RuntimeException is a subtype of Exception, the first more general catch will be matched.
- Second more specific catch will never be executed.
- Most specific catch blocks (in terms of the exception types) should appear first, and most general one should be last.
- Some Java compilers will warn you if a catch will never be executed but this is not a compilation error.

### Multi-exception catch blocks.

```java
try {
    doSomething();
} catch (SomeException | SomeOtherException e) {
    handleSomeException(e);
}
```

- The behavior of a multi-exception catch is a simple extension for the single-exception case.
- The catch matches if the thrown exception matches (at least) one of the listed exceptions.
- The type of **e** is a synthetic union of the exception types in the list. 
- When the value of e is used, its static type is the last common supertype of the type union.
- However, if **e** is rethrown within the catch block, the exception types that are rethrown are the types in the union.

```java
public void method() throws IOException, SQLException {
    try {
        doSomething();
    } catch (IOException | SQLException e) {
        report(e);
        throw e;
    }
}
```

- IOException and SQLException are checked exceptions whose least common supertype is Exception.
- This means that the report method must match report(Exception).
- The compiler knows that the throw can throw only an IOException or SQLException.
- Method can be declared as thrown IOException, SQLException rather than throws Exception.

### The try-witch-resources statement.

- :star: What is resource, Java 7 introduced the **java.lang.AutoCloseable** interface to allow classes to be managed 
using **try-with-resources** statement.
- :star: **Instances of classes that implement AutoCloseable are referred to as resources.**
- These typically need to be disposed of in a timely fashion rather than relying on the **GC** to dispose them.
- The AutoCloseable interface defines a single method.

```java
public void close() throws Exception
```

- **close()** method should dispose of the resource in appropriate fashion.
- Classes that implement AutoCloseable are strongly encouraged to declare the **close()** method to throw a more
specific exception than Exception, or no exception at all.
- Java Classes that implements AutoCloseable:
    - InputStream, OutputStream and their subclasses.
    - Reader, Writer and their subclasses.
    - Socket and ServerSocket and their subclasses.
    - Channel and its subclasses.
    - JDBC interfaces Connection, Statement and ResultSet and their subclasses.

**The basic try-with resource statement.**

```java
try (PrintStream stream = new PrintStream("hello.txt")) {
    stream.println("Hello");
}
```

**The enhanced try-with-resource statement.**

```java
try (PrintStream stream = new PrintStream("hello.txt")) {
    stream.println("Hello");
} catch (FileNotFoundException ex) {
    System.err.println("Cannot open the file");
} finally {
    System.out.println("All done");
}
```

- The resource variable is out of scope in the catch and finally blocks.
- The resource cleanup will happen before the statement tries to match the catch block.
- If the automatic resource cleanup threw an exception, then that could be caught in one of the catch blocks.

**Managing multiple resources.**

```java
try (InputStream is = new FileInputStream(file1);
    OutputStream os = new FileOutputStream()) {
    // Copy 'is' to 'os'
}
```

- The initializations occur in the code order, and later resource variable initializers can use of the values of
the earlier ones.
- All resource variables that were successfully initialized will be cleaned up.
- Resource variables cleaned up in reverse order of their declarations.
- **is** is initialized before **os** and cleaned up after it, and **is** will be cleaned up if there is an exception
while initializing **os**.

**Equivalence of try-with-resource and classical try-catch-finally.**

```java
try (PrintStream stream = new PrintStream("hello.txt")) {
    stream.println("Hello world!");
}
```

**equivalent with try-catch-finally.**

```java
// Note that the constructor is not part of the try-catch statement
PrintStream stream = new PrintStream("hello.txt");

// This variable is used to keep track of the primary exception thrown
// in the try statement. If an exception is thrown in the try block,
// any exception thrown by AutoCloseable.close() will be suppressed.
Throwable primaryException = null;

// The actual try block
try {
    stream.println("Hello world!");
} catch (Throwable t) {
    // If an exception is thrown, remember it for the finally block
    primaryException = t;
    throw t;
} finally {
    if (primaryException == null) {
        // If no exception was thrown so far, exceptions thrown in close() will
        // not be caught and therefore be passed on to the enclosing code.
        stream.close();
    } else {
        // If an exception has already been thrown, any exception thrown in
        // close() will be suppressed as it is likely to be related to the
        // previous exception. The suppressed exception can be retrieved
        // using primaryException.getSuppressed().
        try {
            stream.close();
        } catch (Throwable suppressedException) {
            primaryException.addSuppressed(suppressedException);
        }
    }
}
```

**The enhanced form of try-with-resources is specified as an equivalence with basic form.**

```java
try (PrintStream stream = new PrintStream(fileName)) {
    stream.println("Hello world!");
} catch (NullPointerException ex) {
    System.err.println("Null filename");
} finally {
    System.out.println("All done");
}
```

**is equivalent to.**

```java
try {
    try (PrintStream stream = new PrintStream(fileName)) {
        stream.println("Hello world!");
    }
} catch (NullPointerException ex) {
    System.err.println("Null filename");
} finally {
    System.out.println("All done");
}
```

### Custom exceptions

- It is simpler from a code-design standpoint to use existing generic Exception classes when throwing exceptions.
- If you need the exception to carry a simple error message.
    - In that case, RuntimeException is usually preferred, since it is not checked Exception.
- Other exception classes exist for common classes of errors:
    - **UnsupportedOperationException:** A certain operation not supported.
    - **IllegalArgumentException:** An invalid parameter value passed to a method.
    - **IllegalStateException**
- Cases where you do want to use a custom exception class include the following:
    - You are writing an API or library for use by others, and you want to allow users of your API to be able to
    specifically catch and handle exceptions from your API, and be able to differentiate those exceptions from other,
    more generic exceptions.
    - You are throwing exceptions for a specific kind of error in one part of your program, which you want to
    catch and handle in another part of your program, and you want to be able to differentiate these errors from other
    more generic errors.
- You can create your own custom exceptions by extending RuntimeException for an unchecked exception, or checked
exception by extending any Exception which is not also subclass of RuntimeException because:
    - Subclasses of Exception that are not also subclasses of RuntimeException are checked exceptions

```java
public class StringTooLongException extends RuntimeException {
    // Exceptions can have methods and fields like other classes
    // those can be useful to communicate information to pieces of code catching
    // such an exception
    public final String value;
    public final int maximumLength;
    public StringTooLongException(String value, int maximumLength){
        super(String.format("String exceeds maximum Length of %s: %s", maximumLength, value));
        this.value = value;
        this.maximumLength = maximumLength;
    }
}
```

**Use:

```java
void validateString(String value) {
    if (value.length() > 30) {
        throw new StringTooLongException(value, 30);
    }
}
```

**Used where the exception caught and handled**

```java
void anotherMethod(String value) {
    try {
        validateString(value);
    } catch (StringTooLongException e) {
        System.out.println("The string '" + e.value +
            "' was longer than the max of " + e.maximumLength);
    }
}
```

- :star: If a client can reasonably be expected or recover from an exception, make it checked exception.
- :star: If client cannot do anything to recover from the exception, make it unchecked exception (Runtime).

**Why does runtime exception not require an explicit exception handling.**

```
For Java, RuntimeException is considered to be system exception, generally, it's not recoverable,
so you needn't add throws declaration on the method or use try catch block to handle it.
However, Exception is considered to be application exception, it is recoverable.
```

### :star: Handling InterruptedException.

- If an InterruptedException is caught it means someone, somewhere, called **Thread.interrupt()** on the thread your 
code is currently running in.

```java
// Bad. Don't do this.
try {
     Thread.sleep(1000);
} catch (InterruptedException e) {
    // disregard
}
```

- Wrong way to handle an impossible event occurring. 
- If you know your application will never encounter an **InterruptedException** you should treat such an event as 
a serious violation of your program's assumptions and exit as quickly as possible.
- The proper way to handle an **impossible** interrupt is.

```java
// When nothing will interrupt your code
try {
     Thread.sleep(1000);
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
    throw new AssertionError(e);
}
```

- It restores interrupt status of the thread, and then throws an AssertionError indicating the basic invariants of your 
application have been violated.
- If you know for certain you'll never interrupt the thread this code runs in this is safe since the catch
block should never be reached.
- You cannot guarantee that your thread will never be interrupted. 
- In particular if you're writing code that will be executed by an Executor, or some other thread management 
it's critical your code responds promptly to interrupts, otherwise your application will stall or even deadlock.
- In such cases the best thing to do is generally to allow the InterruptedException to propagate up the call stack,
adding throws InterruptedException to each method in turn.

```java
// Let the caller determine how to handle the interrupt if you're unsure
public void myLongRunningMethod() throws InterruptedException {
    // ...
}
```

- In limited cases (when overriding a method that doesn't throw any checked exceptions) you can reset the interrupted
status without raising an exception, expecting whatever code executed next to handle the interrupt.

```java
// Suppresses the exception but resets the interrupted state letting later code
// detect the interrupt and handle it properly.
try {
    Thread.sleep(1000);
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
    return; // your expectations are still broken at this point - try not to do more work.
}
```

### Return statements in try catch block

- Bad practice but possible.

```java
public static int returnTest(int number){
    try {
        if (number % 2 == 0) {
            throw new Exception("Exception thrown");
        } else {
            return x;
        }
    } catch(Exception e) {
        return 3;
    } finally {
        return 7;
    }
}
```

- Finally, returns 7, this value supersedes the try/catch return value.
- If the catch block returns a primitive value and that primitive value subsequently changed in the finally block,
the value returned to the catch block will be returned, and the changes from finally block will be ignored.

```java
public class FinallyExample {
    public static void main(String[] args) {
        int n = returnTest(4);

        System.out.println(n); // will print "0"
    }

    public static int returnTest(int number) {
        int returnNumber = 0;

        try {
            if (number % 2 == 0) {
                throw new Exception("Exception thrown"):
            } else {
                return returnNumber;
            }
        } catch (Exception e) {
            return returnNumber;
        } finally {
            returnNumber = 1;
        }
    }

}
```

### Exception Introduction

- Exceptions are errors which occur when a program is executing.

```java
class Division {
    public static void main(String[] args) {
        int a, b, result;

        Scanner input = new Scanner(System.in);
        System.out.println("Input two integers");

        a = input.nextInt();
        b = input.nextInt();

        result = a / b;

        System.out.println("Result = " + result);
    }
}
```

- Division by zero is an invalid operation that would produce a value that cannot be represented as an integer.
- Java deals with it by throwing as exception. 
- In this case ArithmeticException.

```java
a = input.nextInt();
b = input.nextInt();
try {
    result = a / b;
} catch (ArithmeticException e) {
    System.out.println("An ArithmeticException occurred. Perhaps you tried to divide by zero.");
    return;
}
```

- A try catch block executed as follows:
    - Begin executing the code in the try block.
    - If an exception occurs in the try block, immediately abort and check to see if this exception caught by the
    catch block.
    - If the exception caught, it is assigned to the variable **e**, and the catch block executed.
    - If either the try or catch block completed then continue to execute code below the try-catch block.
- Instead of returning null when a method fails, it is usually better practice to throw and exception so that 
the application making use of the method can define its own flow control for the situation via exception handling.

### The Java Exception Hierarchy - Unchecked and Checked Exceptions.

- All Java exceptions are instances of classes in the Exception class hierarchy.
- **java.lang.Throwable** this is the base class for all exception classes. 
- Its methods and constructors implement a range of functionality common to all exceptions:
    - **java.lang.Exception** this is the superclass of all normal exceptions.
        - Various standard and custom exception classes.
        - **java.lang.RuntimeException** this the superclass of all normal exceptions that are unchecked exceptions.
            - Various standard and custom runtime exception classes.
    - **java.lang.Error** this is the superclass of all "fatal error" exceptions.
- The Throwable, Exception and RuntimeException class should be treated as abstract.
- The Error exceptions thrown by the JVM in situations where it would be unsafe or unwise for an application
to attempt to recover.
- It would be unwise to declare custom subtypes of Throwable. 
- Java tools and libraries may assume that Error and Exception are the only direct subtypes of Throwable, 
and misbehave if that assumption is incorrect.

### Checked vs Unchecked Exceptions.

- Unhandled exception is liable to cause a program to crash.
- Checked exceptions typically represent anticipated events that an application should be able to deal with.
- IOException and its subtypes represent error conditions that can occur in I/O operations.
- Examples include, file opens failing because a file or directory does not exist, network reads and writes failing
because a network connection has been broken.
- Unchecked exceptions typically represent unanticipated events that an application cannot deal with. 
- These are typically the result of a bug in the application.
- When checked exception may occur:
    - When a checked exception thrown or propagated in a method, it must either be caught by the method, or
    listed in the method's throws clause.
    - When a checked exception thrown or propagated in an intializer block, it must be caught the block.
    - A checked exception cannot be propagated by a method call in a field initialization expression.
- Checked exception must be either handled, or declared.

### Checked exception examples.

```java
// This declares a custom checked exception
public class MyException extends Exception {
    // ...
}

// This declares a custom unchecked exception
public class MyException2 extends RuntimeException {
    // ...
}
```

**Incorrect**

```java
// INCORRECT
public void methodThrowingCheckedException(boolean flag) {
    int i = 1 / 0; // Compiles OK
    if (flag) {
        throw new MyException(); // Compilation error
    } else {
        throw new MyException2(); // Compiles OK
    }
}
```

**Correct**

```java
// CORRECTED
public void methodThrowingCheckedException(boolean flag) throws MyException {
    int i = 1 / 0; // Compiles OK
    if (flag) {
        throw new MyException(); // Compilation OK
    } else {
        throw new MyException2(); // Compiles OK
    }
}
```

**How a propagated checked exception can be dealt with.**

```java
// INCORRECT
public void methodWithPropagatedCheckedException() {
    InputStream is = new FileInputStream("someFile.txt"); // Compilation error
    // FileInputStream throws IOException or a subclass if the file cannot
    // be opened. IOException is a checked exception.
    // ...
}

// CORRECTED (Version A)
public void methodWithPropagatedCheckedException() throws IOException {
    InputStream is = new FileInputStream("someFile.txt");
    // ...
}

// CORRECTED (Version B)
public void methodWithPropagatedCheckedException() {
    try {
        InputStream is = new FileInputStream("someFile.txt");
        // ...
    } catch (IOException ex) {
        System.out.println("Cannot open file: " + ex.getMessage());
    }
}
```

**:star: How to deal with a checked exception in a static field initializer.**

```java
// INCORRECT
public class Test {
    private static final InputStream is = new FileInputStream("someFile.txt"); // compilation error
}

// CORRECTED
public class Test {
    private static final InputStream is;
    static {
        InputStream tmp = null;
        try {
            tmp = new FileInputStream("someFile.txt");
        } catch (IOException ex) {
            System.out.println("Cannot open file: " + ex.getMessage());
        }
        is = tmp;
    }
}
```

- Note that we also have to deal with the problems that is cannot be assigned to more than once,
and yet also has to be assigned to, even in the case of an exception.

### Creating and reading stacktrace

- When an exception object created, the Throwable constructor captures information about the context in which
the exception created. 
- Later on, this information can be output in the form of a stacktrace, which can be used to help diagnose 
the problem that caused the exception in the first place.

**Printing stacktrace**

- The stacktrace does not include the details of the exception itself.
- Stacktrace printing should be used sparingly.

**Understanding a stacktrace**

- Each line represents a method (or constructor) call on the call stack:
    - The name of the class and method that was being executed.
    - The source code filename.
    - The source code line number of the statement that was being executed.
- The class and method names in the stack frames are the internal names for the classes and methods. 
- You will need to recognize the following unusual cases:
    - A nested or inner class will look like **"OuterClass$InnerClass"**.
    - An anonymous inner class will look like **"OuterClass$1"**, **"OuterClass$2"**, etc.
    - When code in a constructor, instance field initializer, or an instance initializer block executed, the
    method name will be "".
    - When code in a static field initializer or static initializer block executed, the method name will be "".

**Exception chaining and nested stacktraces**

- Exception chaining happens when a piece of code catches an exception, and then creates and throws a new one,
passing the first as the cause.

```java
public class Test {
    int foo() {
        return 1 / 0;
    }

    public Test() {
        try {
            foo();
        } catch (ArithmeticException ex) {
            throw new RuntimeException("A bad thing happened " + ex);
        }
    }

    public static void main(String[] args) {
        new Test();
    }
}
```

- The cause mechanism only exposed in the Throwable API in Java 1.4.0. Prior to that, exception chaining
needed to be implemented by the application using a custom exception field to represent the cause, and a custom
printStackTrace method.

### Throwing an exception.

```java
public void checkNumber(int number) throws IllegalArgumentException {
     if (number < 0) {
        throw new IllegalArgumentException("Number must be positive: " + number);
    }
}
```

- When the exception thrown, it causes the enclosing statements to terminate abnormally until the exception handled.
- It is good practice to both create and throw the exception object in a single statement.
- Code immediately after a throw statement is unreachable.

```java
throw new IllegalArgumentException("Bad");
return;     // unreachable
```

- The compiler would report a compilation error for the return statement.

**Exception chaining**

```java
public class AppErrorException extends RuntimeException {
    public AppErrorException() {
        super();
    }
    public AppErrorException(String message) {
        super(message);
    }
    public AppErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

### Advanced features of Exceptions

**Examining the call stack programmatically**

```java
Exception ex = new Exception(); // this captures the call stack
StackTraceElement[] frames = ex.getStackTrace();
System.out.println("This method is " + frames[0].getMethodName());
System.out.println("Called from method " + frames[1].getMethodName());
```

- The information available in a StackTraceElement is limited. 
- There is no more information available than is displayed by printStackTrace.

**Optimizing exception construction**

- Constructing an exception is rather expensive because it entails capturing the recording information about all
stack frames on the current thread. 
- Sometimes, we know that that information is never going to be used for a given
exception; e.g. the stacktrace will never be printed. 
- In that case, there is an implementation trick we can use in a custom exception to cause the information 
to not be captured.
- The stack frame information needed for stack traces, is captured when the Throwable constructors call the
**Throwable.fillInStackTrace()** method. 
- This method is public, which means that a subclass can override it.

```java
public class MyException extends Exception {
    @Override
    public void fillInStackTrace() {
        // ...
    }
}
```

### Erasing or replacing the stacktrace

- **Throwable.setStackTrace** can be used to replace the array of StackTraceElement objects that holds the information.

```java
exception.setStackTrace(new StackTraceElement[0]);
```

### Suppressed exceptions

- Java 7 introduced the try-with-resources construct, and the associated concept of exception suppression.

```java
try (Writer w = new BufferedWriter(new FileWriter(someFilename))) {
    // do stuff
    int temp = 1/0; // throws an ArithmeticException
}
```

- When the exception thrown, the try will call `close()` on the `w` which will flush any buffered output and then close
the FileWriter.
- When IOException is thrown while flushing output data exception while cleaning up a resource suppressed.
- The exception caught, and added to the primary exception's suppressed exception list. 
- Next the try-with-resources will continue with cleanup of the other resources. 
- Finally, primary exception will be rethrown.
- A similar pattern occurs if an exception it throw during the resource initialization, or if the try block completes
normally. 
- The first exception thrown becomes the primary exception, and subsequent ones arising from cleanup suppressed.
- The suppressed exceptions can be retrieved from the primary exception object by calling getSuppressedExceptions.

### The try-finally and try-catch-finally.

- The finally block contains code that will be executed in all circumstances. 
- This makes them suitable for resource management, and other kind of cleanups.

**try-finally**

```java
try {
    doSomething();
} finally {
    cleanUp();
}
```

- The code in the try block executed.
- If no exception thrown in the try block:
    - The code in the finally block executed.
    - If the finally block throws an exception, that exception propagated.
    - Otherwise, control passes to the next statement after the **try...finally**.
- If an exception thrown in the try block:
    - The code in the finally block executed.
    - If the finally block throws an exception, that exception propagated.
    - Otherwise, the original exception continues to propagate.
- The code within finally block will always be executed.
- The only exceptions are if **System.exit(int)** is called or JVM crashes.
- Finally block is the correct place code that always need to be executed, like closing files, releasing locks.

```java
// This code snippet writes the first line of a file to a string
String result = null;
Reader reader = null;
try {
    reader = new BufferedReader(new FileReader(fileName));
    result = reader.readLine();
} catch (IOException ex) {
    Logger.getLogger.warn("Unexpected IO error", ex); // logging the exception
} finally {
    if (reader != null) {
        try {
            reader.close();
        } catch (IOException ex) {
            // ignore / discard this exception
        }
    }
}
```

- We declare the resource (reader variable) before the try block so that it will be in scope for the finally block.
- By putting the **new FileReader(...)**, the catch is able to handle any IOError exception from thrown when opening file.
- We need a reader **.close()** in the finally block because there are some exception paths that we cannot intercept
either in the try block or in catch block.
- However, since an exception might have been thrown before reader initialized, we also need an explicit null test.
- Finally, the reader **.close()** call might throw an exception.

**The `throws` clause in a method declaration**

- Java's checked exception mechanism requires the programmer to declare that certain methods could throw specified
checked exceptions. 
- This is done using the throws.

```java
public class OddNumberException extends Exception { // checked exception
}

public void checkEven(int number) throws OddNumberException {
    if (number % 2 != 0) {
        throw new OddNumberException();
    }
}
```

- The throws OddNumberException declares that a call to checkEven could throw an exception that is of type
OddNumberException.
- A throws clause can declare a list of types, and can include unchecked exceptions as well as checked exceptions.

```java
public void checkEven(Double number) throws OddNumberException, ArithmeticException {
    if (!Double.isFinite(number)) {
        throw new ArithmeticException("INF or NaN");
    } else if (number % 2 != 0) {
        throw new OddNumberException();
    }
}
```

**What is the point of declaring unchecked exceptions as thrown?**

- The throws clause in a method declaration serves two purposes:
    - :one: It tells the compiler which exceptions thrown so that the compiler can report uncaught (checked) exceptions
    as errors.
    - :two: It tells a programmer who is writing code that calls the method what exceptions to expect. For this purpose,
    it often makes to senses to include unchecked exceptions in throws list.

**Throws and method overriding.**

- An override method can be declared with the same set of checked exceptions as thrown by the overriden method, or with
a subset. 
- However, the override method cannot add extra checked exceptions.

```java
@Override
public void checkEven(int number) throws NullPointerException {
    // OK - NullPointerException is an unchecked exception
}

@Override
public void checkEven(Double number) throws OddNumberException { // OK—identical to the superclass
}

class PrimeNumberException extends OddNumberException {}

class NonEvenNumberException extends OddNumberException {}

@Override
public void checkEven(int number) throws PrimeNumberException, NonEvenNumberException // OK—these
// are both subclasses

@Override
public void checkEven(Double number) throws IOExcepion // ERROR
```

- The reason for this rule is that if an overriden method can throw a checked exception that the overriden method
could not throw, that would break the type substitutability.

***

## :star: Exceptions

```
If one of the threads of your program throws an exception that isn't caught
by any method along the method invocation stack, that thread will expire.
```

**:star: Question: If exception in catch block will be catch again ?**

- Answer NO

```java
public class Catch {
    public static void main(String[] args) {
        try {
            throw new java.io.IOException();
        } catch (java.io.IOException exc) {
            System.err.println("In catch IOException: "+exc.getClass());
            throw new RuntimeException();
        } catch (Exception exc) {
            System.err.println("In catch Exception: "+exc.getClass());
        } finally {
            System.err.println("In finally");
        }
    }
}
```

- Output

```
In catch IOException: class java.io.IOException
In finally
Exception in thread "main" java.lang.RuntimeException
        at Catch.main(Catch.java:8)
```

### Checked vs unchecked exceptions

- There are two kinds of exceptions in Java, checked and unchecked, and only checked exceptions need
appear in throws clauses.
- The general rule is: 
    - Any checked exceptions that may be thrown in a method must either be caught or declared in the method's 
    throws clause.
- Checked exceptions called because both the Java compiler and, the Java Virtual Machine check to make sure this 
rule obeyed.

![Exceptions](images/exceptions.png "Exceptions")
