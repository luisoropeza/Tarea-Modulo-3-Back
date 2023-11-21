package com.diplomado.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diplomado.demo.dtos.Request.UpdateRolRequest;
import com.diplomado.demo.dtos.Response.UserDetailedWithRolResponse;
import com.diplomado.demo.models.RolEntity;
import com.diplomado.demo.services.RolService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/roles")
@AllArgsConstructor
public class RolController {
    private final RolService rolService;

    @GetMapping(path = "/{id}")
    public RolEntity getRolById(@PathVariable("id") final Long id) {
        return rolService.getRolById(id);
    }

    @GetMapping(path = "/users")
    public List<UserDetailedWithRolResponse> getUserWithRolByName(@RequestParam("name") final String rol) {
        return rolService.getUserWithRolByName(rol);
    }

    @GetMapping
    public List<RolEntity> getAllRoles() {
        return rolService.getAllRoles();
    }

    @PostMapping
    public RolEntity createRol(@RequestBody final RolEntity rol) {
        return rolService.createRol(rol);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteRolById(@PathVariable("id") final Long id) {
        rolService.deleteRolById(id);
    }

    @PutMapping(path = "/{id}")
    public RolEntity updateRol(@PathVariable("id") final Long id, @RequestBody UpdateRolRequest rol){
        return rolService.updateRol(id, rol);
    }
}
