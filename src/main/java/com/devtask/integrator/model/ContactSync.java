package com.devtask.integrator.model;

import com.devtask.integrator.model.dto.ContactDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ContactSync {


    private long syncedContacts;
    @JsonProperty("contacts")
    private List<ContactDto> contactList;

}
