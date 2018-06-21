package simple.microservices.resttagshandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import simple.microservices.resttagshandler.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@ComponentScan("simple.microservices.resttagshandler")
public class ResttagshandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResttagshandlerApplication.class, args);
    }
}
