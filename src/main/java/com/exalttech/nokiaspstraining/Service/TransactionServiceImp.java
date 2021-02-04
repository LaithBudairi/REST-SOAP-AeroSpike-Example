package com.exalttech.nokiaspstraining.Service;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.roytuts.jaxb.GetTransactionDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImp implements TransactionService{
    @Autowired
    AerospikeClient aerospikeClient;

    @Autowired
    AccountHolderService accountHolderService;
    private final String namespace = "test";

    @Override
    public boolean createTransaction(GetTransactionDetailsRequest request) {
        accountHolderService.updateBalance(request.getFrom(), request.getAmount() * -1);
        accountHolderService.updateBalance(request.getTo(), request.getAmount());

        Key key = new Key(namespace, "transaction", request.getId());
        Bin from = new Bin("from", request.getFrom());
        Bin to = new Bin("to", request.getTo());
        Bin amount = new Bin("amount", request.getAmount());
        aerospikeClient.put(null, key, from, to, amount);

        return true;
    }
}
