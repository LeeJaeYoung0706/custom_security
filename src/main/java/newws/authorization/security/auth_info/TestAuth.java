package newws.authorization.security.auth_info;

import java.util.Arrays;
import java.util.Collection;

public class TestAuth implements AuthenticationObjectDetails{
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Arrays.asList(GrantedAuthority.ROLE_ADMIN);
    }

    @Override
    public String getUsername() {
        return "test name";
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}
