package lt.ak.lunchvoter.domain.repository;

import io.katharsis.queryParams.QueryParams;
import lt.ak.lunchvoter.domain.model.User;
import lt.ak.lunchvoter.domain.repository.base.BaseResourceRepository;
import lt.ak.lunchvoter.jpa.UserJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by Arturas Kazenas
 */
@Component
public class UserRepository extends BaseResourceRepository<User> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserJpaRepo jpaRepo;


    public UserRepository() {
        super(ROLE_ADMIN, ROLE_ADMIN);

    }




    @Override
    public User findOne(Long id, QueryParams queryParams) {
        User user = super.findOne(id, queryParams);
        user.setPasswordHash(null);
        return user;
    }

    @Override
    public Iterable<User> findAll(QueryParams queryParams) {
        Iterable<User> users = super.findAll(queryParams);
        users.forEach((user)->user.setPasswordHash(null));
        return users;
    }

    @Override
    public Iterable<User> findAll(Iterable<Long> ids, QueryParams queryParams) {
        Iterable<User> users = super.findAll(ids, queryParams);
        users.forEach((user)->user.setPasswordHash(null));
        return users;
    }

    @Override
    public <S extends User> S save(S user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        return super.save(user);
    }

    @Override
    protected CrudRepository<User, Long> getJpaRepo() {
        return jpaRepo;
    }

}
