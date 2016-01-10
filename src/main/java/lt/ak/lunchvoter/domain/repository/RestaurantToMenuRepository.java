package lt.ak.lunchvoter.domain.repository;

import com.google.common.collect.Collections2;
import lt.ak.lunchvoter.domain.model.Menu;
import lt.ak.lunchvoter.domain.model.Restaurant;
import lt.ak.lunchvoter.domain.repository.base.BaseRelationshipRepository;
import lt.ak.lunchvoter.jpa.MenuJpaRepo;
import lt.ak.lunchvoter.jpa.RestaurantJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Arturas Kazenas
 */
@Component
public class RestaurantToMenuRepository extends BaseRelationshipRepository<Restaurant, Menu> {

    @Autowired
    private RestaurantJpaRepo sourceJpaRepo;

    @Autowired
    private MenuJpaRepo targetJpaRepo;

    public RestaurantToMenuRepository() {
        super(ROLE_USER, ROLE_ADMIN);
    }

    @Override
    protected CrudRepository<Restaurant, Long> getSourceJpaRepo() {
        return sourceJpaRepo;
    }

    @Override
    protected CrudRepository<Menu, Long> getTargetJpaRepo() {
        return targetJpaRepo;
    }

    @Override
    protected Long getTargetId(Restaurant restaurant) {
        return null;
    }

    @Override
    protected Iterable<Long> getTargetIds(Restaurant restaurant) {
        return Collections2.transform(restaurant.getMenus(), menu -> menu.getId());
    }

    @Override
    protected Collection<Menu> getTargets(Restaurant restaurant) {
        return restaurant.getMenus();
    }

    @Override
    protected void setSource(Menu menu, Restaurant restaurant) {
        menu.setRestaurant(restaurant);
    }

    @Override
    protected Collection<Restaurant> getSources(Menu menu) {
        return null;
    }

    @Override
    protected void setTarget(Restaurant restaurant, Menu menu) {
    }

}
