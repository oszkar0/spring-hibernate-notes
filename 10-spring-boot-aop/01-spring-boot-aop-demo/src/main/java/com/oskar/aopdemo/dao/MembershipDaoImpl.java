package com.oskar.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDaoImpl implements MembershipDao{
    @Override
    public void addMemberAccount() {
        System.out.printf(getClass() + ": DOING MY DB WORK: ADDING ACCOUNT");
    }
}
