package com.oskar.aopdemo.dao;

import com.oskar.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao{
    @Override
    public boolean addAccount(Account account, boolean vipFlag) {
        System.out.printf(getClass() + ": DOING MY DB WORK: ADDING ACCOUNT");
        return true;
    }
}
