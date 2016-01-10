package lt.ak.lunchvoter.domain.model;

import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToMany;
import lt.ak.lunchvoter.domain.model.base.BaseNamedEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arturas Kazenas
 */
@Entity
@JsonApiResource(type = "restaurants")
public class Restaurant extends BaseNamedEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonApiToMany
    private List<Menu> menus;


    public List<Menu> getMenus() {
        if (menus == null) {
            menus = new LinkedList<>();
        }
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
