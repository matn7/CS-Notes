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

### Chain of trust

![Alt text](images/Chain-of-trust.png "Chain of trust")

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

![Alt text](images/Encryption-using-asymmetric-keys.png "Encryption using asymmetric keys")

### How TLS session is established

**Establishing TLS session**

![Alt text](images/Establishing-TLS-session.png "Establishing TLS session")

```
Cipher Suite:
- Set of protocol used in TLS communication
- Specify how symmetric key will be generated
- Which algorithm will be used for data encryption and
decryption
- Info about hashing protocol
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

![Alt text](images/deliver_key.png "No Diffie Hellman")


**Delivering key for Encryption in TLS (with Diffie Hellmen)**

![Alt text](images/deliver_key_diffie_hellman.png "With Diffie Hellman")


**Elliptic Curve Diffie Hellman Exchange**

![Alt text](images/eliptic_curve_diffie_hellman_exchange.png "Key exchange")

```
Cipher Suite: TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256

TLS
ECDHE - elliptic curve Diffie Hellman exchange
ECDSA_WITH_CHACHA20 - elliptic curve digital sign algorithm

POLY1305 - encryption algorithm
SHA256 - hashing algorithm
```

## TLS secured website

- Domain
- Hosting
- TLS Certificate

***

# SSL/TLS Operations

- How SSL works?
- Structure of SSL Certificate
- Encryption Algorithms
- SSL Communication
- Certificate provisioning
- OpenSSL - Library and CLI
- Chain of trust
- Trust stores
- TLS 1.3

**Why do we need SSL certificates?**

- Source identity verification
    - Trust
- Secure against Man-in-the-middle (MITM)
    - Confidentiality & Integrity
- Client/Server authentication
    - Can server identify the client back as well?
- Non-Repudiation
    - can site repudiate (deny) the actions later?

**SSL/TLS Evolution**

- SSL Protocol was developed by Netscape in 1994
- Taher Elgmal & Kipp Hickman
- SSL certificate is produced as proff of identity in SSL protocol
- Transport Layer Security (TLS) protocol was created by Internet Enginerring Task Force (IETF) as
successor to SSL

## How SSL works ?

![How SSL works](images/how-ssl-works.png "How SSL works")

## CA Signed vs Self Signed Certificates

| CA Signed Certs | Self Signed Certs |
|---|---|
| Signed by CA, a third party | Signed by the Website, the owner herself |
| Ideal for public use | Ideal for closed access |
| Trusted by browsers that have the root certificate of the CA in their trust store | Not trusted by browsers until you import your public key manually into browser trust store |
| Renewals / modifications in cert do not require change at browser side | Renewals / modifications requires re-import of the new cert to browser's trust store |
| You buy it from a CA | No cost as you signed it yourself |
| Leaf and intermediate certificates | Root certificates |

- What about setting up your own private CA to sign your certificates?

### Reading / Examining certificate

```console
openssl s_client -connect google.com:443 < /dev/null
openssl s_client -connect google.com:443 < /dev/null | openssl x509 -in /dev/stdin -text -noout
```

## Certificate Architecture

### Structure of certificate

```
 Data:
        Version: 3 (0x2)
        Serial Number:
            40:2a:98:23:2d:c8:6c:13:08:00:00:00:00:1d:89:b1
    Signature Algorithm: sha256WithRSAEncryption
        Issuer: C=US, O=Google Trust Services, CN=GTS CA 1O1
        Validity
            Not Before: Nov  5 07:38:32 2019 GMT
            Not After : Jan 28 07:38:32 2020 GMT
        Subject: C=US, ST=California, L=Mountain View, O=Google LLC, CN=*.google.com
        Subject Public Key Info:
            Public Key Algorithm: rsaEncryption
                Public-Key: (2048 bit)
                Modulus:

        X509v3 extensions:
            X509v3 Subject Alternative Name:
            Authority Information Access:
                OCSP - URI:http://ocsp.pki.goog/gts1o1
                CA Issuers - URI:http://pki.goog/gsr2/GTS1O1.crt
            X509v3 Basic Constraints: critical
                    CA:FALSE
            X509v3 CRL Distribution Points:
                Full Name:
                    URI:http://crl.pki.goog/GTS1O1.crl

    Signature Algorithm: sha256WithRSAEncryption

