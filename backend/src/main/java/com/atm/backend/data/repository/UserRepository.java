package com.atm.backend.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atm.backend.data.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
