package com.atm.backend.business.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.atm.backend.data.entity.RoleEntity;

public interface IRoleService {

    public List<RoleEntity> getAllRole();

    public ResponseEntity<?> addRole(String name);

    public ResponseEntity<?> removeRole(Long id);

}
