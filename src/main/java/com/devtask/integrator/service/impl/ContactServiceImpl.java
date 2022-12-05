package com.devtask.integrator.service.impl;

import com.devtask.integrator.mapper.MemberMapper;
import com.devtask.integrator.model.ContactSync;
import com.devtask.integrator.model.dto.ContactDto;
import com.devtask.integrator.model.dto.MemberDto;
import com.devtask.integrator.model.dto.MembersDto;
import com.devtask.integrator.model.dto.NewMembersDto;
import com.devtask.integrator.service.ContactClient;
import com.devtask.integrator.service.ContactService;
import com.devtask.integrator.service.MailChimpClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactClient contactClient;
    private final MailChimpClient mailChimpClient;

    public ContactServiceImpl(ContactClient contactClient, MailChimpClient mailChimpClient) {
        this.contactClient = contactClient;
        this.mailChimpClient = mailChimpClient;
    }


    @Override
    public List<ContactDto> getAllContacts() {

        Mono<ContactDto[]> response = contactClient.getAllContacts();
        ContactDto[] contacts = response.block();
        return Arrays.asList(contacts);
    }

    public ContactSync synchronize(){
        List<MemberDto> allMembers = getAllContacts().stream()
               .map(MemberMapper::convertToMemberDto)
               .collect(Collectors.toList());

        NewMembersDto members=  mailChimpClient.saveAllContacts(MembersDto.builder().members(allMembers).build()).block();
        List<ContactDto> newMembers = members.getNewMembers().stream()
                .map(MemberMapper::convertToContact)
                .collect(Collectors.toList());

        return ContactSync.builder()
                .contactList(newMembers)
                .syncedContacts(newMembers.size())
                .build();

    }
}
