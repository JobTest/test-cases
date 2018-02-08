package com.paazl.service;

import com.paazl.domain.OrderSheep;
import com.paazl.domain.SheepStatusses;
import com.paazl.dto.OrderSheepDto;
import lombok.NonNull;

import java.util.Date;

public class OrderSheepConverter {

    public static OrderSheepDto write(@NonNull SheepStatusses sheepStatusses) {
        OrderSheepDto dto = new OrderSheepDto();
        dto.setNotSheepDesired(0);
        dto.setSheepStatusses(sheepStatusses);
        dto.setDate(new Date());

        return dto;
    }

    public static OrderSheepDto write(@NonNull OrderSheep orderSheep, @NonNull SheepStatusses sheepStatusses) {
        OrderSheepDto dto = new OrderSheepDto();
        dto.setNotSheepDesired(orderSheep.getNotSheepDesired());
        dto.setSheepStatusses(sheepStatusses);
        dto.setDate(new Date());

        return dto;
    }
}
