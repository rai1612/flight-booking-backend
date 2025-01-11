package com.akr.app.entities;

import com.akr.app.utils.BookingStatus;
import com.akr.app.utils.CheckInStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;

    private List<Long> flightIds;

    private Long passengerId;

    private String bookingDate = LocalDate.now().toString();

    private Double totalPrice;

    private BookingStatus bookingStatus = BookingStatus.CONFIRMED;

    private CheckInStatus checkInStatus = CheckInStatus.NOT_CHECKED_IN;

}
