package com.atm.backend.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atm.backend.data.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
