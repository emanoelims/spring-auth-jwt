package dev.emmanoel.springauthjwt;

import dev.emmanoel.springauthjwt.domain.Role;
import dev.emmanoel.springauthjwt.domain.User;
import dev.emmanoel.springauthjwt.domain.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAuthJwtApplication {

    public static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_MANAGER = "ROLE_MANAGER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static void main(String[] args) {
        SpringApplication.run(SpringAuthJwtApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(final UserService userService) {
        return args -> {
            userService.saveRole(new Role(ROLE_USER));
            userService.saveRole(new Role(ROLE_ADMIN));
            userService.saveRole(new Role(ROLE_MANAGER));
            userService.saveRole(new Role(ROLE_SUPER_ADMIN));

            userService.saveUser(new User("stevejobs", "stevejobssecret"));
            userService.saveUser(new User("billgates", "billgatessecret"));
            userService.saveUser(new User("elonmusk", "elonmusksecret"));
            userService.saveUser(new User("markzuckerberg", "markzuckerbergsecret"));
            userService.saveUser(new User("linus", "linussecret"));

            userService.roleAddToUser("stevejobs", ROLE_ADMIN);
            userService.roleAddToUser("billgates", ROLE_MANAGER);
            userService.roleAddToUser("elonmusk", ROLE_MANAGER);
            userService.roleAddToUser("markzuckerberg", ROLE_USER);
            userService.roleAddToUser("linus", ROLE_SUPER_ADMIN);
        };
    }
}
