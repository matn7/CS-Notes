# Linux

## Networking TCP/IP

- TCP/IP
- Classfull networks
- Subnet masks
- Broadcast addresses
- CIDR
- Private address space

### TCP/IP Networking for Linux System Admin

- TCP/IP
    - Used for network communications
    - TCP - Transmission Control Protocol
    - IP - Internet Protocol
- TCP - controls data exchange
- IP - sends data from one device to another
- Hosts
    - devices on a network that have an IP address

**IP Networking**

- IP address
    - Example: 201.88.129.187
- subnet mask
    - Example: 255.255.255.0
- broadcast address
    - Example: 201.88.129.255

- Network Address
- Host Address
- Each must be unique for proper routing
- Address Classes
    - Used to determine the network address and host address

**Classful Networks**

| Class | Network | Hosts Allowed |
|---|---|---|
| A | 1.0 -> 127.0 Ex: 17.24.88.9 | 16,777,216 |
| B | 128.0 -> 191.255 Ex: 183.194.46.31 | 65,536 |
| C | 192.0.0 -> 233.255.255 Ex: 199.83.131.186 | 255 |


**Subnet Masks**

| Class | Subnet Mask |
|---|---|
| A | 255.0.0.0 |
| B | 255.255.0.0 |
| C | 255.255.255.0 |

**Broadcast Addresses**

| Class | Network | Subnet Mask | Broadcast |
|---|---|
| A | 17.0.0.0 | 255.0.0.0 | 17.255.255.255 |
| B | 183.194.0.0 | 255.255.0.0 | 183.194.255.255 |
| C | 199.83.131.0 | 255.255.255.0 | 199.83.131.255 |

**Classless Inter-Domain Routing / CIDR**

- IP: 121.67.198.94
    - Class A network: 121.0.0.0
    - Class A subnet: 255.0.0.0
    - Class A broadcast: 121.255.255.255
- IP: 121.67.198.94 Subnet: 255.255.255.0
    - CIDR network: 121.67.198.0
    - CIDR subnet: 255.255.255.0
    - CIDR broadcast: 121.67.198.255

**Reserved Private Address Space**

| Class | Range | Private Address Space |
|---|---|---|
| A | 1.0.0.0 - 127.255.255.255 | 10.0.0.0 - 10.255.255.255.255 |
| B | 128.0.0.0 - 191.255.255.255 | 172.16.0.0 - 172.31.255.255 |
| C | 192.0.0.0 - 233.255.255.255 | 192.168.0.0 - 192.168.255.255 |


### Networking - DNS and hostnames

- Determining your IP address
- ip and ifconfig utulities
- hostnames
- DNS and name resolution
- /etc/hosts
- /etc/nsswitch.conf

**Determining your IP address**

```console
ip address

// deprecated
ifconfig
```

**hostnames**

- human-readable name for an IP address
    - webprod01 = 10.109.155.174

**DNS hostnames**

- FQDN - fully qualified domain name
    - webprod01.mycompany.com
- TLD
    - .com, .net, .org, etc.
- Domains
    - below (to the left of) TLD
- sub-domain
    - below (to the left of) the domain
    - webprod01.ny.us.mycompany.com

**Displaying the hostname**

```console
hostname

uname -n

hostname -f
```

**Setting the hostname**

```console
# hostname webprod01

// ubuntu, redhat
# echo 'webprod01' > /etc/hostname

# vim /etc/sysconfig/network
HOSTNAME=webprod01
```

**Resolving DNS Names**

- host
- dig

```console
host www.mycompany.com
```

**The /etc/hosts file**

- Format:
    - IP FQDN alias(es)
    - 10.11.12.13 webprod02.mycorp.com webprod02
- Now we can refer to the host by name.
    - webprod02.mycorp.com OR webprod02
- /etc/hosts is local to your linux system. It does not propagate to the rest of the network.

*/etc/hosts*
```
127.0.0.1       localhost
1.2.1.6         webprod01.mycorp.com webprod01
10.11.13.7      dbcluster
```

**/etc/nsswitch.conf**

- NSS = Name Service Switch
- Controls the search order for resolutions

```
hosts: files dns        // /etc search first
hosts: files nis dns
```

### Networking - DHCP, Dynamic and Static Addressing

- Network ports
- DHCP
- Static IP addresses
- ifup / ifdown
- GUI / TUI tools

**Network Ports**

