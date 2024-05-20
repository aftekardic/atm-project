package com.atm.backend.business.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atm.backend.business.dto.RoleDto;
import com.atm.backend.business.services.IBaseService;
import com.atm.backend.business.services.IRoleService;
import com.atm.backend.data.entity.RoleEntity;
import com.atm.backend.data.repository.RoleRepository;

@Service
public class RoleService implements IRoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    IBaseService baseService;

    @Override
    public ResponseEntity<?> addRole(String name) {
        if (roleRepository.findByName(name) != null) {
            return ResponseEntity.status(409).body("Role already exists");
        }

        RoleDto roleDto = new RoleDto();
        roleDto.setName(name);
        roleRepository.save(baseService.roleDtoToEntity(roleDto));

        return ResponseEntity.ok("Role has been added as " + name);
    }

    @Override
    public ResponseEntity<?> removeRole(Long id) {

        try {
            String roleName = roleRepository.findById(id).get().getName();
            roleRepository.deleteById(id);
            return ResponseEntity
                    .ok("Role that name is " + roleName + "(" + id + ")" + " was deleted");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    public List<RoleEntity> getAllRole() {
        roleRepository.findAll();
        return roleRepository.findAll();
    }

}
