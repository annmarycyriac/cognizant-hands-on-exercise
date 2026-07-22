package com.cognizant.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    @GetMapping("/accounts/{id}")
    public Map<String, Object> getAccount(@PathVariable String id) {
        Map<String, Object> account = new HashMap<>();
        account.put("accountId", id);
        account.put("accountType", "Savings");
        account.put("balance", 50000.0);
        account.put("status", "Active");
        return account;
    }
}
