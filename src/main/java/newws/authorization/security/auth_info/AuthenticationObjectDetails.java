package newws.authorization.security.auth_info;

import java.io.Serializable;
import java.util.Collection;

/**
 * 사용자 디테일 정보 가져올 때 인터페이스
 */
public interface AuthenticationObjectDetails extends Serializable {
    // enum으로 공통 처리 ( Enum을 커스텀 )
    // 참고 package newws.authorization.security.auth_info; GrantedAuthority
    Collection<GrantedAuthority> getAuthorities();
    // Context 내에서 사용할 sign id
    String getUsername();
    // 활성화 여부
    boolean isEnabled();
}
