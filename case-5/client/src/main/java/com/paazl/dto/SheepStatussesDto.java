package com.paazl.dto;

import com.paazl.domain.SheepStatusses;
import lombok.Data;

import java.util.Date;

@Data
public class SheepStatussesDto {
    private SheepStatusses sheepStatusses;
    private Date date;

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
                sheepStatusses +
                ", date=" + date +
                '}';
    }
}
