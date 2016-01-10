package lt.ak.lunchvoter.jpa;

import lt.ak.lunchvoter.domain.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Arturas Kazenas
 */
public interface UserJpaRepo extends CrudRepository<User, Long> {

    User findByName(String name);

}
