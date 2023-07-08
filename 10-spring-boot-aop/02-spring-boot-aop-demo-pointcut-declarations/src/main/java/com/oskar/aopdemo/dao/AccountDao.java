package com.oskar.aopdemo.dao;

import com.oskar.aopdemo.Account;

public interface AccountDao {
    boolean addAccount(Account account, boolean vipFlag);
    String getName();
    void setName(String name);
    String getServiceCode();
    void setServiceCode(String serviceCode);
}
