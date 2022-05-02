package com.example.geektrustproblems.service;

import com.example.geektrustproblems.model.BorrowerAccount;
import com.example.geektrustproblems.model.Payment;
import com.example.geektrustproblems.repository.BorrowerAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentInputService implements IProcessInput {

    @Autowired
    BorrowerAccountRepository accountRepository;

    @Override
    public String process(String[] line) {

        Payment p = new Payment();
        p.setAmount(Double.parseDouble(line[3]));
        p.setEmiNumber(Integer.parseInt(line[4]));

        BorrowerAccount ba = accountRepository.getByborrowerName(line[2]);

        if (ba == null)
            return "no loan found";

        int totalEmis = ba.getLoanPeriod() * 12;

        if (p.getEmiNumber() > totalEmis)
            return "emi number invalid";

        ba.getPayments().add(p);

        accountRepository.saveAndFlush(ba);
        return ba.toString();

    }
}