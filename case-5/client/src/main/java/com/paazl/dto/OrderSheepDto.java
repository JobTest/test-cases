package com.paazl.dto;

import com.paazl.domain.SheepStatusses;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class OrderSheepDto {
    private int notSheepDesired;
    private SheepStatusses sheepStatusses;
    private Date date;

    public int getNotSheepDesired() {
        return notSheepDesired;
    }

    public void setNotSheepDesired(int notSheepDesired) {
        this.notSheepDesired = notSheepDesired;
    }

    public SheepStatusses getSheepStatusses() {
        return sheepStatusses;
    }

    public void setSheepStatusses(SheepStatusses sheepStatusses) {
        this.sheepStatusses = sheepStatusses;
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
                "notSheepDesired=" + notSheepDesired +
                ", " + sheepStatusses +
                ", date=" + date +
                '}';
    }
}
