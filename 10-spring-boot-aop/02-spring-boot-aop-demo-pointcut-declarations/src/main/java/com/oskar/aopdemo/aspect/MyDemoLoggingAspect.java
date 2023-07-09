package com.oskar.aopdemo.aspect;

import com.oskar.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(1)
public class MyDemoLoggingAspect {
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
}


