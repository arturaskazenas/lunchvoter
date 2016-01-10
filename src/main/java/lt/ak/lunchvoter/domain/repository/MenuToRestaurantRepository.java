package lt.ak.lunchvoter.domain.repository;

import lt.ak.lunchvoter.domain.model.Menu;
import lt.ak.lunchvoter.domain.model.Restaurant;
import lt.ak.lunchvoter.domain.repository.base.BaseRelationshipRepository;
import lt.ak.lunchvoter.jpa.MenuJpaRepo;
import lt.ak.lunchvoter.jpa.RestaurantJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Arturas Kazenas
 */
@Component
public class MenuToRestaurantRepository extends BaseRelationshipRepository<Menu, Restaurant> {

    @Autowired
    private MenuJpaRepo sourceJpaRepo;

    @Autowired
    private RestaurantJpaRepo targetJpaRepo;

    public MenuToRestaurantRepository() {
        super(ROLE_USER, ROLE_ADMIN);
    }

    @Override
    protected CrudRepository<Menu, Long> getSourceJpaRepo() {
        return sourceJpaRepo;
    }

    @Override
    protected CrudRepository<Restaurant, Long> getTargetJpaRepo() {
        return targetJpaRepo;
    }

    @Override
    protected Long getTargetId(Menu menu) {
        return menu.getRestaurant().getId();
    }

    @Override
    protected Iterable<Long> getTargetIds(Menu menu) {
        return Arrays.asList(menu.getRestaurant().getId());
    }

    @Override
    protected Collection<Restaurant> getTargets(Menu menu) {
        return null;
    }

    @Override
    protected void setSource(Restaurant restaurant, Menu menu) {
    }

    @Override
    protected Collection<Menu> getSources(Restaurant restaurant) {
        return restaurant.getMenus();
    }

    @Override
    protected void setTarget(Menu menu, Restaurant restaurant) {
        menu.setRestaurant(restaurant);
    }

}
