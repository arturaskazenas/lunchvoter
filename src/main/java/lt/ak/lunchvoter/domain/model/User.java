package lt.ak.lunchvoter.domain.model;

import io.katharsis.resource.annotations.JsonApiResource;
import lt.ak.lunchvoter.domain.model.base.BaseNamedEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by Arturas Kazenas
 */
@Entity
@JsonApiResource(type = "users")
public class User extends BaseNamedEntity {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_MEMBERSHIP", inverseJoinColumns = {@JoinColumn(name = "GROUP_ID")})
    private List<UserGroup> groups;

    @Transient
    private String password;

    private String passwordHash;


    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Transient
    public String getPassword() {
        return password;
    }

    @Transient
    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<UserGroup> groups) {
        this.groups = groups;
    }


}
