package lt.ak.lunchvoter.domain.repository.base;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.ResourceRepository;
import io.katharsis.resource.exception.ResourceNotFoundException;
import lt.ak.lunchvoter.domain.model.base.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Arturas Kazenas
 */
@Component
public abstract class BaseResourceRepository<ENTITY extends BaseEntity> extends BaseRepository implements ResourceRepository<ENTITY, Long> {

    public BaseResourceRepository(String roleForReading, String roleForWriting) {
        super(roleForReading, roleForWriting);
    }

    protected abstract CrudRepository<ENTITY, Long> getJpaRepo();

    @Override
    public ENTITY findOne(Long id, QueryParams queryParams) {
        checkRoleForReading();
        ENTITY entity = getJpaRepo().findOne(id);
        if (entity == null) {
            throw new ResourceNotFoundException("");
        }
        return entity;
    }

    @Override
    public Iterable<ENTITY> findAll(QueryParams queryParams) {
        checkRoleForReading();
        return getJpaRepo().findAll();
    }

    @Override
    public Iterable<ENTITY> findAll(Iterable<Long> ids, QueryParams queryParams) {
        checkRoleForReading();
        return getJpaRepo().findAll(ids);
    }

    @Override
    public void delete(Long id) {
        checkRoleForWriting();
        getJpaRepo().delete(id);
    }

    @Override
    public <S extends ENTITY> S save(S entity) {
        checkRoleForWriting();
        return getJpaRepo().save(entity);
    }

}
