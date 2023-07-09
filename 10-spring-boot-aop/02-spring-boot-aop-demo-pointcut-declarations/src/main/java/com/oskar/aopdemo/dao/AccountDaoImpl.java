package com.oskar.aopdemo.dao;

import com.oskar.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Account> findAccounts() {
        List<Account> accounts = new ArrayList<>();

        Account account1 = new Account();
        account1.setName("Oskar");
        account1.setLevel("80L");

        Account account2 = new Account();
        account2.setName("Oskar");
        account2.setLevel("80L");

        accounts.add(account1);
        accounts.add(account2);

        return accounts;
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) throws Exception {
        if(tripWire){
            throw new Exception("Test exception");
        }
        return null;
    }
}
