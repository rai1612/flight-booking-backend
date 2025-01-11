package com.akr.app.services;

import com.akr.app.dtos.FlightDto;

import java.util.List;

public interface FlightService {

    FlightDto add(FlightDto flightDto);
    List<FlightDto> getAll();
    FlightDto getById(Long flightId);

}
