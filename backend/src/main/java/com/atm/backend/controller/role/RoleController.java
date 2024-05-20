package com.atm.backend.controller.role;

import org.springframework.web.bind.annotation.RestController;

import com.atm.backend.business.dto.RoleDto;
import com.atm.backend.business.services.IRoleService;
import com.atm.backend.data.entity.RoleEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    IRoleService roleService;

    @GetMapping("/all")
    public List<RoleEntity> getAllRole() {
        return roleService.getAllRole();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody RoleDto roleDto) {
        return roleService.addRole(roleDto.getName());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        return roleService.removeRole(id);
    }

}
