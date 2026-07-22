package com.cognizant.loan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoanController {

    @GetMapping("/loans/{id}")
    public Map<String, Object> getLoan(@PathVariable String id) {
        Map<String, Object> loan = new HashMap<>();
        loan.put("loanId", id);
        loan.put("loanType", "Home Loan");
        loan.put("amount", 2500000.0);
        loan.put("tenureMonths", 240);
        loan.put("status", "Approved");
        return loan;
    }
}
