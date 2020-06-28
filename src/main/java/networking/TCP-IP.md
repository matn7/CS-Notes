# TCP/IP

## What is TCP/IP system

**What is a difference between standard and protocol implementation**

- Standard is a set of rules. Implementation is software that works based of these rules
that allows connect computer to network.

**Why data verification is crucial on end devices in ARPAnet**

- By definition network should not be controlled by any centralized system. This is a reason
why sending and receiving computers are responsible for data verification.

**Why in large network there is name translate**

- IP Addresses are difficult to memorize and easy to type mistakely. Domain names are easier to memorize
DNS system is used to bind words with IP addresses.

***

- What is network protocol?
    - Is a set of rules and data formats that allows communication between computers (or other devices)
    in a network.
- What two features of TCP/IP standard allows computers to work in decerntralized network?
    - Data verification on end devices and dynamic routing.
- What system is used to bind domain names witj IP addresses?
    - DNS (Domain Name System)
- What is RFC?
    - RFC (Request for Comment) is a official document that contains standard descriptions obligatory in Internet 
    or report made by task force team that regulates Internet working.
- What is PORT?
    - Is an logical channel that is used to sending data to proper application.
    
***

- **Physical Address** - address bind to network card. In case of Ethernet network address is bind during
production process.
- **IP Address** - logical address used to identify computer or other devices (e.g. printer) in network.
- **Logical Address** - network address binded using software that implements network protocol.
- **ARPAnet** - experimental network where TCP/IP standard was born.     
- **Gate** - router connects LAN network with larger network. At the when in LAN network companies
own protocols (AppleTalk, NetBEUI, Novell IPX/SPX) was used, gate sometimes was referred to router that perform
protocol conversions.
- **Protocol Implementation** - softwer that implements set of rules in communication specified by protocol.
- **Domain Name** - name bind with IP address by DNS system.
- **Port** - logical channel, interface between application and transport layer in TCP/IP model.
- **Network protocol** - set of rules that describes specified aspects of computer communication process.
- **RFC (Request for Comment)** - is a official document that contains standard descriptions obligatory in Internet 
or report made by task force team that regulates Internet working.
- **Router** - network devices that is used to send data to destination based on logical address. Router is also
used to split large network into smaller sub-networks.
- **LAN network (Local Area Network)** - small network that include office, institution or home, that exists in
one specific place.
- **System or protocol set** - system of binded together standards and procedures (protocols) that allows
computer to communicate over network.
- **TCP/IP** - set of network protocols used in Internet and other networks around the world.
- **Domain Name Service** - service that binds human descriptive names wih network addresses. Computer that
performing Name translation is called **name server** and process changing names to corresponding addresses
is **name translate**.
- **Own protocol** - communication technology developed by private company, institution etc.

## How TCP/IP works

![TCP/IP model protocol layers](images/tcp-ip-layers.png "TCP/IP model protocol layers")

### TCP?IP and OSI model

![7 Layers OSI model](images/tcp-ip-osi.png "7 Layers OSI model")

### Data packets

![Data packets](images/data-packet.png "Data packets")

**Simplified TCP/IP**

![Simplified TCP/IP model](images/simplified-tcp-ip-model.png "Simplified TCP/IP model")

***

**What is the main advantages of modular structure of TCP/IP protocols**

- Thanks of modular structure TCP/IP can be easily adapt tospecific device or OS.
Each system layer can be modified independently.

**What is a purpose of Data Access Layer**

- Offers services specific to physical network layer, including preparing , sending and receiving 
fromes from transmission medium (like Ethernet cable). 

**Which Layer from OSI model corresponds to network layer in TCP/IP model**

- Network Layer from TCP/IP model corresponds to Network Layer in OSI model.

**Why header is added to data in each TCP/IP model layer**

- Headers are added in source computer as in each layer in destination computer there are
information needed to proces those incomming data.

***

- Which 2 layers OSI model corresponds data access layer in TCP/IP?
    - Data Access, Physical Layers.
