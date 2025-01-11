package com.akr.app.repos;

import com.akr.app.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {

    public List<Booking> findByPassengerId(Long passengerId);
}
