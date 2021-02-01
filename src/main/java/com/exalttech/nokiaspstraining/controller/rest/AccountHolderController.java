package com.exalttech.nokiaspstraining.controller.rest;

import com.exalttech.nokiaspstraining.Service.AccountHolderServiceImp;
import com.exalttech.nokiaspstraining.model.AccountHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class AccountHolderController {
    @Autowired
    AccountHolderServiceImp accountHolderService;

    @GetMapping("accountHolder")
    public List<AccountHolder> getAllAccountHolders() {
        return accountHolderService.getAllAccountHolders();
    }

    @GetMapping("accountHolder/{id}")
    public AccountHolder getAccountHolder(@PathVariable(value = "id") String id) {
        return accountHolderService.getAccountHolder(id);
    }

    @PostMapping(value = "accountHolder")
    public boolean createNewAccount(@RequestBody AccountHolder accountHolder) {
        return accountHolderService.createNewAccount(accountHolder);
    }

}