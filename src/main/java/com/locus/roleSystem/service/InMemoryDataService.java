package com.locus.roleSystem.service;

import com.locus.roleSystem.constant.ActionType;
import com.locus.roleSystem.constant.Role;
import com.locus.roleSystem.exception.BaseException;
import com.locus.roleSystem.model.Resource;
import com.locus.roleSystem.request.User;

import java.util.List;
import java.util.Set;

public interface InMemoryDataService {
    User getUserByUserName(String username);

    User addUser(User user);

    Set<Role> addRoleForUser(Role role, String username) throws BaseException;

    Set<Role> removeRoleForUser(Role role, String username) throws BaseException;

    Role getRoleBasedOnActionType(ActionType actionType);

    Set<Role> getRolesAssignedToUser(String username);

    Resource getResourceByResourceId(String resourceId);

    void deleteResourceByResourceId(String resourceId) throws BaseException;

    Resource addResource(Resource resource);

    List<Resource> getAllResources();
}
