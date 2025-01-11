package com.akr.app.feignclients;

import com.akr.app.dtos.FlightDto;
import com.akr.app.dtos.PassengerDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

    @FeignClient(name = "api-gateway-service")
public interface BookingServiceClient {

    // passenger-service endpoints

    @PostMapping("/passengers/register")
    public ResponseEntity<PassengerDto> registerPassenger(@Valid @RequestBody PassengerDto passengerDto);

    @GetMapping("/passengers")
    public ResponseEntity<List<PassengerDto>> getAllPassengers();

    @GetMapping("/passengers/{passenger_id}")
    public ResponseEntity<PassengerDto> getPassengerById(@PathVariable("passenger_id") Long passengerId);

    // flight-service endpoints

    @PostMapping("/flights/add")
    public ResponseEntity<FlightDto> addFlight(@Valid @RequestBody FlightDto flightDto);

    @GetMapping("/flights")
    public ResponseEntity<List<FlightDto>> getAllFlights();

    @GetMapping("/flights/{flight_id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable("flight_id") Long flightId);
}
