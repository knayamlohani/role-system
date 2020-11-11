package com.locus.roleSystem.service;

import com.locus.roleSystem.exception.BaseException;
import com.locus.roleSystem.request.CreateUserRequest;

public interface UserValidatorService {
    void validateCreateUserRequest(CreateUserRequest createUserRequest) throws BaseException;
}
