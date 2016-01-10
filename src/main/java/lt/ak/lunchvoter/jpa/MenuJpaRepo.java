package lt.ak.lunchvoter.jpa;

import lt.ak.lunchvoter.domain.model.Menu;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

/**
 * Created by Arturas Kazenas
 */
public interface MenuJpaRepo extends CrudRepository<Menu, Long> {
    Iterable<Menu> findByDate(Date date);
}
