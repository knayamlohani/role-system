package com.locus.roleSystem.service;

import com.locus.roleSystem.exception.BaseException;
import com.locus.roleSystem.request.BaseRequest;
import com.locus.roleSystem.request.ResourceRequest;
import com.locus.roleSystem.response.ResourceRequestResponse;
import com.locus.roleSystem.response.ResourcesResponse;

public interface ResourceService {
    ResourceRequestResponse processResourceRequest(ResourceRequest resourceRequest) throws BaseException;

    ResourcesResponse getAllResources(BaseRequest baseRequest) throws BaseException;
}
