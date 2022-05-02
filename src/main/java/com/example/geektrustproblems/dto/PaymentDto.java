package com.example.geektrustproblems.dto;

import lombok.Data;

@Data
public class PaymentDto {
    private String bankName;
    private String borrowerName;
    private String lumpSumAmount;
    private int emis;
}

// BANK_NAME BORROWER_NAME LUMP_SUM_AMOUNT EMI_NO 