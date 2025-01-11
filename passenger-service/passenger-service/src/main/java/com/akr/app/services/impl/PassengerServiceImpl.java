package com.akr.app.services.impl;

import com.akr.app.dtos.PassengerDto;
import com.akr.app.dtos.UpdateEmailRequest;
import com.akr.app.entities.Passenger;
import com.akr.app.exceptions.ResourceNotFoundException;
import com.akr.app.repos.PassengerRepo;
import com.akr.app.services.PassengerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepo passengerRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PassengerDto register(PassengerDto passengerDto) {

       Passenger entityPassenger = passengerRepo.save(modelMapper.map(passengerDto, Passenger.class));

        return modelMapper.map(entityPassenger, PassengerDto.class);
    }

    @Override
    public List<PassengerDto> getAll() {
        return passengerRepo.findAll().stream().map(passenger -> modelMapper.map(passenger, PassengerDto.class)).collect(Collectors.toList());
    }

    @Override
    public PassengerDto getById(Long passengerId) {
        return modelMapper.map(passengerRepo.findById(passengerId).orElseThrow(() -> new ResourceNotFoundException("passenger", "id", passengerId)), PassengerDto.class);
    }

    @Override
    public PassengerDto updateEmail(UpdateEmailRequest updateEmailRequest) {
        Passenger entityPassenger = passengerRepo.findById(updateEmailRequest.getPassengerId()).orElseThrow(() -> new ResourceNotFoundException("passenger", "id", updateEmailRequest.getPassengerId()));

        entityPassenger.setEmail(updateEmailRequest.getEmail());
        return modelMapper.map(passengerRepo.save(entityPassenger), PassengerDto.class);
    }
}
