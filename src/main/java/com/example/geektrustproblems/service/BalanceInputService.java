package com.example.geektrustproblems.service;

import com.example.geektrustproblems.dto.OutputResponseDto;
import com.example.geektrustproblems.model.BorrowerAccount;
import com.example.geektrustproblems.repository.BorrowerAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceInputService implements IProcessInput {

    @Autowired
    BorrowerAccountRepository accountRepository;

    @Override
    public String process(String[] line) {

        Integer emiNo = Integer.parseInt(line[3]);

        BorrowerAccount ba = accountRepository.getByborrowerName(line[2]);

        if (ba == null)
            return " ";

        Double totalAmountToBePaidNow = emiNo * ba.emiAmount();
        Double totalLumpSumTillNow = ba.lumpSumPaidTillEmiNumber(emiNo);

        Double totalMoneyPaidUptillCurrentEmi = totalAmountToBePaidNow + totalLumpSumTillNow;

        Double pendingAmount = ba.totalAmountToBeRepaid() - totalMoneyPaidUptillCurrentEmi;

        Integer remainingEmis = (int) Math.ceil(pendingAmount / ba.emiAmount());

        OutputResponseDto output = new OutputResponseDto(ba.getBankName(), ba.getBorrowerName(), totalMoneyPaidUptillCurrentEmi,
                remainingEmis);

        return output.toString();
    }

}