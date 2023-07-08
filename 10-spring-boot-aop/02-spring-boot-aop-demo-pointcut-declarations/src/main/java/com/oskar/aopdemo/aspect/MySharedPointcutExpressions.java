package com.oskar.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MySharedPointcutExpressions {
    @Pointcut("execution(* com.oskar.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {}
    @Pointcut("execution(* com.oskar.aopdemo.dao.*.get*(..))")
    public void getter() {}
    @Pointcut("execution(* com.oskar.aopdemo.dao.*.set*(..))")
    public void setter() {}
    @Pointcut("forDaoPackage() && !(setter() || getter())")
    public void forDaoPackageExcludeGettersAndSetter() {}

}
