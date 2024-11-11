import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class EncryptorXXX {

    // Generate AES key
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        return keyGen.generateKey();
    }

    // Convert the SecretKey to a String for storage
    public static String keyToString(SecretKey secretKey) {
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    // Convert a String back to SecretKey
    public static SecretKey stringToKey(String keyStr) {
        byte[] decodedKey = Base64.getDecoder().decode(keyStr);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    // Encrypt a message
    public static String encrypt(String message, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);

        // Shift each character by 3 positions after encryption
        return shiftString(encryptedMessage, 3);
    }

    // Decrypt a message
    public static String decrypt(String shiftedMessage, SecretKey secretKey) throws Exception {
        // Reverse shift each character by 3 positions before decryption
        String encryptedMessage = shiftString(shiftedMessage, -3);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    // Shift each character in a string by a specified number
    public static String shiftString(String input, int shift) {
        StringBuilder shifted = new StringBuilder();
        for (char c : input.toCharArray()) {
            shifted.append((char) (c + shift));
        }
        return shifted.toString();
    }

    // Main method to handle console input
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continueProgram = true;

            while (continueProgram) {
                System.out.println("\nWould you like to (1) Encrypt, (2) Decrypt, or (3) Exit?");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                switch (choice) {
                    case 1: // Encryption flow
                        SecretKey secretKey = generateKey();
                        String keyString = keyToString(secretKey);

                        System.out.print("Enter a message to encrypt: ");
                        String message = scanner.nextLine();

                        String encryptedMessage = encrypt(message, secretKey);
                        System.out.println("Encrypted Message: " + encryptedMessage);
                        System.out.println("Encryption Key (save this securely): " + keyString);
                        break;

                    case 2: // Decryption flow
                        System.out.print("Enter the encrypted message to decrypt: ");
                        String encryptedInput = scanner.nextLine();

                        System.out.print("Enter the key for decryption: ");
                        String keyInput = scanner.nextLine();

                        try {
                            SecretKey userKey = stringToKey(keyInput);
                            String decryptedMessage = decrypt(encryptedInput, userKey);
                            System.out.println("Decrypted Message: " + decryptedMessage);
                        } catch (Exception e) {
                            System.out.println("Decryption failed: Incorrect key or message format.");
                        }
                        break;

                    case 3: // Exit
                        continueProgram = false;
                        System.out.println("Exiting the program. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please select 1 for Encryption, 2 for Decryption, or 3 to Exit.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
