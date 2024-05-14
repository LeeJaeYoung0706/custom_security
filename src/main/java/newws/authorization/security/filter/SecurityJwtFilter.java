package newws.authorization.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import newws.authorization.security.auth_info.AuthenticationObject;
import newws.authorization.security.auth_info.GrantedAuthority;
import newws.authorization.security.auth_info.TestAuth;
import newws.authorization.security.auth_info.WebAuthenticationDetails;
import newws.authorization.security.context.NewwsSecurityHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;


@Component
public class SecurityJwtFilter extends OncePerRequestFilter {

    private static final Logger logger = Logger.getLogger(SecurityJwtFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        TestAuth testAuth = new TestAuth();
        AuthenticationObject authenticationObject = new AuthenticationObject(testAuth, null, Arrays.asList(GrantedAuthority.ROLE_ADMIN));
        authenticationObject.setDetails(new WebAuthenticationDetails(request));
        NewwsSecurityHolder.getContext().setAuthentication(authenticationObject);
        System.out.println("test initCount = "  + NewwsSecurityHolder.getInitializeCount());
        System.out.println("auth = " + NewwsSecurityHolder.getContext().toString());
        filterChain.doFilter(request, response);
    }
}
