package newws.authorization.security.client_credentials;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public  class ClientInfo {

    @Value("${spring.custom-security.client-id}")
    private String clientId;
    @Value("${spring.custom-security.client-secret}")
    private String clientSecret;
    @Value("${spring.custom-security.aes.key}")
    private String aesKey;

    public static String CLIENT_ID;
    public static String CLIENT_SECRET;
    public static String AES_KEY;

    public ClientInfo() {
        this.CLIENT_ID = clientId;
        this.CLIENT_SECRET = clientSecret;
        this.AES_KEY = aesKey;
    }
}
