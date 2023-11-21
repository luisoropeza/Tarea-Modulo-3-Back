package com.diplomado.demo.services.implement;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.diplomado.demo.dtos.Request.UpdateRolRequest;
import com.diplomado.demo.dtos.Response.UserDetailedWithRolResponse;
import com.diplomado.demo.models.RolEntity;
import com.diplomado.demo.models.UserRolEntity;
import com.diplomado.demo.repositories.data.jdbc.RolJdbcRepository;
import com.diplomado.demo.repositories.data.jpa.RolRepository;
import com.diplomado.demo.repositories.data.jpa.UserRolRepository;
import com.diplomado.demo.services.RolService;
import com.diplomado.demo.services.mapper.RolMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class RolServiceImpl implements RolService {
    private final RolRepository rolRepository;
    private final UserRolRepository userRolRepository;
    private final RolMapper rolMapper;
    private final RolJdbcRepository rolJdbcRepository;

    @Override
    @Transactional(readOnly = true)
    public RolEntity getRolById(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el rol"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolEntity> getAllRoles() {
        return rolRepository.findAll();
    }

    @Override
    public RolEntity createRol(RolEntity rol) {
        if (rol.getName() == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "El nombre del rol no puede ser nulo");
        }
        return rolRepository.save(rol);
    }

    @Override
    public void deleteRolById(Long id) {
        RolEntity rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el rol"));
        List<UserRolEntity> roles = userRolRepository.findAllByRol(rol);
        if (!roles.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "El rol esta asignado a uno o mas usuarios.");
        }
        rolRepository.deleteById(id);
    }

    @Override
    public RolEntity updateRol(Long id, UpdateRolRequest dto) {
        RolEntity rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el rol"));
        return rolRepository.save(rolMapper.rolUpdate(rol, dto));
    }

    @Override
    public List<UserDetailedWithRolResponse> getUserWithRolByName(String name) {
        return rolJdbcRepository.getUsersWithRolByName(name);
    }
}