package com.locus.roleSystem.controller;

import com.locus.roleSystem.constant.Role;
import com.locus.roleSystem.request.CreateUserRequest;
import com.locus.roleSystem.response.BaseResponse;
import com.locus.roleSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    ResponseEntity<BaseResponse> createUser(@RequestBody CreateUserRequest createUserRequest) throws Exception {
        return new ResponseEntity<>(
                userService.createUser(createUserRequest),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/user/{username}/role/{role}")
    ResponseEntity<BaseResponse> addRoleForUser(@PathVariable(name = "username") String username,
                                                @PathVariable(name = "role") Role role) throws Exception {
        return new ResponseEntity<>(
                userService.addRoleForUser(username, role),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/user/{username}/role/{role}")
    ResponseEntity<BaseResponse> removeRoleForUser(@PathVariable(name = "username") String username,
                                                   @PathVariable(name = "role") Role role) throws Exception {
        return new ResponseEntity<>(
                userService.removeRoleForUser(username, role),
                HttpStatus.OK
        );
    }

    @GetMapping("/user/{username}/roles")
    ResponseEntity<BaseResponse> getRolesForUser(@PathVariable(name = "username") String username) throws Exception {
        return new ResponseEntity<>(
                userService.getRolesOfUser(username),
                HttpStatus.OK
        );
    }


}
