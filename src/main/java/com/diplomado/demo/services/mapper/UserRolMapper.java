package com.diplomado.demo.services.mapper;

import org.springframework.stereotype.Component;

import com.diplomado.demo.models.RolEntity;
import com.diplomado.demo.models.UserEntity;
import com.diplomado.demo.models.UserRolEntity;

@Component
public class UserRolMapper {
    public UserRolEntity toUserRolEntity(RolEntity rol, UserEntity user){
        UserRolEntity userRol = new UserRolEntity();
        userRol.setUser(user);
        userRol.setRol(rol);
        userRol.setActive(true);
        return userRol;
    }
}
