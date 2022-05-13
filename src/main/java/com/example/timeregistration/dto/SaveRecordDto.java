package com.example.timeregistration.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SaveRecordDto {

    @JsonProperty("date")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    @JsonProperty("value")
    private Double value;

}
