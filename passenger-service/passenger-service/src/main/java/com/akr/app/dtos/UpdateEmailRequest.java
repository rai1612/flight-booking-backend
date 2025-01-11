package com.akr.app.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmailRequest {

    @NotNull(message = "Passenger ID cannot be null")
    private Long passengerId;

    @Email(message = "Invalid email")
    private String email;
}
