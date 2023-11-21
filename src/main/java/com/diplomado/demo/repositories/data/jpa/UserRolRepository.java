package com.diplomado.demo.repositories.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diplomado.demo.models.RolEntity;
import com.diplomado.demo.models.UserEntity;
import com.diplomado.demo.models.UserRolEntity;
import java.util.List;

@Repository
public interface UserRolRepository extends JpaRepository<UserRolEntity, Long> {
    List<UserRolEntity> findAllByUser(UserEntity user);

    List<UserRolEntity> findAllByRol(RolEntity rol);
}
