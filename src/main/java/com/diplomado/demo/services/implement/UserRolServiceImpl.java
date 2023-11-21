package com.diplomado.demo.services.implement;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.diplomado.demo.dtos.Request.UpdateUserRolActiveRequest;
import com.diplomado.demo.models.RolEntity;
import com.diplomado.demo.models.UserEntity;
import com.diplomado.demo.models.UserRolEntity;
import com.diplomado.demo.repositories.data.jpa.RolRepository;
import com.diplomado.demo.repositories.data.jpa.UserRepository;
import com.diplomado.demo.repositories.data.jpa.UserRolRepository;
import com.diplomado.demo.services.UserRolService;
import com.diplomado.demo.services.mapper.UserRolMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class UserRolServiceImpl implements UserRolService {
    private final UserRolRepository userRolRepository;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final UserRolMapper userRolMapper;

    @Override
    public void assignRolesToUser(Long userId, List<Long> rolesId) {
        List<RolEntity> roles = rolRepository.findAllById(rolesId);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario"));
        List<UserRolEntity> userRoles = userRolRepository.findAllByUser(user);
        if (userRoles.isEmpty()) {
            for (RolEntity rol : roles) {
                userRolRepository.save(userRolMapper.toUserRolEntity(rol, user));
            }
        } else {
            for (RolEntity rol : roles) {
                boolean roleAlreadyAssigned = userRoles.stream()
                        .anyMatch(userRol -> userRol.getRol().getId().equals(rol.getId()));
                if (roleAlreadyAssigned) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT,
                            "El usuario ya tiene asignado el rol con ID: " + rol.getId());
                } else {
                    userRolRepository.save(userRolMapper.toUserRolEntity(rol, user));
                }
            }
        }
    }

    @Override
    public void patchUserRolActive(Long userId, UpdateUserRolActiveRequest dto) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario")));
        RolEntity rol = rolRepository.findById(dto.getRolId())
                .orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el rol")));
        List<UserRolEntity> userRoles = userRolRepository.findAllByUser(user);
        boolean foundMatch = false;
        for (UserRolEntity userRol : userRoles) {
            if (userRol.getRol().equals(rol)) {
                userRol.setActive(dto.getActive());
                userRolRepository.save(userRol);
                foundMatch = true;
            }
        }
        if (!foundMatch) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay coincidencia con el usuario y el rol");
        }
    }
}
