package newws.authorization.security.context;

import newws.authorization.security.auth_info.Authentication;
import newws.authorization.security.SerialVersionUIDList;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

public class NewwsSecurityContext implements Serializable {

    private static final long serialVersionUID = SerialVersionUIDList.SERIAL_SECURITY_CONTEXT_UID;
    private Authentication authentication;

    public NewwsSecurityContext() {
    }

    public NewwsSecurityContext(Authentication authentication) {
        this.authentication = authentication;
    }

    public boolean isAuthenticated() {
        return authentication != null;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NewwsSecurityContext other) {
            if ((this.getAuthentication() == null) && (other.getAuthentication() == null)) {
                return true;
            }
            if ((this.getAuthentication() != null) && (other.getAuthentication() != null)
                    && this.getAuthentication().equals(other.getAuthentication())) {
                return true;
            }
        }
        return false;
    }

    // null 일 경우 0 반환 null 이 아닐 경우 해쉬코드 반환
    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(this.authentication);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(" [");
        if (this.authentication == null) {
            sb.append("인증 객체가 없습니다.");
        }
        else {
            sb.append("인증 객체 = ").append(this.authentication);
        }
        sb.append("]");
        return sb.toString();
    }

}
