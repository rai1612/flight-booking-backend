package com.akr.app.dtos;

import com.akr.app.utils.BookingStatus;
import com.akr.app.utils.CheckInStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private Long bookingId;

    @NotNull(message = "Flight IDs cannot be null")
    @NotEmpty(message = "Flight IDs cannot be empty")
    private List<Long> flightIds;

    @NotNull(message = "Passenger ID cannot be null")
    private Long passengerId;

    private String bookingDate = LocalDate.now().toString();

    @NotNull(message = "Total price cannot be null")
    @Min(value = 0, message = "Total price cannot be less than 0")
    private Double totalPrice;

    private BookingStatus bookingStatus = BookingStatus.CONFIRMED;

    private CheckInStatus checkInStatus = CheckInStatus.NOT_CHECKED_IN;

}
