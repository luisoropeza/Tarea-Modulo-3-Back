package com.diplomado.demo.services.mapper;

import org.springframework.stereotype.Component;

import com.diplomado.demo.dtos.Request.UpdateRolRequest;
import com.diplomado.demo.models.RolEntity;

@Component
public class RolMapper {
    public RolEntity rolUpdate(RolEntity rol, UpdateRolRequest dto){
        rol.setName(dto.getName());
        return rol;
    }
}
