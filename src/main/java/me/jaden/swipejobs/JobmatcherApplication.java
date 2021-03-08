package me.jaden.swipejobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("me.jaden.swipejobs")
public class JobmatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobmatcherApplication.class, args);
	}

}
