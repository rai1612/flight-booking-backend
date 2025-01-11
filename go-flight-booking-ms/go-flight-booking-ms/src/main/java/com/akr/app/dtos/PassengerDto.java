package com.akr.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {

    private Long passengerId;

    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be null")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only alphabets")
    @Size(min = 3, max = 20, message = "Name should be between 3-20 characters")
    private String firstName;

    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be null")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only alphabets")
    @Size(min = 3, max = 20, message = "Name should be between 3-20 characters")
    private String lastName;

    @Email(message = "Invalid email")
    private String email;

}

