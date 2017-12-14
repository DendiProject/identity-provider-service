package springboot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
               
                Greeter greeter = new Greeter();
                System.out.println(greeter.sayHello());
        
		SpringApplication.run(TestApplication.class, args);
	}
}
