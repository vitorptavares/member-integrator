package com.devtask.integrator.model.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Builder
@Getter
public class MembersDto {
    List<MemberDto> members;
}
