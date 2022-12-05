package com.devtask.integrator.service;

import com.devtask.integrator.model.dto.MembersDto;
import com.devtask.integrator.model.dto.NewMembersDto;
import reactor.core.publisher.Mono;

public interface MailChimpClient {

    /**
     * Persists a <code>MembersDto</code> composed by a list of <code>MemberDto</code>
     * @param contactList
     * @return <code>Mono<NewMembersDto></code>
     */
    Mono<NewMembersDto> saveAllContacts(MembersDto contactList);
}
