import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Scanner;

public class Main {
    // Converts a byte array to a hexadecimal string
    public static String asHex(byte buf[]) {
        StringBuilder strbuf = new StringBuilder(buf.length * 2);
        for (byte b : buf) {
            if ((b & 0xff) < 0x10)
                strbuf.append("0");
            strbuf.append(Integer.toHexString(b & 0xff));
        }
        return strbuf.toString();
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your message: ");
        String message = scanner.nextLine();

        // Generate AES key
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128); // AES key size (128-bit)
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

        // Initialize cipher for encryption
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        // Encrypt the message
        byte[] encrypted = cipher.doFinal(message.getBytes());
        System.out.println("Encrypted text: " + asHex(encrypted));

        // Initialize cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        // Decrypt the encrypted message
        byte[] original = cipher.doFinal(encrypted);
        String originalString = new String(original);
        System.out.println("Decrypted text: " + originalString);
    }
}
