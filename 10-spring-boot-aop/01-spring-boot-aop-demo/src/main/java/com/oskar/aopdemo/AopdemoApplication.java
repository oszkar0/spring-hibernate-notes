package com.oskar.aopdemo;

import com.oskar.aopdemo.dao.AccountDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDao theAccountDao){
		return runner -> {
			demoTheBeforeAdvice(theAccountDao);
		};
	}

	private void demoTheBeforeAdvice(AccountDao theAccountDao) {
		//call the bussiness method
		theAccountDao.addAccount();
	}


}
