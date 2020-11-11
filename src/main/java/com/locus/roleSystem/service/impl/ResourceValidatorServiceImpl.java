package com.locus.roleSystem.service.impl;

import com.locus.roleSystem.constant.ActionType;
import com.locus.roleSystem.exception.BadRequestException;
import com.locus.roleSystem.request.ResourceRequest;
import com.locus.roleSystem.service.ResourceValidatorService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("ResourceValidatorServiceImpl")
public class ResourceValidatorServiceImpl implements ResourceValidatorService {

    @Override
    public void validateResourceRequest(ResourceRequest resourceRequest) throws BadRequestException {
        if (resourceRequest == null) {
            throw new BadRequestException("resourceRequest cannot be null");
        }


        if (resourceRequest.getActionType() == null) {
            throw new BadRequestException("action type be null");
        }

        if (StringUtils.isEmpty(resourceRequest.getUsername())) {
            throw new BadRequestException("username cannot be null/empty");
        }

        if (resourceRequest.getResource() == null) {
            throw new BadRequestException("resource cannot be null");
        }

        if (resourceRequest.getActionType() == ActionType.READ && StringUtils.isEmpty(resourceRequest.getResource().getResourceId())) {
            throw new BadRequestException("resource id cannot be null/empty");
        }

        if (resourceRequest.getActionType() == ActionType.WRITE && StringUtils.isEmpty(resourceRequest.getResource().getContent())) {
            throw new BadRequestException("resource content cannot be null/empty");
        }
    }

}
