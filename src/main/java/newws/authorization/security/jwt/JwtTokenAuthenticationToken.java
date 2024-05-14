package newws.authorization.security.jwt;

import newws.authorization.security.auth_info.Authentication;
import newws.authorization.security.auth_info.AuthenticationObjectDetails;
import newws.authorization.security.auth_info.GrantedAuthority;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public abstract class JwtTokenAuthenticationToken implements Authentication, CredentialsContainer {

    private final Collection<GrantedAuthority> authorities;
    //WebAuthenticationDetails
    private Object details;
    public static final List<GrantedAuthority> NO_AUTHORITIES = Collections.emptyList();
    private boolean authenticated = false;

    // token 인가 객체 생성
    protected JwtTokenAuthenticationToken(Collection<GrantedAuthority> authorities) {

        if (authorities != null)
            // 변경 불가한 리스트
            this.authorities = List.copyOf(authorities);
        else
            this.authorities = NO_AUTHORITIES;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    // 자격 증명 삭제 -> 하위 상속받은 클래스에서 구현 -> null 만드는 구현 메소드 구축 하면 삭제,
    @Override
    public void eraseCredentials() {
//        eraseSecret(getCredentials());  // 자격 증명 지우기 // token 형식이라 필요하지 않을 것 같습니다. null로 셋팅하고 들어갈 예정
        eraseSecret(getPrincipal());    // 주체 지우기
        eraseSecret(this.details);      // 기타 세부 정보 지우기
    }

    private void eraseSecret(Object secret) {
        if (secret instanceof CredentialsContainer) {
            ((CredentialsContainer) secret).eraseCredentials();
        }
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    @Override
    public String getName() {
        // Object에 대한 타입 검증
        if (this.getPrincipal() instanceof AuthenticationObjectDetails userDetails) {
            return userDetails.getUsername();
        }
        if (this.getPrincipal() instanceof Principal principal) {
            return principal.getName();
        }
        return (this.getPrincipal() == null) ? "" : this.getPrincipal().toString();
    }

    @Override
    public Object getDetails() {
        return this.details;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String toString() {
        return "JwtTokenAuthenticationToken{" +
                "authorities=" + authorities +
                ", details=" + details +
                ", authenticated=" + authenticated +
                '}';
    }
}
