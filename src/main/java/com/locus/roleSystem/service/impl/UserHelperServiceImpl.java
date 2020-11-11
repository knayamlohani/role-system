package com.locus.roleSystem.service.impl;

import com.locus.roleSystem.request.User;
import com.locus.roleSystem.response.CreateUserResponse;
import com.locus.roleSystem.service.UserHelperService;
import com.locus.roleSystem.request.CreateUserRequest;
import com.locus.roleSystem.service.InMemoryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service("UserHelperServiceImpl")
public class UserHelperServiceImpl implements UserHelperService {


    @Autowired
    private InMemoryDataService inMemoryDataService;

    @Override
    public User getUserFromCreateUserRequest(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setUsername(createUserRequest.getUsername());

        return user;
    }


    @Override
    public CreateUserResponse getCreateUserResponseFromUser(User user) {
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setUsername(user.getUsername());

        return createUserResponse;
    }


    @Override
    public boolean checkIfUserExists(String username) {
        return inMemoryDataService.getUserByUserName(username) != null;
    }

}
