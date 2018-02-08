package com.paazl.service;

import com.paazl.domain.OrderSheep;
import com.paazl.dto.OrderSheepDto;
import com.paazl.dto.SheepStatussesDto;
import com.paazl.exception.ClientWSException;
import com.paazl.ws.CheckSheepStatussesRest;
import com.paazl.ws.BalanceRest;
import com.paazl.dto.BalanceDto;
import com.paazl.ws.OrderSheepRest;
import com.paazl.ws.SheepStatussesRest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

@Service
public class ShepherdService {
    public static final Logger logger = LoggerFactory.getLogger(ShepherdService.class);

    @Autowired
    private BalanceRest balance;

    @Autowired
    private SheepStatussesRest sheepStatusses;

    @Autowired
    private CheckSheepStatussesRest checkSheepStatusses;

    @Autowired
    private OrderSheepRest orderSheep;

    public BalanceDto getBalance() {
        try {
            return BalanceConverter.write(
                    balance.get().getBody());
        } catch (ClientWSException e) {
            logger.error(e.getMessage());
        }
        return new BalanceDto();
    }

    public SheepStatussesDto getSheepStatusses() {
        try {
            return SheepStatussesConverter.write(
                    sheepStatusses.get().getBody());
        } catch (ClientWSException e) {
            logger.error(e.getMessage());
        }
        return new SheepStatussesDto();
    }

    public boolean checkSheepStatusses() {
        try {
            return checkSheepStatusses.get().getBody();
        } catch (ClientWSException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public OrderSheepDto postOrderSheep(OrderSheep request) {
        try {
            return OrderSheepConverter.write(
                    request,
                    orderSheep.post(request).getBody());
        } catch (ClientWSException e) {
            logger.error(e.getMessage());
        }
        return OrderSheepConverter.write(
                getSheepStatusses().getSheepStatusses());
    }
}
