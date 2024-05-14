package newws.authorization.security.auth_info;

import newws.authorization.security.auth_info.AuthenticationObjectDetails;
import newws.authorization.security.auth_info.GrantedAuthority;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;

/**
 * 인증 객체 인터페이스 Details -> Token 임으로 password 불필요
 */
public interface Authentication extends Principal, Serializable {

    Collection<GrantedAuthority> getAuthorities();
    Object getDetails();
    Object getPrincipal();
    Object getCredentials();
    boolean isAuthenticated();
    void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException;
}
