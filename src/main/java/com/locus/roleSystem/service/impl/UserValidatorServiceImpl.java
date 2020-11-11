package com.locus.roleSystem.service.impl;

import com.locus.roleSystem.exception.BaseException;
import com.locus.roleSystem.request.CreateUserRequest;
import com.locus.roleSystem.service.UserValidatorService;
import com.locus.roleSystem.exception.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("UserValidatorServiceImpl")
public class UserValidatorServiceImpl implements UserValidatorService {
    @Override
    public void validateCreateUserRequest(CreateUserRequest createUserRequest) throws BaseException {
        if(createUserRequest == null) {
            throw new BadRequestException("request cannot be null");
        }


        if(StringUtils.isEmpty(createUserRequest.getUsername())) {
            throw new BadRequestException("username cannot be null/empty");
        }
    }
}
