package com.devtask.integrator.service;

import com.devtask.integrator.model.ContactSync;
import com.devtask.integrator.model.dto.ContactDto;

import java.util.List;


public interface ContactService {

    /**
     * retrieves a list of ContactDto
     * @return <code>List<ContactDto></code>
     */
    List<ContactDto> getAllContacts();

    /**
     * executes the sync collecting all contacts from MockAPI and persist them into Mailchimp
     * @return <code>ContactSync</code>
     */
    ContactSync synchronize();


}
