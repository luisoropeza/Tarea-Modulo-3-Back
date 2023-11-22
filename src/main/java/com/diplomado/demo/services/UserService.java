package com.diplomado.demo.services;

import java.util.List;

import com.diplomado.demo.dtos.Request.CreateUserWithRolesRequest;
import com.diplomado.demo.dtos.Request.UpdateUserRequest;
import com.diplomado.demo.dtos.Response.UserDetailedResponse;

public interface UserService {
    List<?> getAllUsers(Boolean detailed);

    UserDetailedResponse createUser(CreateUserWithRolesRequest dto);

    void deleteUserById(Long id);

    UserDetailedResponse editUserById(Long id, UpdateUserRequest dto);
}
