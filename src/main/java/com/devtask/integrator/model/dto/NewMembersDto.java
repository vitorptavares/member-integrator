package com.devtask.integrator.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class NewMembersDto {
    @JsonProperty("new_members")
    List<MemberDto> NewMembers;

}
