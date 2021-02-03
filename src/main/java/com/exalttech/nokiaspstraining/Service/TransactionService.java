package com.exalttech.nokiaspstraining.Service;

import com.roytuts.jaxb.GetTransactionDetailsRequest;

public interface TransactionService {
    boolean createTransaction(GetTransactionDetailsRequest getTransactionDetailsRequest);
}
