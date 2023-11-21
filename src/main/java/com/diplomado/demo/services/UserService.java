package com.diplomado.demo.services;

import java.util.List;

import com.diplomado.demo.dtos.UserDetailedDto;
import com.diplomado.demo.dtos.UserDto;
import com.diplomado.demo.dtos.Request.CreateUserWithRolesRequest;
import com.diplomado.demo.dtos.Request.UpdateUserRequest;

public interface UserService {
    List<UserDto> getAllUsers();

    List<UserDetailedDto> getAllUsersDetailed();

    UserDetailedDto createUser(CreateUserWithRolesRequest dto);

    void deleteUserById(Long id);

    UserDetailedDto editUserById(Long id, UpdateUserRequest dto);
}
