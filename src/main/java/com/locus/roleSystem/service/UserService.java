package com.locus.roleSystem.service;

import com.locus.roleSystem.constant.Role;
import com.locus.roleSystem.exception.BaseException;
import com.locus.roleSystem.request.CreateUserRequest;
import com.locus.roleSystem.response.*;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest createUserRequest) throws BaseException;

    UserRolesResponse addRoleForUser(String username, Role role) throws BaseException;

    UserRolesResponse removeRoleForUser(String username, Role role) throws BaseException;

    UserRolesResponse getRolesOfUser(String username) throws BaseException;
}
