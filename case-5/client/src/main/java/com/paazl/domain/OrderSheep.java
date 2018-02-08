package com.paazl.domain;

import lombok.Data;

@Data
public class OrderSheep {
    private int notSheepDesired;

    public OrderSheep(int notSheepDesired) {
        this.notSheepDesired = notSheepDesired;
    }

    public int getNotSheepDesired() {
        return notSheepDesired;
    }

    public void setNotSheepDesired(int notSheepDesired) {
        this.notSheepDesired = notSheepDesired;
    }

    @Override
    public String toString() {
        return "{" +
                "notSheepDesired=" + notSheepDesired +
                '}';
    }
}
