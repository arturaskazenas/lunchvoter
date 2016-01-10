package lt.ak.lunchvoter.domain.model.base;

import javax.persistence.MappedSuperclass;

/**
 * Created by Arturas Kazenas
 */
@MappedSuperclass
public class BaseNamedEntity extends BaseEntity{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
