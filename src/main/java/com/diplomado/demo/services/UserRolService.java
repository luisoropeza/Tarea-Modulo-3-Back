package com.diplomado.demo.services;

import java.util.List;

import com.diplomado.demo.dtos.Request.UpdateUserRolActiveRequest;

public interface UserRolService {
    void assignRolesToUser(Long userId, List<Long> rolesId);

    void patchUserRolActive(Long userId, UpdateUserRolActiveRequest dto);
}
