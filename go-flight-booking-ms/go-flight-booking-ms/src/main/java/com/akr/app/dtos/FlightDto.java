package com.akr.app.dtos;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {

    private Long flightId;

    private String departureTime;
    private String arrivalTime;
    private String departureDate;
    private String arrivalDate;


}
