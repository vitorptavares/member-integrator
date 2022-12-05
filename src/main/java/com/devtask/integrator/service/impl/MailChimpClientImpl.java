package com.devtask.integrator.service.impl;

import com.devtask.integrator.model.dto.MembersDto;
import com.devtask.integrator.model.dto.NewMembersDto;
import com.devtask.integrator.service.MailChimpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class MailChimpClientImpl implements MailChimpClient {

    @Value("${mailchimp.username}")
    private String username;

    @Value("${mailchimp.password}")
    private String token;

    @Value("${mailchimp.listId}")
    private String listId;

    private final String CHIMP_URL = "https://us8.api.mailchimp.com/3.0/";

    private final WebClient webClient;

    public MailChimpClientImpl(WebClient.Builder builder) {
        this.webClient = builder.baseUrl(CHIMP_URL)
                .build();
    }

    @Override
    public Mono<NewMembersDto> saveAllContacts(MembersDto members) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path( "lists/" + listId)
                        .queryParam("skip_merge_validation", "false")
                        .queryParam("skip_duplicate_check", "false").build())
                .body(BodyInserters.fromValue(members))
                .header("Authorization", "Basic " + Base64Utils
                        .encodeToString((username + ":" + token).getBytes(UTF_8)))
                .header("Content-Type","application/json" )
                .retrieve()
                .bodyToMono(NewMembersDto.class);
    }
}
