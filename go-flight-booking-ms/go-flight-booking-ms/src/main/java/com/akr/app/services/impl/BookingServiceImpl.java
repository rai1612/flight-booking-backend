package com.akr.app.services.impl;

import com.akr.app.dtos.BookingDto;
import com.akr.app.dtos.FlightDto;
import com.akr.app.dtos.FlightPassengerDto;
import com.akr.app.dtos.PassengerDto;
import com.akr.app.entities.Booking;
import com.akr.app.exceptions.ResourceNotFoundException;
import com.akr.app.feignclients.BookingServiceClient;
import com.akr.app.repos.BookingRepo;
import com.akr.app.services.BookingService;
import com.akr.app.utils.BookingStatus;
import com.akr.app.utils.CheckInStatus;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BookingServiceClient bookingServiceClient;

    @Override
    @CircuitBreaker(name = "bookCircuitBreaker", fallbackMethod = "bookFallback")
    public FlightPassengerDto book(BookingDto bookingDto) {
        // for checking if flight exists
        bookingDto.getFlightIds().forEach(bookingServiceClient::getFlightById);
//        bookingServiceClient.getFlightById(bookingDto.getFlightId());
        // for checking if passenger exists
        bookingServiceClient.getPassengerById(bookingDto.getPassengerId());

        Booking entityBooking = bookingRepo.save(modelMapper.map(bookingDto, Booking.class));
        BookingDto dtoBooking = modelMapper.map(entityBooking, BookingDto.class);

        return getFlightPassengerDto(dtoBooking);
    }
    public FlightPassengerDto bookFallback(BookingDto bookingDto, Exception ex) {
        System.out.println("Fallback due to" + ex.getMessage());
        return new FlightPassengerDto(null, null, null, null, 0, null, null);
    }
    @Override
    public FlightPassengerDto checkIn(Long bookingId) {
        Booking entityBooking = bookingRepo.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("booking", "id", bookingId));
        entityBooking.setCheckInStatus(CheckInStatus.CHECKED_IN);
        BookingDto dtoBooking = modelMapper.map(bookingRepo.save(entityBooking), BookingDto.class);

        return getFlightPassengerDto(dtoBooking);

    }

    @Override
    public FlightPassengerDto cancel(Long bookingId) {
        Booking entityBooking = bookingRepo.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("booking", "id", bookingId));
        entityBooking.setBookingStatus(BookingStatus.CANCELLED);

        BookingDto dtoBooking = modelMapper.map(bookingRepo.save(entityBooking), BookingDto.class);
        return getFlightPassengerDto(dtoBooking);

    }

    @Override
    @CircuitBreaker(name = "getByPassengerIdCircuitBreaker", fallbackMethod = "getByPassengerIdFallback")
    public List<FlightPassengerDto> getByPassengerId(Long passengerId) {
        // for checking if passenger exists
        bookingServiceClient.getPassengerById(passengerId);
        return bookingRepo.findByPassengerId(passengerId)
                .stream()
                .map(booking -> modelMapper.map(booking, BookingDto.class))
                .map(this::getFlightPassengerDto)
                .collect(Collectors.toList());
    }
    public List<FlightPassengerDto> getByPassengerIdFallback(Long passengerId, Exception ex){
        return List.of(new FlightPassengerDto(null, null, null, null, 0, null, null));
    }
    @Override
    public FlightPassengerDto getById(Long bookingId) {
        BookingDto dtoBooking = modelMapper.map(bookingRepo.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("booking", "id", bookingId)), BookingDto.class);
        return getFlightPassengerDto(dtoBooking);
    }

    @Override
    public List<FlightPassengerDto> getAll() {
        return bookingRepo.findAll()
                .stream()
                .map(booking -> modelMapper.map(booking, BookingDto.class))
                .map(this::getFlightPassengerDto)
                .collect(Collectors.toList());
    }

    @CircuitBreaker(name = "getFlightPassengerDtoCircuitBreaker", fallbackMethod = "getFlightPassengerDtoFallback")
    private FlightPassengerDto getFlightPassengerDto(BookingDto dtoBooking) {
        List<FlightDto> flightDtos = dtoBooking.getFlightIds()
                .stream()
                .map(bookingServiceClient::getFlightById)
                .map(ResponseEntity::getBody)
                .collect(Collectors.toList());

        PassengerDto passengerDto = bookingServiceClient.getPassengerById(dtoBooking.getPassengerId()).getBody();
        return getFlightPassengerDto(dtoBooking, flightDtos, passengerDto);
    }

    private FlightPassengerDto getFlightPassengerDtoFallback(BookingDto dtoBooking, Exception ex){
        System.out.println("Fallback due to" + ex.getMessage());
        return new FlightPassengerDto(null, null, null, null, 0, null, null);
    }
    private static FlightPassengerDto getFlightPassengerDto(BookingDto dtoBooking, List<FlightDto> flightDtos, PassengerDto passengerDto) {
        FlightPassengerDto flightPassengerDto = new FlightPassengerDto();
        flightPassengerDto.setBookingId(dtoBooking.getBookingId());
        flightPassengerDto.setFlights(flightDtos);
        flightPassengerDto.setPassenger(passengerDto);
        flightPassengerDto.setBookingDate(dtoBooking.getBookingDate());
        flightPassengerDto.setTotalPrice(dtoBooking.getTotalPrice());
        flightPassengerDto.setBookingStatus(dtoBooking.getBookingStatus());
        flightPassengerDto.setCheckInStatus(dtoBooking.getCheckInStatus());
        return flightPassengerDto;
    }

}
