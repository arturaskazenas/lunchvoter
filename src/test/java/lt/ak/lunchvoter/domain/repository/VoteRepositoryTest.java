package lt.ak.lunchvoter.domain.repository;

import lt.ak.lunchvoter.TimeUtil;
import lt.ak.lunchvoter.domain.model.Menu;
import lt.ak.lunchvoter.domain.model.User;
import lt.ak.lunchvoter.domain.model.Vote;
import lt.ak.lunchvoter.jpa.MenuJpaRepo;
import lt.ak.lunchvoter.jpa.UserJpaRepo;
import lt.ak.lunchvoter.jpa.VoteJpaRepo;
import lt.ak.lunchvoter.security.AuthConfigurer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Arturas Kazenas.
 */
public class VoteRepositoryTest {

    public static final String USER_NAME = "USER_NAME";

    @InjectMocks
    private VoteRepository testObject;

    @Mock
    private UserJpaRepo userJpaRepo;

    @Mock
    private MenuJpaRepo menuJpaRepo;

    @Mock
    private VoteJpaRepo jpaRepo;

    private Vote vote;
    private User user;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        user = createUser();
        vote = createVote(user);
        setTimeLimit(100000);
        Authentication auth = new TestingAuthenticationToken(USER_NAME, null, AuthConfigurer.ROLE_USER);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private Vote createVote(User user) {
        Vote vote = new Vote();
        Menu menu = new Menu();
        menu.setDate(TimeUtil.today());
        vote.setMenu(menu);
        vote.setUser(user);
        return vote;
    }

    private User createUser() {
        User user = new User();
        user.setName(USER_NAME);
        return user;
    }


    @Test(expected = TooLateException.class)
    public void shouldBeLateIfAfterLimit() throws Exception {
        setTimeLimit(-100000);
        testObject.save(vote);
    }

    @Test
    public void testSave() throws Exception {
        when(userJpaRepo.findByName(USER_NAME)).thenReturn(user);
        Vote existingVote = createVote(user);
        when(jpaRepo.findByUser(user)).thenReturn(Arrays.asList(existingVote));

        testObject.save(vote);

        verify(jpaRepo).save(existingVote);
        assertEquals(vote.getMenu(), existingVote.getMenu());
    }



    private void setTimeLimit(long timeDifference) {
        Date timeLimit = new Date();
        timeLimit.setTime(timeLimit.getTime() + timeDifference);
        testObject.setTimeLimit(new SimpleDateFormat("HH:mm:ss").format(timeLimit));
    }


}