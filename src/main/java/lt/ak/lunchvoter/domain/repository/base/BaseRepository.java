package lt.ak.lunchvoter.domain.repository.base;

import lt.ak.lunchvoter.domain.repository.UnauthorizedException;
import lt.ak.lunchvoter.security.AuthConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Arturas Kazenas
 */
public class BaseRepository {

    protected final static String ROLE_USER = AuthConfigurer.ROLE_USER;
    protected final static String ROLE_ADMIN = AuthConfigurer.ROLE_ADMIN;

    protected String roleForReading;
    protected String roleForWriting;

    public BaseRepository(String roleForReading, String roleForWriting) {
        this.roleForReading = roleForReading;
        this.roleForWriting = roleForWriting;
    }

    protected void checkRole(String role) {
        if (role == null) {
            return;
        }
        for (GrantedAuthority grantedAuthority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            if (grantedAuthority.getAuthority().equals(role)) {
                return;
            }
        }
        throw new UnauthorizedException();
    }

    protected void checkRoleForReading() {
        checkRole(roleForReading);
    }

    protected void checkRoleForWriting() {
        checkRole(roleForWriting);
    }
}
