package pro.sky.TeamJob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Класс запускающий приложение
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@SpringBootApplication
@EnableCaching
public class TeamJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamJobApplication.class, args);
	}

}
