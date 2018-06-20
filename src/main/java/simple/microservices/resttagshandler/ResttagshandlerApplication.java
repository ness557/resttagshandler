package simple.microservices.resttagshandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("simple.microservices.resttagshandler")
public class ResttagshandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResttagshandlerApplication.class, args);
    }
}
