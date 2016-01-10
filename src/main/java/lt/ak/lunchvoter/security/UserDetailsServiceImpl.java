package lt.ak.lunchvoter.security;

import lt.ak.lunchvoter.domain.model.User;
import lt.ak.lunchvoter.domain.model.UserGroup;
import lt.ak.lunchvoter.jpa.UserJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String USER_GROUP_ADMINS = "admins";

    @Autowired
    private UserJpaRepo userRepo;


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username);
        if (user != null) {
            List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(AuthConfigurer.ROLE_USER);
            if (isAdmin(user)) {
                authorities.addAll(AuthorityUtils.createAuthorityList(AuthConfigurer.ROLE_ADMIN));
            }
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPasswordHash(), authorities);

        } else {
            throw new UsernameNotFoundException("could not find the user '" + username + "'");
        }
    }

    private boolean isAdmin(User user) {
        for (UserGroup userGroup : user.getGroups()) {
            if (userGroup.getName().equals(USER_GROUP_ADMINS)) {
                return true;
            }
        }
        return false;
    }
}
