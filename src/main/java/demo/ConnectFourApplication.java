package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
(basePackages = { "demo.controller",
		"demo.repo",
		
		})
		
@EnableAutoConfiguration
public class ConnectFourApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConnectFourApplication.class, args);
    }
}
