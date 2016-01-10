package lt.ak.lunchvoter.domain.repository;

import io.katharsis.resource.exception.ResourceNotFoundException;
import lt.ak.lunchvoter.TimeUtil;
import lt.ak.lunchvoter.domain.model.Menu;
import lt.ak.lunchvoter.domain.model.User;
import lt.ak.lunchvoter.domain.model.Vote;
import lt.ak.lunchvoter.domain.repository.base.BaseResourceRepository;
import lt.ak.lunchvoter.jpa.MenuJpaRepo;
import lt.ak.lunchvoter.jpa.UserJpaRepo;
import lt.ak.lunchvoter.jpa.VoteJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by Arturas Kazenas
 */
@Component
public class VoteRepository extends BaseResourceRepository<Vote> {

    @Value("${lt.ak.lunchvoter.timeLimitToVote}")
    private String timeLimit;

    @Autowired
    private UserJpaRepo userJpaRepo;

    @Autowired
    private MenuJpaRepo menuJpaRepo;

    @Autowired
    private VoteJpaRepo jpaRepo;

    public VoteRepository() {
        super(ROLE_USER, ROLE_USER);
    }

    @Override
    protected CrudRepository<Vote, Long> getJpaRepo() {
        return jpaRepo;
    }


    @Override
    public <S extends Vote> S save(S newVote) {
        if(TimeUtil.now().after(TimeUtil.parseTime(timeLimit))){
            throw new TooLateException();
        }
        if(!TimeUtil.today().equals(newVote.getMenu().getDate())){
            throw new ExpiredMenuException();
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userJpaRepo.findByName(username);

        S  vote;
        Vote  existingVote = findExisting(user);
        if(existingVote!=null){
            vote = (S)existingVote;
        } else {
            vote = newVote;
        }

        vote.setMenu(newVote.getMenu());
        vote.setUser(user);
        return super.save(vote);
    }

    private Vote findExisting(User user) {
        Iterable<Vote> votes = jpaRepo.findByUser(user);
        for(Vote vote: votes){
            if(TimeUtil.today().equals(vote.getMenu().getDate())){
                return vote;
            }
        }
        return null;
    }


    @Override
    public void delete(Long id) {
        checkRole(ROLE_ADMIN);
        Vote vote = jpaRepo.findOne(id);
        if (vote == null) {
            throw new ResourceNotFoundException("");
        }
        Menu menu = vote.getMenu();
        if (menu != null) {
            menu.getVotes().remove(vote);
            menuJpaRepo.save(menu);
        }
    }


    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }


}
