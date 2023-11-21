package com.diplomado.demo.repositories.data.jdbc;

import java.util.List;

import com.diplomado.demo.dtos.Response.UserDetailedWithRolResponse;

public interface RolJdbcRepository {
    public List<UserDetailedWithRolResponse> getUsersWithRolByName(String rol);
}
