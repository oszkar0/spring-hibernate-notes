package com.oskar.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao{
    @Override
    public void addAccount() {
        System.out.printf(getClass() + ": DOING MY DB WORK: ADDING ACCOUNT");
    }
}
