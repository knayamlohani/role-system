package com.locus.roleSystem.controller;

import com.locus.roleSystem.exception.BaseException;
import com.locus.roleSystem.request.BaseRequest;
import com.locus.roleSystem.request.ResourceRequest;
import com.locus.roleSystem.response.ResourceRequestResponse;
import com.locus.roleSystem.response.ResourcesResponse;
import com.locus.roleSystem.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping("/resource")
    public ResponseEntity<ResourceRequestResponse> writeResource(@RequestBody ResourceRequest resourceRequest) throws BaseException {
        return new ResponseEntity<>(resourceService.processResourceRequest(resourceRequest), HttpStatus.OK);
    }

    @GetMapping("/resources")
    public ResponseEntity<ResourcesResponse> getAllResources(@RequestBody BaseRequest baseRequest) throws BaseException {
        return new ResponseEntity<>(resourceService.getAllResources(baseRequest), HttpStatus.OK);
    }
}
