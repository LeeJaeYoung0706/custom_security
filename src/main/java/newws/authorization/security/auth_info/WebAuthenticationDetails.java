package newws.authorization.security.auth_info;

import jakarta.servlet.http.HttpServletRequest;
import newws.authorization.security.SerialVersionUIDList;

import java.io.Serializable;
import java.util.Objects;

public class WebAuthenticationDetails implements Serializable {

    private static final long serialVersionUID = SerialVersionUIDList.SERIAL_SECURITY_CONTEXT_UID;
    private final String remoteAddress;

    public WebAuthenticationDetails(HttpServletRequest request) {
        // IP 주소 저장
        this.remoteAddress = request.getRemoteAddr();
    }

    public String getRemoteAddress() {
        return this.remoteAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WebAuthenticationDetails that = (WebAuthenticationDetails) o;
        return Objects.equals(this.remoteAddress, that.remoteAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.remoteAddress);
    }
}
