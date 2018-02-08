package com.paazl.data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="sheep")
public class Sheep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    private Date timestamp;

    private State state;

    public Sheep() {
        this.timestamp = new Date();
        this.state = State.HEALTHY;
    }

    public Sheep(State state) {
        this();
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

