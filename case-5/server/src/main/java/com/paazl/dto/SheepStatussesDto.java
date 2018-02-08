package com.paazl.dto;

import lombok.Data;

@Data
public class SheepStatussesDto {

    private int numberOfHealthySheep;
    private int numberOfDeadSheep;

    public SheepStatussesDto() {
    }

    public SheepStatussesDto (
            int numberOfHealthySheep,
            int numberOfDeadSheep) {
        this.numberOfHealthySheep = numberOfHealthySheep;
        this.numberOfDeadSheep = numberOfDeadSheep;
    }
}
