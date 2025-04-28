package com.root.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy // 생략 가능
@EnableTransactionManagement // 생략 가능
@EnableScheduling
public class Gdj89SpringBootMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(Gdj89SpringBootMavenApplication.class, args);
	}

}
