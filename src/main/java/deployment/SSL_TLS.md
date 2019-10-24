# SSL TLS

```console
nslookup www.instagram.com
```

***

## Encryption and hashing

### Encryption algorithms

| algorithm | key |
|---|---|
| DES | Symmetric |
| 3DES | Symmetric |
| AES | Symmetric |
| RSA | Asymmetric |

### Hash

```
+------+                                 +------+
| Data | ---> one way hash function ---> | Hash |
+------+                                 +------+

Variable length                      Fixed length
                                     128 bits
                                     160 bits
                                     ...
```

- Verifies integrity of data
- Keys in hash function add sender authentication
- One way algorithm

### MD5 hashing algorithm

```
   128 bit
+---------+     +---------+     +----------+
|   MD5   |     |   SHA   |     |   HMAC   |    Can be used with MD5 and SHA
+---------+     +---------+     +----------+

                - SHA-1     160 bit
                - SHA-256   256 bit
                - SHA-512   512 bit
```

### SHA hashing algorithm

- SHA-1 (160 bit) 40 hex characters
- SHA-256 (256 bits) 64 hex characters
- SHA-512 (512 bits) 128 hex characters

- HMAC: Data + Key = Hash

### Asymmetric Keys

- Private Key
    - Always kept secret
- Public Key
    - Available for anyone

- Public, Private key can be used for encryption
- Signing data using private key
- Sign and verify signature using asymmetric keys

### RSA

- RSA - public-key cryptosystem
- Key length (bits)
    - 1024
    - 2048 *
    - 3072
    - 4096

### PKI - Public Key Infrastructure

- CA - certification authority
    - Sign certificate
    - Delegate certificate signature (intermediate CA)
    - Stores public key of owner of certificate

### Certificate

```
    CERTIFICATE
- Information about owner
- Information about issuer
- Signature
- PUBLIC KEY
```

- If certificate is signed by CA (certification authority) and we truct CA - we trust owner of
the certificate too.
- Public key in certificate ALWAYS belongs to the owner of certificate.

***

## Open SSL

```console
openssl genrsa

openssl genrsa -aes256
// Enter pass phrase:   # used for encryption PK

openssl genrsa -des3

openssl genrsa -aes256 -out private.pem

// Extract Public Key
openssl rsa -in private.pem -outform PEM -pubout -out public.pem

openssl genrsa 4096
```

### Chain of thust

```
+------------------------------------------------+
|    Root CA                                     |
|                                                |
|    Owner Info *     Public Key      Private Key |
|    Issuer Info +                       |    |   |
|                                       |    |   |
|    Signature<-------------------------+    |   |
|                                            |   |
|                                            |   |
|    Self-Signed certificate                 |   |
+--------------------------------------------+---+
                                             |
  +------------------------------------------+
  |
+-+----------------------------------------------+
| |  Intermediate CA                             |
| |                                              |
| |  Owner Info %    Public Key      Private Key |
| |  Issuer Info *                           |   |
| |                                          |   |
| +->Signature                               |   |
|                                            |   |
|                                            |   |
|    Signed by Root CA                       |   |
+--------------------------------------------+---+
                                             |
  +------------------------------------------+
  |
+-+----------------------------------------------+
| |  End user                                    |
| |                                              |
| |  Owner Info &    Public Key      Private Key |
| |  Issuer Info %                               |
| |                                              |
| +->Signature                                   |
|                                                |
|                                                |
|    Signed by Intermediate CA                   |
+------------------------------------------------+

Issuer Info & (In End user) must match Owner Info & (In Intermediate CA)

Signature - SHA hash with RSA Encryption
```

- How end user certificate was securely signed by private key of the Intermediate CA ?
    -  Signing occurs on the Intermediate CA server. With it's private key it securely signes CSR
    (Certificate Signing Request) received from the end user

### Verify chain of certificates

- Web server sends own certificate and certificates of all intermediate CAs
- Current date and time should fall within the certificate validity interval
- Verification of signature
- If signature was verified successfully trust between Intermediate CA and End user is established

