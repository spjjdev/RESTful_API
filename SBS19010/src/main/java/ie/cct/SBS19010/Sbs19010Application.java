package ie.cct.SBS19010;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ie.cct.*")
public class Sbs19010Application {

	public static void main(String[] args) {
		SpringApplication.run(Sbs19010Application.class, args);
	}

}
