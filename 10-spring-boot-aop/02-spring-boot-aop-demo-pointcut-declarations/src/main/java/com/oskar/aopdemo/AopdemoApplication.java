package com.oskar.aopdemo;

import com.oskar.aopdemo.dao.AccountDao;
import com.oskar.aopdemo.dao.MembershipDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDao theAccountDao, MembershipDao theMembershipDao){
		return runner -> {
			//demoTheBeforeAdvice(theAccountDao, theMembershipDao);
			demoAfterReturningAdvice(theAccountDao);
		};
	}

	private void demoAfterReturningAdvice(AccountDao theAccountDao) {
		List<Account> accounts = theAccountDao.findAccounts();
	}

	private void demoTheBeforeAdvice(AccountDao theAccountDao, MembershipDao theMembershipDao) {
		//call the bussiness method
		Account account = new Account();
		account.setName("Oskar");
		theAccountDao.addAccount(account, true);
		theMembershipDao.addMemberAccount();

		theAccountDao.setName("coope");
		theAccountDao.setServiceCode("L@ACD");

		String name = theAccountDao.getName();
		String serviceCode = theAccountDao.getServiceCode();
	}


}
