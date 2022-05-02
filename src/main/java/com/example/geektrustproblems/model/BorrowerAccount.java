package com.example.geektrustproblems.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class BorrowerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountId;

    private String bankName;
    private String borrowerName;
    private Double principalAmount;
    private int loanPeriod;
    private Double roi;
    private Timestamp creationTimestamp;

    @OneToMany(targetEntity = Payment.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "accountId")
    private List<Payment> payments = new ArrayList<>();

    public Double totalAmountToBeRepaid() {
        if (this.loanPeriod > 0)
            return this.principalAmount + ((this.principalAmount * this.loanPeriod * this.roi) / 100);
        else
            return 0.0;
    }

    public Double emiAmount() {
        Double totalAmountToBeRepaid = totalAmountToBeRepaid();
        if (totalAmountToBeRepaid > 0)
            return Math.ceil(totalAmountToBeRepaid / (this.loanPeriod * 12));
        else
            return 0.0;
    }

    public Double lumpSumPaidTillEmiNumber(Integer emiNumber) {
        Double sum = 0.0;
        if (this.payments != null && this.payments.size() > 0) {

            for (Payment payment : this.payments) {
                if (payment.getEmiNumber() <= emiNumber) {
                    sum += payment.getAmount();
                }
            }
            // this.payments.stream().filter(x -> x.getEmiNumber() <= emiNumber).mapToDouble(i -> i.getAmount()).sum();
        }
        return sum;
    }
}