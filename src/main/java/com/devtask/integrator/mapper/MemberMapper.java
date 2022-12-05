package com.devtask.integrator.mapper;

import com.devtask.integrator.MemberStatus;
import com.devtask.integrator.model.dto.ContactDto;
import com.devtask.integrator.model.dto.MemberDto;
import com.devtask.integrator.model.dto.MergeFieldsDto;

public class MemberMapper {

    public static MemberDto convertToMemberDto(ContactDto contact){
        MergeFieldsDto mergeFieldsDto = MergeFieldsDto.builder()
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .build();

        return MemberDto.builder()
                .emailAddress(contact.getEmail())
                .status(MemberStatus.SUBSCRIBED.getStatus())
                .mergeFields(mergeFieldsDto)
                .build();
    }

    public static ContactDto convertToContact(MemberDto member){
        return ContactDto.builder()
                .firstName(member.getMergeFields().getFirstName())
                .lastName(member.getMergeFields().getLastName())
                .email(member.getEmailAddress())
                .build();
    }
}
