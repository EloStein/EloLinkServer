package de.spring.elolink_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EloLinkSpring {

	public static void main(String[] args) {
		SpringApplication.run(EloLinkSpring.class, args);
	}

	@Bean
	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> portCustomizer() {
		return factory -> factory.setPort(25270);
	}

}
