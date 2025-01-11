package com.akr.app.services;

import com.akr.app.dtos.PassengerDto;
import com.akr.app.dtos.UpdateEmailRequest;

import java.util.List;

public interface PassengerService {

    PassengerDto register(PassengerDto passengerDto);

    List<PassengerDto> getAll();

    PassengerDto getById(Long passengerId);

    PassengerDto updateEmail(UpdateEmailRequest updateEmailRequest);
}
