package lt.ak.lunchvoter.domain.model;

import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToOne;
import lt.ak.lunchvoter.domain.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Arturas Kazenas
 */
@Entity
@JsonApiResource(type = "votes")
public class Vote extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonApiToOne
    private Menu menu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonApiToOne
    private User user;



    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
