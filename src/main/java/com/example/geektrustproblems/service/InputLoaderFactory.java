package com.example.geektrustproblems.service;

import com.example.geektrustproblems.model.Command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputLoaderFactory {

    private LoanInputService loanInputService;
    private PaymentInputService paymentInputService;
    private BalanceInputService balanceInputService;

    @Autowired
    public InputLoaderFactory(LoanInputService loanInputService, PaymentInputService paymentInputService,
            BalanceInputService balanceInputService) {
        this.loanInputService = loanInputService;
        this.paymentInputService = paymentInputService;
        this.balanceInputService = balanceInputService;
    }

    public IProcessInput loadInputService(String command) {

        switch (command) {
            case Command.LOAN:
                return loanInputService;

            case Command.PAYMENT:
                return paymentInputService;

            case Command.BALANCE:
                return balanceInputService;

            default:
                return null;
        }

    }
}