- Which layer in TCP/IP model is responsible for sending data from one network to another?
    - Network Layer.
- What are advantages and disadvantages of UDP over TCP protocol?
    -  UDP is simpler than TCP, but it does not contains transmission error detection and data control.
- What is data encapsulation in model layer?
    - Adding some data to headers specific to layer before carry over to another layer.
    
***

- Exchange operations executed in each TCP/IP model layer:
    - **Access Layer** - contains procedures support physical access to network.
    - **Network Layer** - contains procedures to logical data addressation and datagrams routes.
    - **Transport Layer** - contains procedures to transmission error detection, flow control and 
    data acknowledgement.
    - **Application Layer** - contains tools to network diagnostics, to send files to remote devices control
    and other network operations. This Layer contains API interfaces that allows program that works in OS use 
    network.
- Exchange layers, in which datagrams are created:
    - In Transport and Network Layer.
- How can TCP/IP standard be adapted to new versions of network cards:
    - Only network layer should be adapted.
- What reliable data transmission using TCP protocol means:                   
    - Means that TCP protocol is used, that allows detect errors in data transmission and receiving 
    acknowledgement if possible.

***

- ARP (Address Resolution Protocol) - protocol binds logical addresses with physical addresses.
- Datagram - data packet transfer between Network Layer and Access Layer or packet processed by UDP protocol
in network and transport layer.
- IP (Internet Protocol) - network layer protocol implementing logical data addresses and routing.
- Statement - data packet transmitted between application layer and transport layer. Data chunk send
over network from one device to another.
- Header - information added to data packets in each system layer.
- Frame - data packet created in Access Layer.
- Segment - data packet created by using TCP protocol in transport and network layers.
- TCP - reliables, connectionless transport layer protocol.
- UDP - connectionless transport level protocol, does not provide reliable data transfer.
- Application Layer - layer that controls network applications, interface for OS.
- Data Access Layer - interface of physical network.
- Network Layer - logical data addressing and routing.
- Transport Layer - data transmission errors are detected and data acknowledged. Interface for network
applications.

## Data Access Layer

**What kind of services are defined in data access layer?**

- Services and specifications connected to maintaining access to physical network.

**Which OSI layer corresponds to data access in TCP/IP model?**

- Data Layer, Physical Layer

**Which LAN technology is most popular?**

- Ethernet, in wireless WiFi.

**What CSMA/CD means?**

- CSMA/CD means co-sharing access to transmission medium with collision detection. Used in Ethernet.
Computers connected to network waits for proper moment to send data, if 2 computers send data simultaneously
then transmission is stopped, wait random number of time and then retry transmission. 

***

- What CRC means?
    - Cyclic Redundancy Check is a control sum used to check whether data in frame was not corrupted
    during data transfer.
- Describe collision detection in Ethernet?
    - Collision occurs when two computers send data at the same time. When computers states that 
    collision occured, that means collision was detected.
- What is a size of physical address field in Ethernet frame?
    - 48 bits.
- What is a role of ARP protocol?
    - Is used to bind physical addresses with logical addresses.
    
***

- Exchange two protocols that binds physical with addresses with IP addresses.
    - ARP and RARP.
- Exchange three network technologies.
    - Ethernet, 801.11 (WiFi), 802.16 (WiMax)
- Describe function MAC and LLC layers in OSI model.
    - MAC layer handle network layer. In LCC layer error in fromes received from network are detected and 
    connection between devices are supported. 

***

