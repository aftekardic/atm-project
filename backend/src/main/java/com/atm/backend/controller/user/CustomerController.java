package com.atm.backend.controller.user;

import org.springframework.web.bind.annotation.RestController;

import com.atm.backend.business.services.ICustomerService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/user")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @GetMapping("/get-info/{id}")
    public ResponseEntity<?> getMethodName(@PathVariable Long id) {
        System.out.println(customerService.getInfo(id));
        return ResponseEntity.ok().body(Map.of("info", customerService.getInfo(id)));
    }

}
