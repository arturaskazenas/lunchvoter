package lt.ak.lunchvoter.domain.model.base;

import io.katharsis.resource.annotations.JsonApiId;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by Arturas Kazenas
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    @JsonApiId
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