- Next **Intermediate CA certificate** signature must be verified
- Find Certificate with Owner Info equal to Issuer Info in the Intermediate CA Certificate
- Root CA certificate is found in the builtin OS certificates store
- Verify Intermediate CA certificate signature using public key found in the Root CA certificate
- If signature was verified successfully trust between Root CA aand Intermediate CA is established

- Trust Root CA > Trust Intermediate CA > Trust End User
- Identity of End User (for example Web Server) was successfully verified
- Certificate is valid

### Verifying SSL certificate and certificates chain

```
geocerts.com
sslshopper.com
```

### PKI Public Key Infrastructure

- Set of rules, algorithms and protocols that allows you to build relationships between entities.
- Every entity owns public key, and based on build trust to public keys
- Each Certificate contains
    - Information about owner
    - Information about issuer
    - Signature
    - PUBLIC KEY

### Certificate domain scopes

- Single domain
    - www.instagram.com
    - www.mywebsite.com
- Wildcard
    - *.google.com
    - *.mywebsite.com
- Multidomain
    - *.facebook.com
    - *.fb.com
    - *.messenger.com

***

## SSL, TLS and HTTPS

- SSL - Secure Socket Layer
- TLS - Transport Layer Security
- SSL and TLS cryptographic protocol

- Certificate doesn't depend on specific protocol (TLS or SSL) and could be used for both

### History of SSL and TLS

```
SSL 3.0         TLS 1.1                     TLS 1.3
Deprecated      Not recommended for use     Recommended for use

SSL 2.0
Deprecated      TLS 1.0                     TLS 1.2
                Not recommended for use     Actively used
SSL 1.0
Deprecated
```

- Why RSA is not used in HTTPS for data encryption?
    - RAS encryption is slow
    - Bi-directional data encryption requires RSA key pairs on both sides (private key in client and server)

**Encryption using asymmetric keys**

```
+---------------+          +-----------------------------+
| +-------+     |          | +----------+ OWNER has      |
| | Data  |     |          | | Data     | Public/Private |
| +---+---+     |          | +----------+   Key          |
|     |         |          |          A                  |
| Encryption    |          |      Decryption             |
|     |  Public |          | Private  |                  |
|     V  Key    |          | Ket      |                  |
| +---+-------+ |          |  +-------+---+              |
| | Encrypted | |          |  | Encrypted |              |
| | Data      +-+----------+->+ Data      |              |
| +-----------+ |          |  +-----------+              |
+---------------+          +-----------------------------+
```

### How TLS session is established

**Establishing TLS session**

```
+-------------------+           +------------------------+
|   Web Browser     |           |   Web Server           |
|       +-----------------------------------+            | Cipher Suite:
|       |       Negotiate Cipher Suite      |            | - Set of protocol used in TLS communication
|       +-----------------------------------+            | - Specify how symmetric key will be generated
|                   |           |                        | - Which algorithm will be used for data encryption and
|       SSL/TLS  <--+-----------+--- SSL/TLS             |   decryption
|     Certificate   |           |   Certificate          | - Info about hashing protocol
|                   |           |                        |
|       Verify      |           |                        |
|       Server      |           |                        |
|       Certificate |           |                        |
|                   |           |                        |
|     +--------------------------------------------+     |
|     | Generate symmetric key for data encryption |     |
|     +--------------------------------------------+     |
|                   |           |                        |
|     +--------------------------------------------+     |
| Key |     Send/Receive encrypted data            | KEY |
|     +--------------------------------------------+     |
|                   |           |                        |
+-------------------+           +------------------------+
```

- Certificate verification
    - Verify signature
    - Check validity period
    - Verify whether certificate was revoked or not (optional)

### Cipher Suite

```
TLS_ECDHE-RSA-WITH_AES_128_GCM_SHA256
```

**Delivering key for Encryption in TLS (without Diffie Hellman)**

- RSA key pair is "static" and is not changed until certificate is renewed

![Alt text](src/main/resources/deliver_key.png?raw=true "No Diffie Hellman")


**Delivering key for Encryption in TLS (with Diffie Hellmen)**

![Alt text](src/main/resources/deliver_key_diffie_hellman.png?raw=true "With Diffie Hellman")



















