package com.atm.backend.controller.user;

import org.springframework.web.bind.annotation.RestController;

import com.atm.backend.business.dto.DepositRequestDto;
import com.atm.backend.business.dto.UserDto;
import com.atm.backend.business.dto.WithdrawRequestDto;
import com.atm.backend.business.services.ICustomerService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/user")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @GetMapping("/info/{id}")
    public ResponseEntity<?> getUserInfoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(Map.of("info", customerService.getInfo(id)));
    }

    @GetMapping("/amount/{id}")
    public ResponseEntity<?> getUserAmountById(@PathVariable Long id) {
        return ResponseEntity.ok().body(Map.of("amount", customerService.getAmountById(id)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserInformations(@PathVariable Long id, @RequestBody UserDto userDto) {
        return customerService.updateUserById(id, userDto);
    }

    @PutMapping("/deposit/{id}")
    public ResponseEntity<?> updateUserAmountByDeposit(@PathVariable Long id, @RequestBody DepositRequestDto deposit) {
        return customerService.depositById(id, deposit.getDeposit());
    }

    @PutMapping("/withdraw/{id}")
    public ResponseEntity<?> updateUserAmountByWithdraw(@PathVariable Long id,
            @RequestBody WithdrawRequestDto withdraw) {
        return customerService.withdrawById(id, withdraw.getWithdraw());
    }
}
