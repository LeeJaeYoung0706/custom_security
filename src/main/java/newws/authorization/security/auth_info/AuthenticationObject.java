package newws.authorization.security.auth_info;


import newws.authorization.security.jwt.JwtTokenAuthenticationToken;
import newws.authorization.security.SerialVersionUIDList;

import java.util.Collection;

public class AuthenticationObject extends JwtTokenAuthenticationToken {

    private static final long serialVersionUID = SerialVersionUIDList.SERIAL_SECURITY_CONTEXT_UID;

    private final AuthenticationObjectDetails principal;
    private Object credentials;

    public AuthenticationObject(AuthenticationObjectDetails principal, Object credentials, Collection<GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    public AuthenticationObject(AuthenticationObjectDetails principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    @Override
    public AuthenticationObjectDetails getPrincipal() {
        return this.principal;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    // 인증되지 않은 사용자
    public static AuthenticationObject unauthenticated(AuthenticationObjectDetails principal, Object credentials) {
        return new AuthenticationObject(principal, credentials);
    }
    // 인증된 사용자
    public static AuthenticationObject authenticated(AuthenticationObjectDetails principal, Object credentials,
                                                                    Collection<GrantedAuthority> authorities) {
        return new AuthenticationObject(principal, credentials, authorities);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
       super.setAuthenticated(isAuthenticated);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }

}
