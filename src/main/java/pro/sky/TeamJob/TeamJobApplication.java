package pro.sky.TeamJob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TeamJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamJobApplication.class, args);
	}

}
