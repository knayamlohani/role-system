package com.locus.roleSystem.service.impl;

import com.locus.roleSystem.constant.Role;
import com.locus.roleSystem.exception.BadRequestException;
import com.locus.roleSystem.exception.BaseException;
import com.locus.roleSystem.request.CreateUserRequest;
import com.locus.roleSystem.request.User;
import com.locus.roleSystem.response.CreateUserResponse;
import com.locus.roleSystem.response.UserRolesResponse;
import com.locus.roleSystem.service.InMemoryDataService;
import com.locus.roleSystem.service.UserHelperService;
import com.locus.roleSystem.service.UserService;
import com.locus.roleSystem.service.UserValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private InMemoryDataService inMemoryDataService;

    @Autowired
    private UserHelperService userHelperService;

    @Autowired
    private UserValidatorService userValidatorService;

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) throws BaseException {

        log.info("received request to create user: {}", createUserRequest);

        userValidatorService.validateCreateUserRequest(createUserRequest);

        if (userHelperService.checkIfUserExists(createUserRequest.getUsername())) {
            throw new BadRequestException("user with username: " + createUserRequest.getUsername() + " already exists");
        }


        return userHelperService.getCreateUserResponseFromUser(
                inMemoryDataService.addUser(
                        userHelperService.getUserFromCreateUserRequest(createUserRequest)
                )
        );
    }


    @Override
    public UserRolesResponse addRoleForUser(String username, Role role) throws BaseException {

        log.info("received request to add role: {} for user with username:  {}", role, username);

        if (!userHelperService.checkIfUserExists(username)) {
            throw new BadRequestException("user with username: " + username + " not found");
        }

        UserRolesResponse userRolesResponse = new UserRolesResponse();
        userRolesResponse.setUsername(username);
        userRolesResponse.setAssignedRoles(inMemoryDataService.addRoleForUser(role, username));
        return userRolesResponse;
    }

    @Override
    public UserRolesResponse removeRoleForUser(String username, Role role) throws BaseException {
        log.info("received request to remove role: {} for user with username:  {}", role, username);

        if (!userHelperService.checkIfUserExists(username)) {
            throw new BadRequestException("user with username: " + username + " not found");
        }

        UserRolesResponse userRolesResponse = new UserRolesResponse();
        userRolesResponse.setUsername(username);
        userRolesResponse.setAssignedRoles(inMemoryDataService.removeRoleForUser(role, username));
        return userRolesResponse;
    }

    @Override
    public UserRolesResponse getRolesOfUser(String username) throws BaseException {
        if (!userHelperService.checkIfUserExists(username)) {
            throw new BadRequestException("user with username: " + username + " not found");
        }

        UserRolesResponse userRolesResponse = new UserRolesResponse();
        userRolesResponse.setUsername(username);
        userRolesResponse.setAssignedRoles(inMemoryDataService.getRolesAssignedToUser(username));
        return userRolesResponse;
    }
}
