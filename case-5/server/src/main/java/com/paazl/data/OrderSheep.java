package com.paazl.data;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class OrderSheep {
    @Min(1)
    private Integer notSheepDesired;

    public int getNotSheepDesired() {
        return notSheepDesired;
    }

    public void setNotSheepDesired(int notSheepDesired) {
        this.notSheepDesired = notSheepDesired;
    }
}
