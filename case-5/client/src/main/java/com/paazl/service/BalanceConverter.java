package com.paazl.service;

import com.paazl.dto.BalanceDto;
import lombok.NonNull;

import java.math.BigInteger;
import java.util.Date;

public class BalanceConverter {

    public static BalanceDto write(@NonNull BigInteger balance) {
        BalanceDto dto = new BalanceDto();
        dto.setBalance(balance);
        dto.setDate(new Date());

        return dto;
    }
}
