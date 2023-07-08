package com.oskar.aopdemo.dao;

import com.oskar.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao{
    private String name;
    private String serviceCode;
    @Override
    public boolean addAccount(Account account, boolean vipFlag) {
        System.out.printf(getClass() + ": DOING MY DB WORK: ADDING ACCOUNT");
        return true;
    }

    public String getName() {
        System.out.println(getClass() + "in getName()");
        return name;
    }

    public void setName(String name) {

        System.out.println(getClass() + "in setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + "in getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + "in setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
