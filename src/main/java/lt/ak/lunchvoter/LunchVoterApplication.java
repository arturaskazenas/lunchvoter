package lt.ak.lunchvoter;

import io.katharsis.spring.boot.KatharsisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(KatharsisConfig.class)
public class LunchVoterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LunchVoterApplication.class, args);
    }

}
