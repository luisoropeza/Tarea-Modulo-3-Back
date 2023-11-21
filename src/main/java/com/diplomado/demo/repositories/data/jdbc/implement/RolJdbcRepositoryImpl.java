package com.diplomado.demo.repositories.data.jdbc.implement;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.diplomado.demo.dtos.Response.UserDetailedWithRolResponse;
import com.diplomado.demo.repositories.data.jdbc.RolJdbcRepository;
import com.diplomado.demo.repositories.data.jdbc.implement.row.mapper.RolRowMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class RolJdbcRepositoryImpl implements RolJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<UserDetailedWithRolResponse> getUsersWithRolByName(String rol) {
        var sql = """
                SELECT
                    u.id,
                    u.username,
                    u."password",
                    u.email,
                    ud.first_name,
                    ud.last_name,
                    ud.age,
                    ud.birth_day,
                    r.name
                FROM
                    users u
                LEFT JOIN user_detail ud ON
                    u.id = ud.user_id
                INNER JOIN user_rol ur ON
                    u.id = ur.user_id
                INNER JOIN rol r ON
                    ur.rol_id = r.id
                WHERE
                    r.name = ?;
                """;

        return jdbcTemplate.query(sql, new RolRowMapper(), rol);
    }
}
