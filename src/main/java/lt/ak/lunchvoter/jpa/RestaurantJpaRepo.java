package lt.ak.lunchvoter.jpa;

import lt.ak.lunchvoter.domain.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Arturas Kazenas
 */
public interface RestaurantJpaRepo extends CrudRepository<Restaurant, Long> {
}