- When a service starts it binds itself to a port.
- Ports 1 - 1023 are well-known ports.
    - 22 - SSH
    - 25 - SMTP
    - 80 - HTTP
    - 143 - IMAP
    - 389 - LDAP
    - 443 - HTTPS

**/etc/services**

- Maps port names to port numbers

```
ssh     22/tcp      # SSH Remote Login Protocol
smtp    25/tcp      # SMTP
http    80/tcp      # http
ldap    389/tcp     # LDAP
https   443/tcp     # http protocol over TLS/SSL
```

**DHCP**

- Dynamic Host Configuration Protocol
- DHCP servers assign IP address to DHCP clients
    - IP Address
    - netmask
    - gateway
    - DNS servers
- Each IP is "leased" from the pool of IP addresses the DHCP server manages.
    - The lease expiration time is configurable on DHCP server. (1hr, 1day, 1week, etc.)
    - The client must renew the lease if it wants to keep using the IP address. If no renewal is received, the IP
    is available to other DHCP clients.

**Configuring a DHCP Client -RHEL**

```
ifconfig -a or -ip link
```

```
/etc/sysconfig/network-scripts/ifcfg-DEVICE
/etc/sysconfig/network-scripts/ifcfg-eth0
/etc/sysconfig/network-scripts/ifcfg-enp5s2
BOOTPROTO=dhcp
```

**Configuring a DHCP Client - Ubuntu**

```
/etc/network/interfaces

auto eth0
iface eth0 inet dhcp
```

**Assigning a Static IP Address - RHEL**

- /etc/sysconfig/network-scripts/ifcfg-eth0

```
DEVICE=eth0
BOOTPROTO=static
IPADDR=10.109.155.174
NETMASK=255.255.255.0
NETWORK=10.109.155.0
BROADCAST=10.109.155.255
GATEWAY=10.109.155.1
ONBOOT=yes
```

**Assigning a Static IP Address - Ubuntu**

- /etc/network/interfaces

```
auto eth0
iface eth0 inet static
        address 10.109.155.174
        netmask 255.255.255.0
        gateway 10.109.155.1
```

**Manually Assigning an IP Address**

- Format:
```
ip address add IP[/netmask] dev NETWORK_DEVICE

ip address add 10.11.12.13 dev eth0

ip address add 10.11.12.13/255.255.255.0 dev eth0

ip link set eth0 up
```

```
ifconfig NETWORK_DEVICE addr netmask SUBNET_MASK

ifconfig eth0 10.11.12.13
ifconfig eth0 10.11.12.13 netmask 255.255.255.0

ifconfig eth0 up
```

**ifup / ifdown**

- Can be used instead of ifconfig / ip
- Distribution dependent
- Uses configuration files
- Examples:

```
ifup eth0
ifup enp5s2
ifdown eth0
ifdown enp5s2
```

**GUI / TUI Tools**

- RedHat
    - nmtui
    - system-config-network
- SUSE
    - YaST
- Ubuntu
    - No official tool available

### Network Troubleshooting

- ping
- traceroute / tracepath
- netstat
- tcpdump
- telnet

**Testing Connectivity with Ping**

- Uses ICMP
```
ping HOST
ping -c COUNT HOST
```

```console
ping -c 3 google.com
ping -c 3 10.0.2.2
```

**traceroute**

```console
tracerout -n google.com
```

**tracepath**

```console
tracepath -n google.com
```

**The netstat Command**

- -n    Display numerical address and ports.
- -i    Displays a list of network interfaces.
- -r    Displays the route table. (netstat -rn)
- -p    Display the PID and program used.
- -l    Display listening sockets. (netstat -nlp)
- -t    Limit the output to TCP (netstat -ntlp)
- -u    Limit the output to UDP (netstat -nulp)

```console
netstat -l

netstat -rn

sudp netstat -ntlp
```

**Packet sniffing with tcpdump**

tcpdump
- -n    Display numerical addresses and ports.
- -A    Display ASCII (text) output.
- -v    Verbose mode. Produce more output.
- -vvv  Even more verbose output.

```console
sudo tcpdump
sudo tcpdump -Anvvv
```

```
telnet HOST_OR_IP PORT_NUMBER
```

```console
telnet google.com 80
```

***

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

### Navigating the File System

```console
pwd
ls
ls -F
ls -l
ls -lh

cd /home/matikomp/Downloads
cd ~/Downloads
cd Downloads

ls -a
```

### File Extensions

```console
file today.txt
```

### Wildcards

