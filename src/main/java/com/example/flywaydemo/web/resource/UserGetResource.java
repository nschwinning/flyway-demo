package com.example.flywaydemo.web.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Data
public class UserGetResource {

    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

}
