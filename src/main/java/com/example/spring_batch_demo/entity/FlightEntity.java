package com.example.spring_batch_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@RequiredArgsConstructor
@Entity(name="Flight")
public class FlightEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int flightId;

    @Column(name="FlightNo")
    private String flightNo;

    @Column(name="FlightDate")
    private Date flightDate;

    @Column(name = "Route")
    private String route;

    @Column(name = "LinkFlight")
    private String linkFlight;
}
