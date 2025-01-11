package com.akr.app.services;

import com.akr.app.dtos.BookingDto;
import com.akr.app.dtos.FlightPassengerDto;

import java.util.List;

public interface BookingService {

    FlightPassengerDto book(BookingDto bookingDto);

    FlightPassengerDto checkIn(Long bookingId);

    FlightPassengerDto cancel(Long bookingId);

    List<FlightPassengerDto> getByPassengerId(Long passengerId);

    FlightPassengerDto getById(Long bookingId);

    List<FlightPassengerDto> getAll();

}
