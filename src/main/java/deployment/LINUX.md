- Commands are text you type in terminal
- Commands are interpreted by the shell
- Different shells can interpret the same text in different ways
- Terminal is the window to the shell
- Commands are case-sensitive

```bash
$ commandName options input

$ echo $PATH

$ which cal
/usr/bin/cal
```

### Redirection - Standard Output

- Data Streams have numbers associated with them
    - 0 - standard input
    - 1 - standard output
    - 2 - standard error

```console
cat 1> output.txt
cat > output.txt
cat 1>> output.txt
cat >> output.txt
```

### Redirection - Standard Error, Standard Input

```console
cat 2>> error.txt
cat -k bla 2>> error.txt

cat 1>> output.txt 2>> error.txt

cat > input.txt

cat < input.txt     // standard input

cat 0< input.txt 1> hello.txt

# On one terminal
cat tty     # /dev/pts/1

# On second terminal
cat 0< input.txt > /dev/pts/1
```

- Standard Input, Standard Output and Standard Error are Data Streams.
- Using redirection you can control where those streams "flow".
- Standard Input = 0, Standard Output = 1, Standard Error = 2.
- > will overwrite a file before writing to it.
- >> will append to what's already there.

## Piping

```console
date 1> date.txt

cut 0< date.txt --delimiter " " --fields 1

date | cut --delimiter " " --fields 1
```

### tee command

```console
date

date | cut --delimiter " " --fields 1
```

```
ls -l | tee file.txt | less
stdout                stdin
```

```console
date | tee fulldate.txt | cut --delimiter " " --field 1

date | tee fulldate.txt | cut --delimiter " " --field 1 > today.txt
```

- tee - take snapshot and continue piping.

### xargs command

- xargs - takes data from standard input and convert to command line arguments.
- echo - does not accept standard input.

```console
date | xargs echo

date | xargs echo "hello"

date | cut --delimiter " " --fields 1 | xargs echo

rm deleteme.txt

# filetodelete.txt
# --> fulldate.txt
# --> today.txt
cat filestodelete.txt | xargs rm
```

**Summary**
- Piping connects STDOUT of one command to STDIN of another.
- Redirection of STDOUT breaks pipelines.
- To save a data "snapshot" without breaking pipelines, use the **tee** command.
- If a command doesn't accept STDIN, but you want to pipe to it, use **xargs**.
- Commands you use with **xargs** can still have their own arguments.

### Aliases

- .filename - hidden file

**.bash_aliases**

```bash
alias getdates='date | tee /home/matikomp/fulldate.txt | cut --delimiter " " --fields 1 | tee /home/matikomp/shortdate.txt | xargs echo hello'
alias calmagic='xargs cal -A 1 -B 1 > /home/matikomp/thing.txt'
```

```console
getdates

echo "12 2017" | calmagic
```

**Summary**

- An alias is a custom nickname for a command or pipeline.
- Aliases go in a **.bash_aliases** file in your home folder.
- alias aliasName="command1 -options args | command2 -options args ..."
- aliases are accessible when you restart your terminal.
-

## Linux filesystem

**The Linux File System**

- The Linux File system follows a tree like structure.
- Everything can be tracked back to the / directory.
- The root user has absolute power of the system.
- /home stores home directories for all regular users on the system.
- /root is the home directory for the root user.

![Alt text](images/linux-filesystem.png "Linux filesystem")

- / - The Very Top (Root) of The File Tree. Holds Everything Else.
- /bin - Stores Common Linux user command binaries, e.g. date, cat, cal commands are in there
- /boot - Bootable linux Kernel and bootloader config fileshttp://192.168.99.100
- /dev - Files representing devices, tty=terminal, fd=floppydisk, (sd or hd) = harddisk, ram=RAM, cd=CD-ROM
- /etc - Administrative Configuration files.
- /home - Where the home directories for regular users are stored.
- /media - Is usually where removable media (USB sticks, external hard drives etc.) are mounted.
- /lib - Contains shared libraries needed by applications in /bin and /sbin to boot the system.
- /mnt - A place to mount external devices. Superseded by /media.
- /misc - A directory used to sometimes automount filesystems on request.
- /opt - Directory Structure used to store additional (i.e. optional) software.
- /proc - Information about System Resources.
- /root - The home folder for the root user aka the superuser (similar to the administrator on Windows).
- /sbin - Contains administrative commands (binaries) for the root (super) user.
- /tmp - Contains temporary files used by running applications.
- /usr - Contains files pertaining to users that in theory don't change after installation.
- /var - Contains directives of variable data that could be used by various applications. System log files are
usually found there.











