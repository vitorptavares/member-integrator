package com.devtask.integrator.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MergeFieldsDto {

    @JsonProperty("FNAME")
    private String firstName;
    @JsonProperty("LNAME")
    private String lastName;
    @JsonProperty("ADDRESS")
    private String address;
    @JsonProperty("PHONE")
    private String phone;
    @JsonProperty("BIRTHDAY")
    private String birthday;
}
