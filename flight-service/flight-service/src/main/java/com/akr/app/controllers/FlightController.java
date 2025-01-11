package com.akr.app.controllers;

import com.akr.app.dtos.FlightDto;
import com.akr.app.services.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/add")
    public ResponseEntity<FlightDto> add(@Valid @RequestBody FlightDto flightDto){
        return new ResponseEntity<>(flightService.add(flightDto), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<FlightDto>> getAll(){
        return new ResponseEntity<>(flightService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{flight_id}")
    public ResponseEntity<FlightDto> getById(@PathVariable("flight_id") Long flightId){
        return new ResponseEntity<>(flightService.getById(flightId), HttpStatus.OK);
    }
}
