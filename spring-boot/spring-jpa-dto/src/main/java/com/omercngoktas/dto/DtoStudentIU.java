package com.omercngoktas.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoStudentIU {

    @NotEmpty(message = "First name cannot be null.")
    @Size(min = 2, max = 50, message = "First name size must be 2-50.")
    private String firstName;

    @NotBlank(message = "Last name cannot be null.")
    @Size(min = 2, max = 50, message = "Last name size must be 2-50.")
    private String lastName;

    @NotBlank(message = "Email cannot be null.")
    @Email(message = "Provide valid email address.")
    private String email;

    @Past(message = "Date of birth must be in the past.")
    private LocalDate dateOfBirth;
}