- **MAC Address** - address that identifies network card. In case of Ethernet address is binded to card
at production step, in modern network card there is possiblility to configure this address.
- **CRC** - control sum used to verify valid data in frame.
- **CSMA/CD** - medium access method used in Ethernet technology.
- **Ethernet** - popular technology in LAN network, use CSM/CD method to medium access.
- **FCS (Frame Check Sequence)** - field in Ethernet frame that contains CRC used to verify validity of data.
- **LLC (Logical Link Control)** - sublayer in data link layer in OSI model, responsible for detecting
errors in transmission and maintaining connections between network devices.
- **MAC** - sublayer in data link layer in OSI model, used to support network card.
- **Access Control** - procedures that describes access to transmission medium.
- **Preamble** - bits sequence indicating start of frame.
- **Network Technology** - network physical specificaion, that inculde medium access methods, data frame 
format and type of cable.
- **Physical Layer** - first layer in OSI model, is used to process data frame into sequence of bits
adapted to transmit over medium (cable).
- **Data Link Layer** - second layer of OSI model.
 
## Network Layer

![IP network-host](images/ip-network-host.png "IP network-host")

| Class | Binary address start | First octet range | Disabled Address range |
|---|---|---|---|
| A | 0 | 0 to 127 | 10.0.0.0 to 10.255.255.255 / 127.0.0.0 to 127.255.255.255 |
| B | 10 | 128 to 191 | 127.16.0.0 to 127.31.255.255 |
| C | 110 | 192 to 223 | 192.168.0.0 to 192.168.255.255 |

**What data format is used to simplify 32-bit binary IP address?**

- Decimal format.

**What info about IP address delivers ARP protocol?**

- About assigned to it physical address (MAC).

**What statement sends router to source computer, when receive to much data?**

- *Source Quench*

**To which IP class belongs address with 3 first bits 110?**

- C Class

***

- What is a role of TTL field in IP header?
    - Is used to calculate routers before remove IP datagram. Purpose is to avoid processing data
    in infinite loop inside network.
- What size have network id and host id in A Class address?
    - Network 8 bits, host 24 bits.
- What it is octet?
    - Is 8 bits of data (byte).
- What is IP address?
    - Is used to identify computer interfaces or network devices.
- What is difference between ARP and RARP protocol?
    - ARP assigns IP address with physical address. RARP assigns physical addresses with IP addresses.
    
***

| 128 | 64 | 32 | 16 | 8 | 4 | 2 | 1 | Res |
|---|---|---|---|---|---|---|---|---|
| 0 | 0 | 1 | 0 | 1 | 0 | 1 | 1 | 43 |
| 0 | 1 | 0 | 1 | 0 | 0 | 1 | 0 | 82 |
| 1 | 1 | 0 | 1 | 0 | 1 | 1 | 0 | 214 |
| 1 | 0 | 1 | 1 | 0 | 1 | 1 | 1 | 183 |
| 0 | 1 | 0 | 0 | 1 | 0 | 1 | 0 | 74 |
| 0 | 1 | 0 | 1 | 1 | 1 | 0 | 1 | 93 |
| 1 | 0 | 0 | 0 | 1 | 1 | 0 | 1 | 141 | 
| 1 | 1 | 0 | 1 | 1 | 1 | 1 | 0 | 222 |

**Dec to Bin**

| Num | Res | Reminder | Bin |
|---|---|---|---|
| 238 / 2 | 119 | 1 | 0 |
| 119 / 2 | 59 | 1 | 1 |
| 59 / 2 | 29 | 1 | 1 |
| 29 / 2 | 14 | 1 | 1 |
| 14 / 2 | 7 | 0 | 0 |
| 7 / 2 | 3 | 1 | 1 |
| 3 / 2 | 1 | 1 | 1 |
| 1 / 2 | 0 | 1 | 1 |

| 128 | 64 | 32 | 16 | 8 | 4 | 2 | 1 | Res |
|---|---|---|---|---|---|---|---|---|
| 1 | 1 | 1 | 0 | 1 | 1 | 1 | 0 | 238 |

***

