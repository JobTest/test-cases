package com.paazl.service;

import com.paazl.domain.SheepStatusses;
import com.paazl.dto.SheepStatussesDto;
import lombok.NonNull;

import java.util.Date;

public class SheepStatussesConverter {

    public static SheepStatussesDto write(@NonNull SheepStatusses sheepStatusses) {
        SheepStatussesDto dto = new SheepStatussesDto();
        dto.setSheepStatusses(sheepStatusses);
        dto.setDate(new Date());

        return dto;
    }
}
