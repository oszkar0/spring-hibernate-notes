package com.oskar.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
    //this is where we add all of our related advices for logging

    //lets start with an @Before advice
    @Pointcut("execution(* com.oskar.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {}
    @Pointcut("execution(* com.oskar.aopdemo.dao.*.get*(..))")
    private void getter() {}
    @Pointcut("execution(* com.oskar.aopdemo.dao.*.set*(..))")
    private void setter() {}
    @Pointcut("forDaoPackage() && !(setter() || getter())")
    private void forDaoPackageExcludeGettersAndSetter() {}
    @Before("forDaoPackageExcludeGettersAndSetter()")
    public void beforeAddAccountAdvice(){
        System.out.println("\n===>Executing @Before advice on addAccount()");
    }
    @Before("forDaoPackageExcludeGettersAndSetter()")
    public void performApiAnalytics(){
        System.out.println("===>Performing API analytics");
    }
}
