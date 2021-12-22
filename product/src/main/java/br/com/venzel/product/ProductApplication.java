package br.com.venzel.product;

import java.util.TimeZone;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
		SpringApplication.run(ProductApplication.class, args);
	}

}
