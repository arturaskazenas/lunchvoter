package lt.ak.lunchvoter.domain.model;

import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToOne;
import lt.ak.lunchvoter.domain.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * Created by Arturas Kazenas
 */
@Entity
@JsonApiResource(type = "menuitems")
public class MenuItem extends BaseEntity {

    private String dishName;

    private BigDecimal price;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonApiToOne
    private Menu menu;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }


    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
