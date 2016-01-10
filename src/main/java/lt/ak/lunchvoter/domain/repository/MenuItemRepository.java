package lt.ak.lunchvoter.domain.repository;

import io.katharsis.resource.exception.ResourceNotFoundException;
import lt.ak.lunchvoter.domain.model.Menu;
import lt.ak.lunchvoter.domain.model.MenuItem;
import lt.ak.lunchvoter.domain.repository.base.BaseResourceRepository;
import lt.ak.lunchvoter.jpa.MenuItemJpaRepo;
import lt.ak.lunchvoter.jpa.MenuJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Arturas Kazenas
 */
@Component
public class MenuItemRepository extends BaseResourceRepository<MenuItem> {

    @Autowired
    private MenuItemJpaRepo jpaRepo;

    @Autowired
    private MenuJpaRepo menuJpaRepo;


    public MenuItemRepository() {
        super(ROLE_USER, ROLE_ADMIN);
    }

    @Override
    protected CrudRepository<MenuItem, Long> getJpaRepo() {
        return jpaRepo;
    }

    @Override
    public void delete(Long id) {
        checkRoleForWriting();
        MenuItem menuItem = jpaRepo.findOne(id);
        if (menuItem == null) {
            throw new ResourceNotFoundException("");
        }
        Menu menu = menuItem.getMenu();
        if (menu != null) {
            menu.getMenuitems().remove(menuItem);
            menuJpaRepo.save(menu);
        }
    }

}
