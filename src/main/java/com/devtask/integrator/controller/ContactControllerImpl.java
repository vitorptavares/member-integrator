package com.devtask.integrator.controller;

import com.devtask.integrator.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactControllerImpl implements ContactController{

    private ContactService contactService;

    public ContactControllerImpl(ContactService contactService) {
        this.contactService = contactService;
    }

    public ResponseEntity<?> synchronizeContacts(){
        return new ResponseEntity<>(contactService.synchronize(), HttpStatus.OK);

    }
}
