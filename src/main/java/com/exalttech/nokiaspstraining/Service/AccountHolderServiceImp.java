package com.exalttech.nokiaspstraining.Service;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.query.Filter;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import com.exalttech.nokiaspstraining.model.AccountHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountHolderServiceImp implements AccountHolderService {

    @Autowired
    AerospikeClient aerospikeClient;

    private final String namespace = "test";

    @Override
    public List<AccountHolder> getAllAccountHolders() {
        Statement stmt = new Statement();
        stmt.setNamespace(namespace);
        stmt.setSetName("bar");
        RecordSet records =  aerospikeClient.query(null, stmt);

        System.out.println(records);
        return null;
    }

    @Override
    public AccountHolder getAccountHolder(String id) {
        WritePolicy writePolicy = new WritePolicy();
        writePolicy.setTimeout(3);

        Key key = new Key(namespace, "account_holder", id);

        Record record = aerospikeClient.get(writePolicy, key);

        System.out.println(record);

        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setId(id);
        accountHolder.setFirstName(record.getString("firstName"));
        accountHolder.setLastName(record.getString("lastName"));
        accountHolder.setBalance(record.getDouble("balance"));



        return accountHolder;
    }

    @Override
    public boolean createNewAccount(AccountHolder accountHolder) {
        WritePolicy writePolicy = new WritePolicy();
        writePolicy.setTimeout(3);


        Key key = new Key(namespace, "account_holder", accountHolder.getId());
        Bin firstName = new Bin("firstName", accountHolder.getFirstName());
        Bin lastName = new Bin("lastName", accountHolder.getLastName());
        Bin balance = new Bin("balance", accountHolder.getBalance());

        aerospikeClient.put(writePolicy, key, firstName, lastName, balance);


        return true;
    }
}
