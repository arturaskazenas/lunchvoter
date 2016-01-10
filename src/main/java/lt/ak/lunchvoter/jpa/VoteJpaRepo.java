package lt.ak.lunchvoter.jpa;

import lt.ak.lunchvoter.domain.model.User;
import lt.ak.lunchvoter.domain.model.Vote;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Arturas Kazenas
 */
public interface VoteJpaRepo extends CrudRepository<Vote, Long> {
    Iterable<Vote> findByUser(User user);
}
