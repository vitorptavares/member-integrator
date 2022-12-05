package com.devtask.integrator.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDto {
    private String id;
    @JsonProperty("email_address")
    private String emailAddress;
    @JsonProperty("unique_email_id")
    private String uniqueEmailId;
    @JsonProperty("contact_id")
    private String contactId;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("web_id")
    private Long webId;
    @JsonProperty("consents_to_one_to_one_messaging")
    private Boolean consentsToOneMessaging;
    private String status;
    @JsonProperty("merge_fields")
    private MergeFieldsDto mergeFields;
}
