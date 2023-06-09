package com.example.saga;

import com.example.saga.Entity.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SagaApplication {

	public static void main(String[] args) {
		Account[] a = new Account[10];
		SpringApplication.run(SagaApplication.class, args);
	}

}
