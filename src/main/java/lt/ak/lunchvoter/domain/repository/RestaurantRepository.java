package lt.ak.lunchvoter.domain.repository;

import lt.ak.lunchvoter.domain.model.Restaurant;
import lt.ak.lunchvoter.domain.repository.base.BaseResourceRepository;
import lt.ak.lunchvoter.jpa.RestaurantJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Arturas Kazenas
 */
@Component
public class RestaurantRepository extends BaseResourceRepository<Restaurant> {

    @Autowired
    private RestaurantJpaRepo jpaRepo;


    public RestaurantRepository() {
        super(ROLE_USER, ROLE_ADMIN);
    }

    @Override
    protected CrudRepository<Restaurant, Long> getJpaRepo() {
        return jpaRepo;
    }

}
