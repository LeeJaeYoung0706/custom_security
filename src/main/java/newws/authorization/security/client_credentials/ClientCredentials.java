package newws.authorization.security.client_credentials;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import static newws.authorization.security.client_credentials.ClientInfo.AES_KEY;
import static newws.authorization.security.client_credentials.ClientInfo.CLIENT_SECRET;

/**
 * REST API 통신 시 1차적인 인증을 해결하기 위해 OAuth2 Client Credentials 방법을 생각해봤습니다.
 */
public abstract class ClientCredentials {

    public String getCLIENT_SECRET() throws Exception {
        return encrypt(CLIENT_SECRET);
    }

    private String encrypt(String data) throws Exception {
        SecretKeySpec key = new SecretKeySpec(AES_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    private String decrypt(String encryptedData) throws Exception {
        SecretKeySpec key = new SecretKeySpec(AES_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedData);
    }
}
