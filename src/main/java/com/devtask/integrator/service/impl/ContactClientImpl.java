package com.devtask.integrator.service.impl;

import com.devtask.integrator.model.dto.ContactDto;
import com.devtask.integrator.service.ContactClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ContactClientImpl implements ContactClient {
    private final String MOCK_API_URL = "https://challenge.trio.dev";
    private static final String MOCK_GET_CONTACTS_URI = "/api/v1/contacts";

    private final WebClient webClient;

    public ContactClientImpl(WebClient.Builder builder) {
        this.webClient = builder.baseUrl(MOCK_API_URL)
                .build();
    }

    @Override
    public Mono<ContactDto[]>  getAllContacts() {
        return webClient.get()
                .uri(MOCK_GET_CONTACTS_URI)
                .retrieve()
                .bodyToMono(ContactDto[].class);

    }
}
