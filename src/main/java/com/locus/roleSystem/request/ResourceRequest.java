package com.locus.roleSystem.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.locus.roleSystem.constant.ActionType;
import com.locus.roleSystem.model.Resource;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceRequest {
    private Resource resource;
    private ActionType actionType;
    private String username;

}
