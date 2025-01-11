package com.akr.app.dtos;

import com.akr.app.utils.BookingStatus;
import com.akr.app.utils.CheckInStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightPassengerDto {

    private Long bookingId;
    private List<FlightDto> flights;
    private PassengerDto passenger;
    private String bookingDate;
    private double totalPrice;
    private BookingStatus bookingStatus;
    private CheckInStatus checkInStatus;

}
