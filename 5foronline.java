import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
            keyGenerator.init(128);
            Key secretKey = keyGenerator.generateKey();

            byte[] iv = new byte[8];
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance("Blowfish/CFB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

            System.out.println("Initialization Vector: " + Base64.getEncoder().encodeToString(iv));

            String inputMessage = "This is the text to encrypt!";
            System.out.println("Input message: " + inputMessage);

            byte[] encryptedBytes = cipher.doFinal(inputMessage.getBytes());
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println("Encrypted text: " + encryptedText);

            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            String decryptedText = new String(decryptedBytes);
            System.out.println("Decrypted text: " + decryptedText);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
