package lt.ak.lunchvoter.domain.repository.base;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.RelationshipRepository;
import io.katharsis.resource.exception.ResourceNotFoundException;
import lt.ak.lunchvoter.domain.model.base.BaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Arturas Kazenas
 */
public abstract class BaseRelationshipRepository<SOURCE_ENTITY extends BaseEntity, TARGET_ENTITY extends BaseEntity>
        extends BaseRepository
        implements RelationshipRepository<SOURCE_ENTITY, Long, TARGET_ENTITY, Long> {


    protected abstract CrudRepository<SOURCE_ENTITY, Long> getSourceJpaRepo();

    protected abstract CrudRepository<TARGET_ENTITY, Long> getTargetJpaRepo();

    protected abstract Long getTargetId(SOURCE_ENTITY source);

    protected abstract Iterable<Long> getTargetIds(SOURCE_ENTITY source);

    protected abstract Collection<TARGET_ENTITY> getTargets(SOURCE_ENTITY source);

    protected abstract void setSource(TARGET_ENTITY target, SOURCE_ENTITY source);

    protected abstract Collection<SOURCE_ENTITY> getSources(TARGET_ENTITY target);

    protected abstract void setTarget(SOURCE_ENTITY source, TARGET_ENTITY target);


    public BaseRelationshipRepository(String roleForReading, String roleForWriting) {
        super(roleForReading, roleForWriting);
    }


    @Override
    public void setRelation(SOURCE_ENTITY source, Long targetId, String fieldName) {
        checkRoleForWriting();
        TARGET_ENTITY target = getTargetJpaRepo().findOne(targetId);
        if (target == null) {
            throw new ResourceNotFoundException("");
        }
        setTarget(source, target);
        getSources(target).add(source);
        getTargetJpaRepo().save(target);
    }



    @Override
    public void setRelations(SOURCE_ENTITY source, Iterable<Long> targetIds, String fieldName) {
        checkRoleForWriting();
    }

    @Override
    public void addRelations(SOURCE_ENTITY source, Iterable<Long> targetIds, String fieldName) {
        checkRoleForWriting();
        Collection<TARGET_ENTITY> targets = getTargets(source);
        for(Long targetId : targetIds){
            TARGET_ENTITY target = getTargetJpaRepo().findOne(targetId);
            if (target == null) {
                throw new ResourceNotFoundException("");
            }
            setSource(target, source);
            targets.add(target);
        }
        getSourceJpaRepo().save(source);
    }


    @Override
    public void removeRelations(SOURCE_ENTITY source, Iterable<Long> targetIds, String fieldName) {
        checkRoleForWriting();
        Collection<TARGET_ENTITY> targets = new LinkedList<>(getTargets(source));
        for(Long targetId : targetIds){
            for(TARGET_ENTITY target : targets){
                if(targetId.equals(target.getId())){
                    getTargets(source).remove(target);
                }
            }
        }
        getSourceJpaRepo().save(source);
    }

    @Override
    public TARGET_ENTITY findOneTarget(Long sourceId, String fieldName, QueryParams queryParams) {
        checkRoleForReading();
        SOURCE_ENTITY source = getSourceJpaRepo().findOne(sourceId);
        if (source == null) {
            throw new ResourceNotFoundException("");
        }
        Long targetId = getTargetId(source);
        return getTargetJpaRepo().findOne(targetId);
    }

    @Override
    public Iterable<TARGET_ENTITY> findManyTargets(Long sourceId, String fieldName, QueryParams queryParams) {
        checkRoleForReading();
        SOURCE_ENTITY source = getSourceJpaRepo().findOne(sourceId);
        if (source == null) {
            throw new ResourceNotFoundException("");
        }
        Iterable<Long> targetIds = getTargetIds(source);
        return getTargetJpaRepo().findAll(targetIds);
    }
}
