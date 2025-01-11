package com.akr.app.services.impl;

import com.akr.app.dtos.FlightDto;
import com.akr.app.entities.Flight;
import com.akr.app.exceptions.DepartureBeforeArrivalException;
import com.akr.app.exceptions.ResourceNotFoundException;
import com.akr.app.repos.FlightRepo;
import com.akr.app.services.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepo flightRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FlightDto add(FlightDto flightDto) {

        LocalDate arrivalDateParsed = LocalDate.parse(flightDto.getArrivalDate());
        LocalDate departureDateParsed = LocalDate.parse(flightDto.getDepartureDate());
        LocalTime arrivalTimeParsed = LocalTime.parse(flightDto.getArrivalTime());
        LocalTime departureTimeParsed = LocalTime.parse(flightDto.getDepartureTime());

        if (arrivalDateParsed.isAfter(departureDateParsed) || (arrivalDateParsed.isEqual(departureDateParsed) && arrivalTimeParsed.isAfter(departureTimeParsed))) {
            throw new DepartureBeforeArrivalException("Arrival time must be before departure time");
        }

        Flight entityFlight = flightRepo.save(modelMapper.map(flightDto, Flight.class));
        return modelMapper.map(entityFlight, FlightDto.class);
    }

    @Override
    public List<FlightDto> getAll() {
        return flightRepo.findAll().stream().map(flight -> modelMapper.map(flight, FlightDto.class)).collect(Collectors.toList());
    }

    @Override
    public FlightDto getById(Long flightId) {
        return modelMapper.map(flightRepo.findById(flightId).orElseThrow(() -> new ResourceNotFoundException("flight", "id", flightId)), FlightDto.class);
    }
}
