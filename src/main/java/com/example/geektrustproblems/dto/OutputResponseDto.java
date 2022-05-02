package com.example.geektrustproblems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutputResponseDto {

    private String bankName;
    private String borrowerName;
    private Double amountPaid;
    private Integer noOfEmisLeft;

    @Override
    public String toString() {
        return bankName + " " + borrowerName + " " + (int)amountPaid.doubleValue() + " " + noOfEmisLeft;
    }

}