package com.devtask.integrator.service.impl;

import com.devtask.integrator.mapper.MemberMapper;
import com.devtask.integrator.model.ContactSync;
import com.devtask.integrator.model.dto.ContactDto;
import com.devtask.integrator.model.dto.MemberDto;
import com.devtask.integrator.model.dto.MembersDto;
import com.devtask.integrator.model.dto.NewMembersDto;
import com.devtask.integrator.service.ContactClient;
import com.devtask.integrator.service.MailChimpClient;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ContactServiceImplTest {

    @InjectMocks
    ContactServiceImpl contactService;

    @Mock
    ContactClient contactClient;

    @Mock
    MailChimpClient mailChimpClient;

    ContactDto[] contacts;

    @BeforeEach
    public void setup(){
        contactClient = Mockito.mock(ContactClient.class);
        mailChimpClient = Mockito.mock(MailChimpClient.class);
        contactService = new ContactServiceImpl(contactClient, mailChimpClient);
    }


    @Test
    void test_get_all_contacts() {
        //given
        ContactDto contact1 = ContactDto.builder().firstName("Jhon").lastName("Doe").email("jhondoe@gmail.com").build();
        ContactDto contact2 = ContactDto.builder().firstName("Edward").lastName("Paul").email("edpaule@gmail.com").build();
        ContactDto[] contacts = {contact1, contact2};
        //when
        Mono<ContactDto[]> mono =  Mono.just(contacts);
        when(contactClient.getAllContacts()).thenReturn(mono);
        //then
        assertEquals(Arrays.asList(contacts),contactService.getAllContacts());
    }

    @Test
    void test_synchronize() {

        //given
        ContactDto contact1 = ContactDto.builder().firstName("Jhon").lastName("Doe").email("jhondoe@gmail.com").build();
        ContactDto contact2 = ContactDto.builder().firstName("Edward").lastName("Paul").email("edpaule@gmail.com").build();
        ContactDto[] contacts = {contact1, contact2};
        List<MemberDto> members = Arrays.stream(contacts).map(MemberMapper::convertToMemberDto).collect(Collectors.toList());
        NewMembersDto newMembersDto = new NewMembersDto();
        newMembersDto.setNewMembers(members);
        Mono<ContactDto[]> contactMono =  Mono.just(contacts);
        Mono<NewMembersDto> newMemberMono = Mono.just(newMembersDto);
        ContactSync expectedContactSync = ContactSync.builder().contactList(Arrays.stream(contacts).collect(Collectors.toList())).syncedContacts(2).build();
        //when
        when(contactClient.getAllContacts()).thenReturn(contactMono);
        when(mailChimpClient.saveAllContacts(any(MembersDto.class))).thenReturn(newMemberMono);
        ContactSync response = contactService.synchronize();
        //then
        assertEquals(expectedContactSync.getSyncedContacts(), response.getSyncedContacts());
        assertEquals(expectedContactSync.getContactList().get(0).getFirstName(), response.getContactList().get(0).getFirstName());
        assertEquals(expectedContactSync.getContactList().get(0).getLastName(), response.getContactList().get(0).getLastName());
        assertEquals(expectedContactSync.getContactList().get(0).getEmail(), response.getContactList().get(0).getEmail());
    }
}