- **ARP** - is used to ge physical address corresponding to IP address. Protocol is used to create 
address IP and physical addresses table pair.
- **BOOTP** - protocol used to load computer or other network device operating system from different
computer.
- **Decimal format with dot** - format used in IP addresses represents four octetc decimal number separated
by dots (209.121.131.14).
- **ICMP** - used in router to send to source computers information about problem with sending data.
Used in `ping` command to check status of chosen computer in network.
- **Host id** - part of IP address that identifies computer in network.
- **Network ID** - part of IP address that identifies network.
- **IP** - network layer protocol, used to process IP addresses, sending data and laying out
transmission paths.
- **Address Class** - system to qualify IP addresses. Defines way to separate IP address to host and network id.
- **Multicast** - technique to simultaneous sending datagrams to specified group of computers.
- **Octet** - eight bit binary number.
- **Sub network** - logical part of address space in TCP/IP standard.
- **RARP** - protocol used to determine computer IP address based on physical address. Used in computer
without disks, in whcih OS is loaded from different computer used software saved in memory
PROM network card.

## Sub-networks CIDR

![Class A](images/class-a.png "Class A") 

![Sub networks](images/sub-networks.png "Sub networks") 

**Adress IP / subnetwork mask pair**

![Pair Address IP/sub-net mask](images/address-ip-sub-net-mask.png "Pair Address IP/sub-net mask") 

**Class and Class less network bits allocation**

![Network - sub network bits](images/network-sub-network.png "Network - sub network bits") 

**Datagram send in network separated by sub-networks**

![Network -  Sub-Network](images/send-datagram-sub-network.png "Network -  Sub-Network") 

**Sub Network C Class**

![Sub Network C Class](images/subnetworks-c-class.png "Sub Network C Class") 

***

**What length in bits has sub-network identifier in address B class with mask 255.255.0.0**

- 0 bits we are not able to create sub-network id. Msk 255.255.0.0 is default mast for class B.
Network identifier occupies all 16 bits and we cannot create sub-net identifier.

**Administrator decided that mask will contains of 21 bits. What is a decimal format of this mask?**

- 11111111 11111111 11111000 00000000 = 255.255.248.0

**B class network separated by 10 localizations 12 components each. What mask will be needed to
address all computers?**

- In mask 255.255.255.240 there are 4 bits to identify host, which are enough to address all users.

**Administrator needs three bits to separate addresses in class A on sub-networks. Which mask should be
used?**

- Three first bits of second octets are 128 + 64 + 32 = 224. Mask should be 255.224.0.0.

**What is a range of IP addresses with CIDR 212.100.192.0/20?**

- First address has format: 11010100.01100100.11000000.00000000.
- Last address has format: 11010100.01100100.11001111.11111111.
- Answer: 212.100.192.0 to 212.100.207.255

***

- From where we get bits that identifies sub-network?
    - Are taken from host identifier.
- Why separate network on sub-networks is not as essential as before?**
    - Because separation on sub-networks is defined in CIDR addressing.
- What classless in CIDR addressing means?
    - Means that traditional classes (A, B, C and D) are not in use, as CIDR prefix replaces them. 
- How many hosts addresses we can create with mask /26?
    - 2 ^ 6 - 2 = 62 host addresses.
- What is a term used to describe couple of smaller sub-network into larger network?
    - Creating supernetwork.

***

- Describe with CIDR notation ip range from 180.4.0.0 to 180.7.255.255.255.
    - 180.4.0.0/14
- How many hosts we can define in sub-network od IP 192.100.50.192 and mask 255.255.255.224.
    -   Sub-network identifier consists of 3 bits, so host identifier is 5 bits. 2^5 - 2 = 30 addresses.
- How many subnetworks we can define with address and mask from previous point?
    - Sub-network identifier consists of 3 bits. 2 ^ 3 - 2 = 6.  
- What is the first address of host in sub-network 195.50.100.0/23.
    - 195.50.100.1
- What is the last address of host in sub-network 195.50.100.0/23.        
    - 195.50.101.254
    
***

- **CIDR** - technique allows define sub-network block creating one whole.
- **Sub-network mask** - 32 bits binary value in which couple of hosts bits are used to identify sub-network.
- **Super-network mask** - 32 bits binary value, that groups couple sub-network id in one whole.
- **Sub-network** - logical part of address space defined by network identifier.    












