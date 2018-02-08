package com.paazl.service;

import com.paazl.data.Sheep;
import com.paazl.dto.SheepStatussesDto;

import java.util.List;
import lombok.NonNull;

public class SheepStatusses {

    public static SheepStatussesDto write(@NonNull List<Sheep> healthySheep, @NonNull List<Sheep> deadSheep) {
        return new SheepStatussesDto(
                healthySheep.size(),
                deadSheep.size()
        );
    }
}
