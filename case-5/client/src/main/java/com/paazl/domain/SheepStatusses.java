package com.paazl.domain;

import lombok.Data;

@Data
public class SheepStatusses {
    private int numberOfHealthySheep;
    private int numberOfDeadSheep;

    @Override
    public String toString() {
        return "{" +
                "numberOfHealthySheep=" + numberOfHealthySheep +
                ", numberOfDeadSheep=" + numberOfDeadSheep +
                '}';
    }
}
