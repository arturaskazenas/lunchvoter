package lt.ak.lunchvoter.jpa;

import lt.ak.lunchvoter.domain.model.MenuItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Arturas Kazenas
 */
public interface MenuItemJpaRepo extends CrudRepository<MenuItem, Long> {
}
