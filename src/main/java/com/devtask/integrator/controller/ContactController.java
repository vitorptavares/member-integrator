package com.devtask.integrator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


public interface ContactController {

     String CONTACT_SYNC_API = "/contacts/sync";

    @GetMapping(value = CONTACT_SYNC_API)
    ResponseEntity<?> synchronizeContacts();

}
