package com.locus.roleSystem.service;

import com.locus.roleSystem.request.CreateUserRequest;
import com.locus.roleSystem.request.User;
import com.locus.roleSystem.response.CreateUserResponse;

public interface UserHelperService {
    User getUserFromCreateUserRequest(CreateUserRequest createUserRequest);
    CreateUserResponse getCreateUserResponseFromUser(User user);
    boolean checkIfUserExists(String username);
}
