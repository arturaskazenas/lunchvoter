package lt.ak.lunchvoter.domain.repository;

import com.google.common.collect.Collections2;
import lt.ak.lunchvoter.domain.model.Menu;
import lt.ak.lunchvoter.domain.model.MenuItem;
import lt.ak.lunchvoter.domain.repository.base.BaseRelationshipRepository;
import lt.ak.lunchvoter.jpa.MenuItemJpaRepo;
import lt.ak.lunchvoter.jpa.MenuJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Arturas Kazenas
 */
@Component
public class MenuToMenuItemRepository extends BaseRelationshipRepository<Menu, MenuItem> {

    @Autowired
    private MenuJpaRepo sourceJpaRepo;

    @Autowired
    private MenuItemJpaRepo targetJpaRepo;

    public MenuToMenuItemRepository() {
        super(ROLE_USER, ROLE_ADMIN);
    }

    @Override
    protected CrudRepository<Menu, Long> getSourceJpaRepo() {
        return sourceJpaRepo;
    }

    @Override
    protected CrudRepository<MenuItem, Long> getTargetJpaRepo() {
        return targetJpaRepo;
    }

    @Override
    protected Long getTargetId(Menu restaurant) {
        return null;
    }

    @Override
    protected Iterable<Long> getTargetIds(Menu menu) {
        return Collections2.transform(menu.getMenuitems(), menuItem -> menuItem.getId());
    }

    @Override
    protected Collection<MenuItem> getTargets(Menu menu) {
        return menu.getMenuitems();
    }

    @Override
    protected void setSource(MenuItem menuItem, Menu menu) {
        menuItem.setMenu(menu);
    }

    @Override
    protected Collection<Menu> getSources(MenuItem menuItem) {
        return null;
    }

    @Override
    protected void setTarget(Menu menu, MenuItem menuItem) {
    }

}
