package com.akr.app.controllers;

import com.akr.app.dtos.PassengerDto;
import com.akr.app.dtos.UpdateEmailRequest;
import com.akr.app.services.PassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;


    @PostMapping("/register")
    public ResponseEntity<PassengerDto> register(@Valid @RequestBody PassengerDto passengerDto){
        return new ResponseEntity<>(passengerService.register(passengerDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PassengerDto>> getAll(){
        return new ResponseEntity<>(passengerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{passenger_id}")
    public ResponseEntity<PassengerDto> getById(@PathVariable("passenger_id") Long passengerId){
        return new ResponseEntity<>(passengerService.getById(passengerId), HttpStatus.OK);
    }

    @PutMapping("/update/email")
    public ResponseEntity<PassengerDto> updateEmail(@Valid @RequestBody UpdateEmailRequest updateEmailRequest){
        return new ResponseEntity<>(passengerService.updateEmail(updateEmailRequest), HttpStatus.OK);
    }
}
