package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.joda.time.LocalTime;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
            LocalTime currentTime = new LocalTime();
            System.out.println("The current local time is: " + currentTime);
            Greeter greeter = new Greeter();
            System.out.println(greeter.sayHello());
            
            SpringApplication.run(DemoApplication.class, args);
	}
}
