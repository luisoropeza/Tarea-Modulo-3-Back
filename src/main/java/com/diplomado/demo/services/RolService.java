package com.diplomado.demo.services;

import java.util.List;

import com.diplomado.demo.dtos.Request.UpdateRolRequest;
import com.diplomado.demo.dtos.Response.UserDetailedWithRolResponse;
import com.diplomado.demo.models.RolEntity;

public interface RolService {
    RolEntity getRolById(Long id);

    List<RolEntity> getAllRoles();

    RolEntity createRol(RolEntity rol);

    void deleteRolById(Long id);

    RolEntity updateRol(Long id, UpdateRolRequest dto);

    List<UserDetailedWithRolResponse> getUsersByRol(String rol);
}