- Wildcards are used to build patterns called "regular expressions"
- Anything that matches the pattern will pass as a command linee argument to a command
- * matches anything, regardless of length
- ? matches anything, but just for one place
- [] matches just one place, but allows you to specify options

```console
ls Documents/ Downloads/ Pictures/

// list contant of all folders
ls *

ls D*

// list only files in txt format
ls *.txt

// Match A.txt, B.txt etc.
ls ?.txt

// Match AA.txt, BB.txt etc.
ls ??.txt

// [] match letters or nums listed
// match file1.txt, file2.txt
ls file[1234567890].txt
ls file[0-9].txt

// match fileA.txt, fileB.txt
ls file[A-Z].tzt

// match file9Aa.txt
ls file[0-9][A-Z][a-z].tzt

// match file9a.txt
ls file[0-9abc].txt
```

### Creating Files and Directories

```console
touch file1
echo "hello" > hello.txt

// create entire path of folders
mkdir -p thing/more/things
```

- create 100 files
- braces expansion

```console
mkdir {jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec}_{2017,2018,2019,2020,2021,2022}
mkdir {jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec}_{2017..2022}

touch {jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec}_{2017..2022}/file{1..100}

ls {jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec}_{2017..2022}

touch file{A..C}.txt
```

- mkdir -p can be used to create entire folder paths
- Don't put spaces in your filenames. User underscores instead

### Deleting Files and Folders

```console
rm file1 Documents/file2.txt Downloads/file3.txt

rm *.txt
rm file*
rm *[2,3]*

rm -r Linux
touch delfolder/deleteme{1,2,3}/file{1,2,3}
rm -r delfolder
rm -ri delfolder

mkdir -p delfolder/deleteme{1,2,3}
touch delfolder/deleteme{1,2,3}/file{1,2,3}

mkdir -p delfolder/folder{1..3}
touch delfolder{1,2}/file{1..10}.txt

rmdir delfolder/*
```

### Copying Files and Folders

```console
cp file1.txt file2.txt destination/
rm file*
cp destination/* .

cp -r source/ destination/
```

### Moving and Renaming Files and Folders

```console
mv oldname.txt newname.txt
mv oldfolder/ newfolder/
mv newfolder/* .

mv file* newfolder/
mv newfolder/ ~/Documents/
mv ~/Documents/newfolder

mv ~/Documents/newfolder/ ./jackpot
```

### The Locate Command

- The locate command searches a database for files and returns a list of results
- Using a database is very fast, but requires updating.
- To help protect against errors you can use the --existing and --follow options.
- Best thing to do is update the database (done automatically daily).
- This requires admin privileges and therefore the sude command is used.

```console
locate *.conf
locate -i *.CONF
locate -i --limit 3 *.conf
locate -S
locate -e *.conf

locate --follow *.conf

touch findme.txt
locate findme.txt
updatedb

sudo updatedb
```

### The find command

- The find command can be used to execute sophisticated search tasks.
- You can search by -name, -type, -size and more.
- You can use the -exec option to execute another command on each of the results - remember to end with \;

```console
find /
find /etc

find . -maxdepth 1

// find only files
find . -type f

// find only directories
find . -type d

find . -maxdepth 1 -type d

find . -name "5.txt"

find / -type f -size +100k
sudo find / -type f -size -100k -o -size -5M | wc -l

find -iname "*.TXT"

mkdir copy_here
sudo find / -type f -size +100k -size -5M
sudo find / -type f -size +100k -size -5M | wc -l

sudo find / -type f -size +100k -size -5M -exec cp {} ~/Desktop/copy_here \;

sudo find / -maxdepth 3 -type f -size +100k -size -5M -exec cp {} ~/Desktop/copy_here \;

// asks for confirmation
sudo find / -maxdepth 3 -type f -size +100k -size -5M -ok cp {} ~/Desktop/copy_here \;

mkdir haystack
mkdir haystack/folder{1..500}
touch haystack/folder{1..500}/file{1..100}

touch haystack/folder$(shuf -i 1-500 -n 1)/needle.txt

// find needle
find haystack/ -type f -name "needle.txt" -exec mv {} ~/Desktop \;
```

### Viewing Files

- cat - concatenate

- You can join files together using the cat command.
- Reverse files vertically using tac command.
- Reverse files horizontally using the rev command.
- Use less to page through large amounts of output.
- Use head and tail to cut out just what you want.

