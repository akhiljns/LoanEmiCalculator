package com.example.geektrustproblems.service;

import java.sql.Timestamp;
import java.time.Instant;

import com.example.geektrustproblems.model.BorrowerAccount;
import com.example.geektrustproblems.repository.BorrowerAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanInputService implements IProcessInput {

    @Autowired
    BorrowerAccountRepository accountRepository;

    @Override
    public String process(String[] line) {

        if (accountRepository.getByborrowerName(line[2]) != null)
            return "loan already present";

        BorrowerAccount ba = new BorrowerAccount();
        ba.setBankName(line[1]);
        ba.setBorrowerName(line[2]);
        ba.setPrincipalAmount(Double.parseDouble(line[3]));
        ba.setLoanPeriod(Integer.parseInt(line[4]));
        ba.setRoi(Double.parseDouble(line[5]));
        ba.setCreationTimestamp(Timestamp.from(Instant.now()));

        accountRepository.saveAndFlush(ba);
        return ba.toString();

    }
}