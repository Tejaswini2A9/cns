import java.security.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Create a MessageDigest instance for SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA1");

            // Input string
            String input = "abc";

            // Compute the SHA-1 hash
            md.update(input.getBytes());
            byte[] output = md.digest();

            // Display the result
            System.out.println("SHA1(\"" + input + "\") = " + bytesToHex(output));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    // Convert byte array to hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(hexDigits[(b >> 4) & 0x0F]);
            hexString.append(hexDigits[b & 0x0F]);
        }
        return hexString.toString();
    }
}
