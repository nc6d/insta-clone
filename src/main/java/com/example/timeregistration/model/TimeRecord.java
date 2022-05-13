package com.example.timeregistration.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class TimeRecord {

    @Id
    private BigInteger id;

    private LocalDate date;

    private Double value;

    @DBRef
    private User user;


}
