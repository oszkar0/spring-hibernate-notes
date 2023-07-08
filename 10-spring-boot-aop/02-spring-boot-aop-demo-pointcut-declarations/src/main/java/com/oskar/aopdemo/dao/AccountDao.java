package com.oskar.aopdemo.dao;

import com.oskar.aopdemo.Account;

import java.util.List;

public interface AccountDao {
    boolean addAccount(Account account, boolean vipFlag);
    String getName();
    void setName(String name);
    String getServiceCode();
    void setServiceCode(String serviceCode);
    List<Account> findAccounts();
}
