package com.devtask.integrator.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Contact {
    private String firstName;
    private String lastName;
    private String email;
}
