package com.example.geektrustproblems.repository;

import com.example.geektrustproblems.model.BorrowerAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerAccountRepository extends JpaRepository<BorrowerAccount, Integer> {

    public BorrowerAccount getByborrowerName(String borrowerName);

}