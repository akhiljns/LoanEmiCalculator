package com.example.geektrustproblems.dto;

import lombok.Data;

@Data
public class LoanDto {
    private String bankName;
    private String borrowerName;
    private String principalAmount;
    private int years;
}