```

- Data
    - Version
    - Serial number
    - Signature Algorithm
    - Issuer
    - Validity
        - Start date
        - End date
    - Subject's Pub Key
    - X509 Extensions
        - SAN
        - CRL/OCSP
- Signature
    - Signature Algorithm
    - Signature

### Digital Signature

- Hash of something signed by private key
- Verified using public key
- Satisfies Integrity, Authenticity and Non-repudation
- Verification is done by comparing the message digests (Hashes)
- Applications - SSL, Secure eMail, eDocuments, Watermarking

![Digital Signature](images/digital-signature.png "Digital Signature")

### Certificate Standards and Encoding methods

- Standard
    - x509 - PKIX (Public Key Infrastructure) certificate.
    Standard for SSL by NetScape.
- Encoding
    - DER (Distinguished Encoding Rules) Std => Binary DER encodes certs. (appear as .cer/.crt files)
    - PEM (Privacy Enhance Mail) Std => ASCII (Base64) armored data prefixed with a "--- BEGIN ---" line.
    (appears as .cer/.crt/.pem files)
- File extensions
    - .crt => *nix convention of binary DER or Base64 PEM
    - .cer => Microsoft convention of binary DER or Base64 PEM
    - .key => public/private PKCS#8 keys. DER or PEM.

```
# Encoding conversion
openssl x509 -in ServerCertificate.cer -outform der -out ServerCertificate.der
openssl x509 -in ServerCertificate.der -inform der -outform pem -out ServerCertificate.pem
```

### Types of Certificates

based on trust level (DV, OV, EV)

- DV - Domain Validated (Basic):
    - Small or medium level website owners who only wish to encrypt their domain
    can issue DV SSL certificate.
    - Features:
        - No paper work or documentation required for validation. Validated against the
        domain. It does not guarantee the identity of the website's owner nor the actual
        existence of the organization
        - Green padlock
        - Lower price
        - Quick issuance within minutes
        - 99,9% mobile and web browser compatibility
        - Comes up with Wildcard and Multi Domain features
        - Reissue as many times as needed during the validity period
    - Validation process (email, file, registrar)
    - https://aboutssl.org/domain-validated-ssl-validation-process/

- OV - Organization Validated (Enhanced)
    - Business identity level trust. Organization name printed in the certificate. (https://reddit.com)
    - Features:
        - Green padlock
        - 1-3 for issuance
        - More trusted than DV
        - Organization name is validated and part of the certificate. (Organization and Subject are filled up)
        - https://aboutssl.org/document-require-for-ov-ssl-code-signing-certificate/

- EV - Extended Validated (Complete)
    - For trusted and high security sites (https://www.godaddy.com)
    - Features
        - Green Address Bar + Organization Name + Trust Seal
        - Up to 10 business days for issuance & Very Strict Validation Process
        - OV by default + High 256-bit encryption with 2048-bit key length
        - Multi domain with SAN only.
        - https://aboutssl.org/document-require-for-ev-ssl-certificate/

## Encryption Algorithms

**Kerckhoff's Principle**
```
A cryptosystem should be secure even if the attacker knows all details about the system, with
the exception of the secret key. In particular, the system should be secure when the attacker
knows the encryption and decryption algorithms.
```
- Private/Symmetric Key Encryption Algorithms
- Public/Asymmetric Key Encryption Algorithms
- Hashing Algorithms

**Symmetric/Private Key Algorithm**

| Key length | Security Estimation |
|---|---|
| 56-65 bits (AES, 3DES) | Short term: a few hours / days |
| 112-128 bits (AES, 3DES) | Long term several decades |
| 256 bits (AES, 3DES) | Long term several decades |

**Asymmetric/Public

- Integer Factorization (RSA)
- Descrete Logarithm (DH, DSA, Elgamal)
- Elliptic Curves (ECDH, ECDSA)

### Private/Symmetric Key Encryption

- DES, 3DES, AES, RC4
- Based on single common shared secret key
- Faster than Public Key encryption
- Both sender and receiver should have the shared secret

![Private Symmetric Key Encryption](images/private-symmetric-key.png "Private Symmetric Key Encryption")

### AES

- Based on Rijndael algorithm
- Block cipher
- Modes
    - Electronic Codebook (ECB)
    - Cipher Block Chaining (CBC)
    - Output Feedback (OFB)
    - Counter (CTR)
    - Galois/Counter Mode (GCM)
- 128, 192 and 256 bits

### Public/Asymmetric Key Encryption

- RSA, DSA
- Key bit length
- Slower

![Public Asymmetric Key Encryption](images/public-asymmetric-key-encryption.png "Public Asymmetric Key Encryption")

### RSA

- Ron Rivest, Adi Shamir and Leonard Adelman in 1977
- Patented by MIT expired in 2000
- Good for signing and encryption
- Advanced key computation
- Bad for key exchange

**Integer Prime Factorization Problem**
```
For three very large positive integers e, d and n such that with modular
exponentiation for all integer m:
(m^e)^d=m(mod n)

and that even knowing e and n or even m it can be extremely difficult to
find d. The public key is represented by the integers n and e; and, the private
key, by the integer d.
```

### Elliptic Curve Cryptography

- Discovered in 1985 by Victor Miller (IBM) and Neil Koblits (University of Washington)
- Some implementations patented by Certicom
- Low computing power requirements
- Reduced key length and hence fast
- Use only standard NIST curves

**Elliptic Curve Discrete Logarithm Problem**
```
Let P and Q be two points on an elliptic curve such that
kP = Q, where k is a scalar. Given P and Q, it is
computationally infeasible to obtain k, if k is sufficiently
large. k is the discrete logarithm of Q to the base P.

On EC, Scalar multiplication is a one way function.
```

### Hashing Algorithms

- One-way digest
- Unique
- Fixed length (32, 40, 64 etc.)
- Collision resistance
- MD5, SHA1, SHA2, SHA3, RIPEMD, Tiger, Whirlpool, GOST etc.

```console
openssl dgst -md5 message1.bin message2.bin
openssl dgst -sha1 message1.bin message2.bin
openssl dgst -sha256 message1.bin message2.bin
```






