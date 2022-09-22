package es.plaza.retobici;

import es.plaza.retobici.config.RsaKeyProperties;
import es.plaza.retobici.di.MapboxConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MapboxConfigProperties.class, RsaKeyProperties.class})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
