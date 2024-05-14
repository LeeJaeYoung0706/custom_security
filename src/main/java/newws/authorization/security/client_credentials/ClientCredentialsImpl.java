package newws.authorization.security.client_credentials;

public class ClientCredentialsImpl extends ClientCredentials{

    // 암호화 변경하고 싶을 경우 ( 기본 AES256 )
    @Override
    public String getCLIENT_SECRET() throws Exception {
        return super.getCLIENT_SECRET();
    }
}
