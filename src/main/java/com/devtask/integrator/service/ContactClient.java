package com.devtask.integrator.service;

import com.devtask.integrator.model.dto.ContactDto;
import reactor.core.publisher.Mono;

public interface ContactClient {
    /**
     * get all contacts from mockAPI as Mono of ContactDto[]
     * @return <code>Mono<ContactDto[]></code>
     */
    Mono<ContactDto[]> getAllContacts();
}
