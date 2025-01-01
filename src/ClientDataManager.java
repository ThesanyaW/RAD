import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDataManager {
    private static final String ALGORITHM = "AES";
    private static final Map<String, String> userProfiles = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(ClientDataManager.class.getName());

    public static void main(String[] args) {
        try {
            String name = "John Doe";
            String nic = "123456789V";
            String email = "john.doe@example.com";
            SecretKey secretKey = generateKey();

            String encryptedName = encrypt(name, secretKey);
            String encryptedNic = encrypt(nic, secretKey);
            String encryptedEmail = encrypt(email, secretKey);

            System.out.println("Client Data Successfully Encrypted");

            storeUserProfile(encryptedName, encryptedNic, encryptedEmail);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred", e);
        }
    }

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(256, new SecureRandom());
        return keyGen.generateKey();
    }

    public static String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static void storeUserProfile(String name, String nic, String email) {
        userProfiles.put(email, "Name: " + name + ", NIC: " + nic + ", Email: " + email);
        System.out.println("User profile stored successfully.");
    }

    public static void retrieveUserProfile(String email) {
        String profile = userProfiles.get(email);
        if (profile != null) {
            System.out.println("Retrieved User Profile: " + profile);
        } else {
            System.out.println("User profile not found.");
        }
    }
}
