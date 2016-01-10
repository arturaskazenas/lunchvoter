package lt.ak.lunchvoter.domain.repository;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.queryParams.params.FilterParams;
import io.katharsis.resource.exception.ResourceNotFoundException;
import lt.ak.lunchvoter.domain.model.Menu;
import lt.ak.lunchvoter.domain.model.Restaurant;
import lt.ak.lunchvoter.domain.repository.base.BaseResourceRepository;
import lt.ak.lunchvoter.jpa.MenuJpaRepo;
import lt.ak.lunchvoter.jpa.RestaurantJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

/**
 * Created by Arturas Kazenas
 */
@Component
public class MenuRepository extends BaseResourceRepository<Menu> {

    @Autowired
    private MenuJpaRepo jpaRepo;

    @Autowired
    private RestaurantJpaRepo restaurantJpaRepo;


    public MenuRepository() {
        super(ROLE_USER, ROLE_ADMIN);
    }


    @Override
    protected CrudRepository<Menu, Long> getJpaRepo() {
        return jpaRepo;
    }

    @Override
    public void delete(Long id) {
        checkRoleForWriting();
        Menu menu = jpaRepo.findOne(id);
        if (menu == null) {
            throw new ResourceNotFoundException("");
        }
        Restaurant restaurant = menu.getRestaurant();
        if (restaurant != null) {
            restaurant.getMenus().remove(menu);
            restaurantJpaRepo.save(restaurant);
        }
    }

    @Override
    public Iterable<Menu> findAll(QueryParams queryParams) {
        checkRoleForReading();
        Iterable<Menu> menus;
        Date dateFilter = getDateFilter(queryParams);
        if (dateFilter != null) {
            menus = jpaRepo.findByDate(dateFilter);
        } else {
            menus = jpaRepo.findAll();
        }
        return menus;
    }

    private Date getDateFilter(QueryParams queryParams) {
        FilterParams menusParams = queryParams.getFilters().getParams().get("menus");
        if(menusParams == null){
            return null;
        }
        Set<String> dateSet = menusParams.getParams().get("date");
        if(dateSet==null || dateSet.isEmpty()){
            return null;
        }
        String strDate = dateSet.iterator().next();
        return new Date(Long.parseLong(strDate));
    }
}
