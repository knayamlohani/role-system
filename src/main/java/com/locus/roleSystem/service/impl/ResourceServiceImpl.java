package com.locus.roleSystem.service.impl;

import com.fasterxml.uuid.Generators;
import com.locus.roleSystem.constant.ActionType;
import com.locus.roleSystem.exception.AuthorizationFailureException;
import com.locus.roleSystem.exception.BadRequestException;
import com.locus.roleSystem.exception.BaseException;
import com.locus.roleSystem.exception.NotFoundException;
import com.locus.roleSystem.model.Resource;
import com.locus.roleSystem.request.BaseRequest;
import com.locus.roleSystem.request.ResourceRequest;
import com.locus.roleSystem.response.ResourceRequestResponse;
import com.locus.roleSystem.response.ResourcesResponse;
import com.locus.roleSystem.service.InMemoryDataService;
import com.locus.roleSystem.service.ResourceService;
import com.locus.roleSystem.service.ResourceValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("ResourceServiceImpl")
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private InMemoryDataService inMemoryDataService;


    @Autowired
    private ResourceValidatorService resourceValidatorService;

    @Override
    public ResourceRequestResponse processResourceRequest(ResourceRequest resourceRequest) throws BaseException {

        log.info(" received resource request : {}", resourceRequest);

        resourceValidatorService.validateResourceRequest(resourceRequest);

        ResourceRequestResponse resourceRequestResponse = new ResourceRequestResponse();
        checkIfUserPermittedToPerformAction(resourceRequest.getUsername(), resourceRequest.getActionType());

        switch (resourceRequest.getActionType()) {
            case READ:
                resourceRequestResponse.setResource(processReadResourceRequest(resourceRequest));

                if (resourceRequestResponse.getResource() == null) {
                    throw new NotFoundException("Resource with id: " + resourceRequest.getResource().getResourceId() + " not found");
                }
                break;

            case WRITE:
                resourceRequestResponse.setResource(processWriteResourceRequest(resourceRequest));
                break;

            case DELETE:
                processDeleteResourceRequest(resourceRequest);
                break;
        }


        return resourceRequestResponse;
    }

    @Override
    public ResourcesResponse getAllResources(BaseRequest baseRequest) throws BaseException {
        checkIfUserPermittedToPerformAction(baseRequest.getUsername(), ActionType.READ);
        ResourcesResponse resourcesResponse = new ResourcesResponse();

        resourcesResponse.setResources(inMemoryDataService.getAllResources());
        return resourcesResponse;
    }


    private Resource processReadResourceRequest(ResourceRequest resourceRequest) throws BaseException {
        return inMemoryDataService.getResourceByResourceId(resourceRequest.getResource().getResourceId());
    }

    private Resource processWriteResourceRequest(ResourceRequest resourceRequest) throws BaseException {
        Resource resource = resourceRequest.getResource();
        resource.setResourceId(Generators.timeBasedGenerator().generate().toString());
        return inMemoryDataService.addResource(resource);
    }

    private void processDeleteResourceRequest(ResourceRequest resourceRequest) throws BaseException {
        inMemoryDataService.deleteResourceByResourceId(resourceRequest.getResource().getResourceId());
    }

    private void checkIfUserPermittedToPerformAction(String username, ActionType actionType) throws BaseException {

        if (inMemoryDataService.getUserByUserName(username) == null) {
            throw new BadRequestException("User with username: " + username + " not found");
        }


        if (!inMemoryDataService.getRolesAssignedToUser(username)
                .contains(inMemoryDataService.getRoleBasedOnActionType(actionType))) {
            throw new AuthorizationFailureException("User with username: " + username +
                    " is not authorized to perform " + actionType + " action");
        }

    }


}
