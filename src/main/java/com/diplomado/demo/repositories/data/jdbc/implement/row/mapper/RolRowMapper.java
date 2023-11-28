package com.diplomado.demo.repositories.data.jdbc.implement.row.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.diplomado.demo.dtos.Response.UserDetailedWithRolResponse;

public class RolRowMapper implements RowMapper<UserDetailedWithRolResponse> {
    @Override
    public UserDetailedWithRolResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserDetailedWithRolResponse(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getInt("age"),
                rs.getDate("birth_day").toLocalDate(),
                rs.getString("name"));
    }
}
