import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

public class BlowFishEncryption {
    public static void main(String[] args) {
        try {
            // Generate a Blowfish key
            KeyGenerator keyGen = KeyGenerator.getInstance("Blowfish");
            keyGen.init(128); // Key size of 128 bits
            SecretKey secretKey = keyGen.generateKey();

            // Convert the key to a readable Base64 string
            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            System.out.println("Generated Secret Key (Base64): " + encodedKey);

            // Initialize the Cipher for encryption
            Cipher cipher = Cipher.getInstance("Blowfish");

            // Read plaintext from user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter text to encrypt: ");
            String plaintext = scanner.nextLine();

            // Encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println("Encrypted Text: " + encryptedText);

            // Decrypt the text
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            String decryptedText = new String(decryptedBytes);
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
