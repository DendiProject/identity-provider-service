package SpringBoot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("Hello Wotld !!!");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		SpringApplication.run(TestApplication.class, args);
	}
}
