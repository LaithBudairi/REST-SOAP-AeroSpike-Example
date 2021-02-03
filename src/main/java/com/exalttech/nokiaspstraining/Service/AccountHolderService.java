package com.exalttech.nokiaspstraining.Service;

import com.exalttech.nokiaspstraining.model.AccountHolder;

import java.util.List;

public interface AccountHolderService {

    List<AccountHolder> getAllAccountHolders();
    AccountHolder getAccountHolder(String id);
    boolean createNewAccount(AccountHolder accountHolder);
    boolean updateBalance(String id, double amount);
}
