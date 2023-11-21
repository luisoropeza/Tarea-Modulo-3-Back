package com.diplomado.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diplomado.demo.dtos.UserDetailedDto;
import com.diplomado.demo.dtos.Request.AssignRolesToUserRequest;
import com.diplomado.demo.dtos.Request.CreateUserWithRolesRequest;
import com.diplomado.demo.dtos.Request.UpdateUserRequest;
import com.diplomado.demo.dtos.Request.UpdateUserRolActiveRequest;
import com.diplomado.demo.services.UserRolService;
import com.diplomado.demo.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRolService userRolService;

    @GetMapping
    public List<?> getAllUsers(
            @RequestParam(required = false, defaultValue = "false") boolean detailed) {
        return userService.getAllUsers(detailed);
    }

    @PostMapping
    public UserDetailedDto createUser(@RequestBody final CreateUserWithRolesRequest dto) {
        return userService.createUser(dto);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUserById(@PathVariable("id") final Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping(path = "/{id}")
    public UserDetailedDto editUserById(@PathVariable("id") final Long id,
            @RequestBody final UpdateUserRequest dto) {
        return userService.editUserById(id, dto);
    }

    @PostMapping(path = "/{id}")
    public void assignRolesToUser(@PathVariable("id") Long id,
            @RequestBody AssignRolesToUserRequest dto) {
        userRolService.assignRolesToUser(id, dto.getRolesId());
    }

    @PatchMapping(path = "/{id}")
    public void patchUserRolActive(@PathVariable("id") Long userId, @RequestBody UpdateUserRolActiveRequest dto) {
        userRolService.patchUserRolActive(userId, dto);
    }
}
