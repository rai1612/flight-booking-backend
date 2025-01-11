package com.akr.app.controllers;

import com.akr.app.dtos.BookingDto;
import com.akr.app.dtos.FlightPassengerDto;
import com.akr.app.services.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<FlightPassengerDto> book(@Valid @RequestBody BookingDto bookingDto){
        return new ResponseEntity<>(bookingService.book(bookingDto), HttpStatus.CREATED);
    }

    @PutMapping("/checkin/{booking_id}")
    public ResponseEntity<FlightPassengerDto> checkIn(@PathVariable("booking_id") Long bookingId){
        return new ResponseEntity<>(bookingService.checkIn(bookingId), HttpStatus.OK);
    }

    @PutMapping("/cancel/{booking_id}")
    public ResponseEntity<FlightPassengerDto> cancel(@PathVariable("booking_id") Long bookingId){
        return new ResponseEntity<>(bookingService.cancel(bookingId), HttpStatus.OK);
    }

    @GetMapping("/passengerId/{passenger_id}")
    public ResponseEntity<List<FlightPassengerDto>> getByPassengerId(@PathVariable("passenger_id") Long passengerId){
        return new ResponseEntity<>(bookingService.getByPassengerId(passengerId), HttpStatus.OK);
    }

    @GetMapping("/{booking_id}")
    public ResponseEntity<FlightPassengerDto> getById(@PathVariable("booking_id") Long bookingId){
        return new ResponseEntity<>(bookingService.getById(bookingId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FlightPassengerDto>> getAll(){
        return new ResponseEntity<>(bookingService.getAll(), HttpStatus.OK);
    }
}
