package com.paazl.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class BalanceDto {
    private BigInteger balance;
    private Date date;

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                "balance=" + balance +
                ", date=" + date +
                '}';
    }
}
