package lt.ak.lunchvoter.domain.model;

import io.katharsis.resource.annotations.JsonApiIncludeByDefault;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToMany;
import io.katharsis.resource.annotations.JsonApiToOne;
import lt.ak.lunchvoter.domain.model.base.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arturas Kazenas
 */
@Entity
@JsonApiResource(type = "menus")
public class Menu extends BaseEntity {

    @Column(name = "MENU_DATE")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonApiToOne
    private Restaurant restaurant;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonApiToMany
    @JsonApiIncludeByDefault
    private List<MenuItem> menuitems;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonApiToMany
    @JsonApiIncludeByDefault
    private List<Vote> votes;


    public List<Vote> getVotes() {
        if (votes == null) {
            votes = new LinkedList<>();
        }
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<MenuItem> getMenuitems() {
        if (menuitems == null) {
            menuitems = new LinkedList<>();
        }
        return menuitems;
    }

    public void setMenuitems(List<MenuItem> menuitems) {
        this.menuitems = menuitems;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
