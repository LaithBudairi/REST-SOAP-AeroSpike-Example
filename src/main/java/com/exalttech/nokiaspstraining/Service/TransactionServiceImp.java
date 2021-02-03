package com.exalttech.nokiaspstraining.Service;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.WritePolicy;
import com.roytuts.jaxb.GetTransactionDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImp implements TransactionService{
    @Autowired
    AerospikeClient aerospikeClient;

    private final String namespace = "test";

    @Override
    public boolean createTransaction(GetTransactionDetailsRequest getTransactionDetailsRequest) {

        WritePolicy writePolicy = new WritePolicy();
        writePolicy.setTimeout(3);

        Key key = new Key(namespace, "transaction", getTransactionDetailsRequest.getId());
        Bin from = new Bin("from", getTransactionDetailsRequest.getFrom());
        Bin to = new Bin("to", getTransactionDetailsRequest.getTo());
        Bin amount = new Bin("amount", getTransactionDetailsRequest.getAmount());
        aerospikeClient.put(writePolicy, key, from, to, amount);

        return true;
    }
}
