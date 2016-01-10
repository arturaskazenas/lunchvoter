package lt.ak.lunchvoter;

import lt.ak.lunchvoter.security.AuthConfigurer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {LunchVoterApplication.class, AuthConfigurer.class})
@TestPropertySource(locations="classpath:test.properties")
public class LunchVoterApplicationTests {

	@Test
	public void contextLoads() {
	}

}
