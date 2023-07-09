package com.oskar.aopdemo.aspect;

import com.oskar.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(1)
public class MyDemoLoggingAspect {
    @Around("execution(* getFortune(..)) ")
    public  Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint)  throws Throwable{
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        System.out.println("@Around advice " + methodSignature + "\n");

        long begin = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed(); // <- run the function

        long end = System.currentTimeMillis();

        System.out.println("@Around advice: duration: " + (end - begin)/1000 + " seconds");

        return result;
    }


    //runs for success and failure
    @After("execution(* com.oskar.aopdemo.dao.AccountDao.findAccounts(..)) ")
    public void afterFinallyFindAccountAdvice(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("@After: " + methodSignature + "\n");
    }

    @AfterReturning(
            pointcut = "execution(* com.oskar.aopdemo.dao.AccountDao.findAccounts(..))",
            returning = "results" //<- must match parameter name of below function
    )
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> results){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("@AfterReturning: " + methodSignature + "\n");

        System.out.println(results);

        //lets post process the data
        convertAccountNamesToUpperCase(results);

        System.out.println(results);
    }

    private void convertAccountNamesToUpperCase(List<Account> results) {
        for(Account a: results){
            a.setName(a.getName().toUpperCase());
        }
    }

    @Before("com.oskar.aopdemo.aspect.MySharedPointcutExpressions.forDaoPackageExcludeGettersAndSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint){
        //display method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        System.out.println("Method: " +  methodSignature + "\n");

        //get args
        Object[] args = joinPoint.getArgs();

        for(Object o: args){
            System.out.println(o);

            if(o instanceof Account){
                Account account = (Account) o;

                System.out.println(account.getName());
            }
        }

        System.out.println("\n===>Executing @Before advice on addAccount()\n");
    }

    @AfterThrowing( //doesnt handle the exception!!!! it is throw if not caught
            pointcut = "execution(* findAccounts(boolean))",
            throwing = "exception"
    )
    public void afterThrowingAccountsAdvice(JoinPoint joinPoint, Throwable exception){
        //display method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        System.out.println("@AfterThrowing advice: Method throwing exception: " +  methodSignature + "\n");


        //log exception
        System.out.println("@AfterThrowing advice: Exception: " + exception.getMessage());
    }
}


