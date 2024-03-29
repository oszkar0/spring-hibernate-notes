package com.oskar.aopdemo;

import com.oskar.aopdemo.dao.AccountDao;
import com.oskar.aopdemo.dao.MembershipDao;
import com.oskar.aopdemo.service.TrafficFortuneService;
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
	public CommandLineRunner commandLineRunner(AccountDao theAccountDao, MembershipDao theMembershipDao,
											   TrafficFortuneService trafficFortuneService){
		return runner -> {
			//demoTheBeforeAdvice(theAccountDao, theMembershipDao);
			//demoAfterReturningAdvice(theAccountDao);
			//demoAfterThrowingAdvice(theAccountDao);
			//demoAfterAdvice(theAccountDao);
			//demoAroundAdvice(trafficFortuneService);
			demoAroundAdviceHandleException(trafficFortuneService);
		};
	}

	private void demoAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain program: demoAroundAdviceHandleException");

		System.out.println("Calling getFortune()");

		boolean tripWire = true;
		String data = trafficFortuneService.getFortune(tripWire);

		System.out.println("My fortune is: " + data);

		System.out.println("Finished");
	}

	private void demoAroundAdvice(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain program: demoAroundAdvice");

		System.out.println("Calling getFortune()");

		String data = trafficFortuneService.getFortune();

		System.out.println("My fortune is: " + data);

		System.out.println("Finished");
	}

	private void demoAfterAdvice(AccountDao theAccountDao) {
		List<Account> accounts = null;
		try {
			boolean tripWire = true;
			accounts =  theAccountDao.findAccounts(tripWire);
		} catch (Exception e ){
			System.out.println("Exception in main program");
		}
		System.out.println("Main method accounts: " + accounts);
	}

	private void demoAfterThrowingAdvice(AccountDao theAccountDao) {
		List<Account> accounts = null;
		try {
			boolean tripWire = true;
			accounts =  theAccountDao.findAccounts(tripWire);
		} catch (Exception e ){
			System.out.println("Exception in main program");
		}
		System.out.println("Main method accounts: " + accounts);
	}

	private void demoAfterReturningAdvice(AccountDao theAccountDao) {
		List<Account> accounts = theAccountDao.findAccounts();

		System.out.println("Main method accounts: " + accounts);
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