```console
cat file1.txt
// hello
cat file2.txt
// there

cat file1.txt file2.txt > hello_there.txt
cat file[1-5].txt > hello_there.txt

// cat - works also for mp3 format and so on

echo "abc" > alpha.txt
echo "def" >> alpha.txt
cat alpha.txt
// abc
// def

tac alpha.txt
// def
// abc

cat file[1-5].txt | tac > reversed.txt

tac myfile.mp3 > myreversedfile.mp3

// reverse content of each line
cat file[1-5].txt | rev

cat file[1-5].txt | rev | rev

sudo find / -maxdepth 4 -name "*.conf" -size +20k
cat /etc/brltty.conf

less /etc/brltty.conf

cat /etc/brltty.conf | less

find | less

// see first 3 lines
cat file[1-5],txt | head -n 3

find | head -n 5
find | head -n 5 | tac

cat /etc/brltty.conf | wc -l
head -n 20 /etc/brltty.conf

cat file[1-5].txt | tail -n 2

head -n 20 /etc/brltty.conf | tail -n 3

find | tail -n 3 > filtered.txt
```

### Sorting data

- You can sort data using the sort command.
- The sort command tends to sort "smallest first" (a-z, 0-9 etc)
- You can reverse this using the -r option.
- To sort numerically give the -n option.
- To only provide unique results, use the -u option.

- You can sort tabular data using the -k option.
- You need to give the -k option a KEYDEF.
- 3nr means sort using column 3, and use the -n and -r option.
- To sort human readable data use the -h option.
- To sort month data use the -M option.
- Order of options in the KEYDEF don't matter.

```console
sort words.txt

sort words.txt > sorted.txt
sort words.txt | tac

sort -r words.txt | less

sort -n numbers.txt | less
sort -nr numbers.txt | less

// unique
sort -u numbers0-9.txt | less

// sort tabular data
ls -l /etc | head -n 20
ls -l /etc | head -n 20 | sort -k 5nr
ls -lh /etc | head -n 20 | sort -k 5nr

ls -lh /etc | head -n 20 | sort -k 5hr

// sort by month
ls -lh /etc | head -n 20 | sort -k 6M

ls -lh /etc | head -n 20 | sort -k 2n
ls -lh /etc | head -n 20 | sort -k 2nr
```

### Searching File Content

- grep is used to search data for certain text.
- grep returns lines that contain a piece of text (wildcards too).
- The -c option will return the line count.
- The -i option will search in a case insensitive manner.
- The -v option will invert the search.
- grep workd great with piped data.

```console
cat hello.txt
grep e hello.txt

grep e hello.txt | wc -l
grep -c e hello.txt

grep Gadsby gadsby_manuscript.txt
grep -i gadsby gadsby_manuscript.txt
grep -ic a gadsby_manuscript.txt

// how many time used letter e
grep -ic e gadsby_manuscript.txt

grep -ic "our boys" gadsby_manuscript.txt

wc -l gadsby_manuscript.txt
// line without letter e
grep -vc e gadsby_manuscript.txt

grep -i "e" gadsby/gadsby_manuscript.txt hello.txt
grep -ci "e" gadsby/gadsby_manuscript.txt hello.txt

touch hello/file{1..100}
ls hello/

ls hello/ | grep hello.txt

ls -lF / | grep root
ls -F /etc | grep -v /
ls -F /etc | grep -v / | sort -r > files.txt

man -k print | grep files
```

### File Archiving and Compression

- Tarballs are containers to store files in for compression.
- Tarball can be compressed using various compression algorithms.
- gzip and bzip2 are common options on Linux. xz is another option.
- You can also use the zip and unzip commands to create/extract .zip files.

```
1) make tarball
2) compress tarball
```

```console
ls -lh

// c - create new archive
// v - verbose, display output
// f - accept files
tar -cvf ourarchive.tar file[1-3].txt
ls -lh | grep .tar
file ourarchive.tar

// x - extract archive
tar -xvf ourarchive.tar

// list content
tar -tf ourarchive.tar
```

```
gzip - faster compression, less power
bzip2 - compress to smaller size, require more power
```

```console
gzip ourarchive.tar
gunzip ourarchive.tar.gz

bzip2 ourarchive.tar

zip ourthing.zip file1.txt file2.txt file3.txt

// z - compress using gzip
tar -cvzf ourarchive.tar file[1-3].txt
file ourarchive.tar.gz

// j - compress using bzip2
tar -cvjf ourarchive.tar file[1-3].txt
file ourarchive.tar.gz

// extract
tar -xvzf ourarchive.tar.gz
tar -xvjf ourarchive.tar.bz2
```

***

## Task Automation

### Bash Scripts


























