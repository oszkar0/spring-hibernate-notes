package com.oskar.aopdemo.aspect;

import com.oskar.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyDemoLoggingAspect {
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


