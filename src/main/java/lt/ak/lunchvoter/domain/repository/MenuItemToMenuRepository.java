package lt.ak.lunchvoter.domain.repository;

import lt.ak.lunchvoter.domain.model.Menu;
import lt.ak.lunchvoter.domain.model.MenuItem;
import lt.ak.lunchvoter.domain.repository.base.BaseRelationshipRepository;
import lt.ak.lunchvoter.jpa.MenuItemJpaRepo;
import lt.ak.lunchvoter.jpa.MenuJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Arturas Kazenas
 */
@Component
public class MenuItemToMenuRepository extends BaseRelationshipRepository<MenuItem, Menu> {

    @Autowired
    private MenuItemJpaRepo sourceJpaRepo;

    @Autowired
    private MenuJpaRepo targetJpaRepo;

    public MenuItemToMenuRepository() {
        super(ROLE_USER, ROLE_ADMIN);
    }

    @Override
    protected CrudRepository<MenuItem, Long> getSourceJpaRepo() {
        return sourceJpaRepo;
    }

    @Override
    protected CrudRepository<Menu, Long> getTargetJpaRepo() {
        return targetJpaRepo;
    }

    @Override
    protected Long getTargetId(MenuItem menuItem) {
        return menuItem.getMenu().getId();
    }

    @Override
    protected Iterable<Long> getTargetIds(MenuItem menuItem) {
        return Arrays.asList(menuItem.getMenu().getId());
    }

    @Override
    protected Collection<Menu> getTargets(MenuItem menuItem) {
        return null;
    }

    @Override
    protected void setSource(Menu menu, MenuItem menuItem) {
    }

    @Override
    protected Collection<MenuItem> getSources(Menu menu) {
        return menu.getMenuitems();
    }

    @Override
    protected void setTarget(MenuItem menuItem, Menu menu) {
        menuItem.setMenu(menu);
    }

}
