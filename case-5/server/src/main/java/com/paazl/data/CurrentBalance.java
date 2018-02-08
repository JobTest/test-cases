package com.paazl.data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name="current_balance")
public class CurrentBalance {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private BigInteger balance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    private Date timestamp;

    public CurrentBalance() {
        this.timestamp = new Date();
    }

    public CurrentBalance(BigInteger balance) {
        this();
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
