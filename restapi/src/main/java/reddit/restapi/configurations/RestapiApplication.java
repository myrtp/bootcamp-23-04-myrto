package reddit.restapi.configurations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import reddit.restapi.controllers.AuthenticationController;
import reddit.restapi.controllers.UsersController;
import reddit.restapi.models.User;
import reddit.restapi.repositories.UserRepo;
import reddit.restapi.services.UsersService;
import reddit.restapi.util.AuthorizationChecks;

@ComponentScan(basePackageClasses = {UsersController.class, UsersService.class, UserRepo.class,
		WebSecurityConfig.class, AuthorizationChecks.class})
@EnableJpaRepositories(basePackageClasses = {UserRepo.class})
@EntityScan(basePackageClasses = {User.class})
@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

}
