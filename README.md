# Encryption Java Code

This repository contains Java implementations for message encryption and decryption using the AES (Advanced Encryption Standard) algorithm. It includes different variations of encryption methods, such as simple encryption, character shifting, and random character shifting.

## Features

- **AES Encryption**: Securely encrypts messages using AES with a 256-bit key.
- **Decryption**: Decrypts messages using the corresponding key.
- **Key Management**: Generates and converts AES keys to and from Base64 encoded strings for easy storage.
- **User Interaction**: Console-based interface for encrypting and decrypting messages.

## Variants

- **Encrpyptor.java**: Basic AES encryption and decryption without any additional transformations.
- **EncryptorXXX.java**: AES encryption with an additional character shift of 3 positions after encryption and before decryption.
- **EncryptorRandom.java**: AES encryption with a random character shift (between 1 and 10), where the shift is stored with the encrypted message for correct decryption.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- An IDE or text editor for Java development (e.g., IntelliJ IDEA, Eclipse, or VSCode)

### Installation

1. Clone this repository:

    ```bash
    git clone https://github.com/yourusername/encryption-java-code.git
    cd encryption-java-code
    ```

2. Compile the Java files:

    ```bash
    javac src/*.java
    ```

3. Run the desired program:

    ```bash
    java -cp src Encryptor
    ```

   or

    ```bash
    java -cp src EncryptorXXX
    ```

   or

    ```bash
    java -cp src EncryptorRandom
    ```

## Usage

Upon running the program, you will be prompted to choose an action:

- **Encrypt**: Input a message to encrypt and receive the encrypted message along with the key.
- **Decrypt**: Input an encrypted message and the corresponding key to decrypt the message.
- **Exit**: Exit the program.

### Example

**Encrypting a Message:**

```plaintext
Enter a message to encrypt: Hello World
Encrypted Message: [encrypted string]
Encryption Key (save this securely): [base64 key]

## Example

**Encrypting a Message:**

```plaintext
Enter the encrypted message to decrypt: [encrypted string]
Enter the key for decryption: [base64 key]
Decrypted Message: Hello World
