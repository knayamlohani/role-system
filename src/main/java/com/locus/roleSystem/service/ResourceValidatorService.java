package com.locus.roleSystem.service;

import com.locus.roleSystem.exception.BadRequestException;
import com.locus.roleSystem.request.ResourceRequest;

public interface ResourceValidatorService {
    void validateResourceRequest(ResourceRequest resourceRequest) throws BadRequestException;
}
