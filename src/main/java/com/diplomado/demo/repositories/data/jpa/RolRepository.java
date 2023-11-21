package com.diplomado.demo.repositories.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diplomado.demo.models.RolEntity;


@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {
    RolEntity findByName(String name);
}
