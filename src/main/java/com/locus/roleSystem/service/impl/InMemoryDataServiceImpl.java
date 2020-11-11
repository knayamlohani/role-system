package com.locus.roleSystem.service.impl;

import com.locus.roleSystem.constant.ActionType;
import com.locus.roleSystem.constant.Role;
import com.locus.roleSystem.exception.BadRequestException;
import com.locus.roleSystem.exception.BaseException;
import com.locus.roleSystem.exception.NotFoundException;
import com.locus.roleSystem.model.Resource;
import com.locus.roleSystem.request.User;
import com.locus.roleSystem.service.InMemoryDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.*;

@Slf4j
@Service("InMemoryDataServiceImpl")
public class InMemoryDataServiceImpl implements InMemoryDataService {

    private Map<String, User> users = new HashMap<>();


    private Map<String, Set<Role>> userRoles = new HashMap<>();
    private Map<Role, Set<ActionType>> roleActions = new HashMap<>();
    private Map<ActionType, Role> actionTypeToRoleMap = new HashMap<>();

    private Map<String, Resource> resources = new HashMap<>();


    @PostConstruct
    void init() {
        initializeActionTypesBasedOnRole();
        initializeActionTypeToRoleMap();
    }

    private void initializeActionTypesBasedOnRole() {
        roleActions.put(Role.READ_ROLE, new HashSet() {{
            add(ActionType.READ);
        }});

        roleActions.put(Role.WRITE_ROLE, new HashSet() {{
            add(ActionType.WRITE);
        }});

        roleActions.put(Role.DELETE_ROLE, new HashSet() {{
            add(ActionType.DELETE);
        }});
    }


    private void initializeActionTypeToRoleMap() {
        actionTypeToRoleMap.put(ActionType.READ, Role.READ_ROLE);
        actionTypeToRoleMap.put(ActionType.WRITE, Role.WRITE_ROLE);
        actionTypeToRoleMap.put(ActionType.DELETE, Role.DELETE_ROLE);
    }

    @Override
    public User getUserByUserName(String username) {
        return users.get(username);
    }

    @Override
    public User addUser(User user) {
        users.put(user.getUsername(), user);
        return user;
    }


    @Override
    public Set<Role> addRoleForUser(Role role, String username) throws BaseException {

        userRoles.computeIfAbsent(username, (k) -> new HashSet<>());

        boolean roleAdded = userRoles.get(username).add(role);

        if (!roleAdded) {
            throw new BadRequestException(role + " already exists for user with username: " + username);
        }
        return userRoles.get(username);
    }

    @Override
    public Set<Role> removeRoleForUser(Role role, String username) throws BaseException {
        boolean roleRemoved = userRoles.get(username).remove(role);

        if (!roleRemoved) {
            throw new NotFoundException(role + " does not exists for user with username: " + username);
        }
        return userRoles.get(username);
    }


    @Override
    public Role getRoleBasedOnActionType(ActionType actionType) {
        return actionTypeToRoleMap.get(actionType);
    }


    @Override
    public Set<Role> getRolesAssignedToUser(String username) {
        return Optional.ofNullable(userRoles.get(username)).orElse(new HashSet<>());
    }


    @Override
    public Resource getResourceByResourceId(String resourceId) {
        return resources.get(resourceId);
    }

    @Override
    public void deleteResourceByResourceId(String resourceId) throws BaseException{
        Resource resource =  resources.remove(resourceId);

        if(resource == null) {
            throw new BadRequestException("resource with id: " + resourceId + " not found");
        }
    }

    @Override
    public Resource addResource(Resource resource) {
        resources.put(resource.getResourceId(), resource);
        return resource;
    }


    @Override
    public List<Resource> getAllResources() {
        return new ArrayList<>(resources.values());
    }


}
