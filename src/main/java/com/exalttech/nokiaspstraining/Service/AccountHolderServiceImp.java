package com.exalttech.nokiaspstraining.Service;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.QueryPolicy;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import com.exalttech.nokiaspstraining.model.AccountHolder;
import com.exalttech.nokiaspstraining.repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountHolderServiceImp implements AccountHolderService {
    @Autowired
    AerospikeClient aerospikeClient;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    private final String namespace = "test";

    //todo Use AeroSpike-Spring-Data instead of AeroSpike-Client
    @Override
    public List<AccountHolder> getAllAccountHolders() {
        QueryPolicy queryPolicy = new QueryPolicy();
        queryPolicy.sendKey = true;

        Statement stmt = new Statement();
        stmt.setNamespace(namespace);
        stmt.setSetName("account_holder");
        RecordSet records =  aerospikeClient.query(queryPolicy, stmt);

        List<AccountHolder> list = new ArrayList<>();

        while(records.next()) {
            Record current = records.getRecord();

            AccountHolder accountHolder = new AccountHolder();
            accountHolder.setFirstName(current.getString("firstName"));
            accountHolder.setLastName(current.getString("lastName"));
            accountHolder.setBalance(current.getDouble("balance"));

            list.add(accountHolder);
        }
//        List<AccountHolder> list = new ArrayList<AccountHolder>();
//        accountHolderRepository.findAll();
        return list;
    }

    @Override
    public AccountHolder getAccountHolder(String id) {
       return  accountHolderRepository.findById(id).get();
    }

    @Override
    public boolean createNewAccount(AccountHolder accountHolder) {
        accountHolderRepository.save(accountHolder);

        return true;
    }

    @Override
    public boolean updateBalance(String id, double amount) {
        Key key = new Key(namespace, "account_holder", id);
        Record record = aerospikeClient.get(null, key, "balance");
        Bin balance = new Bin("balance", amount + record.getDouble("balance"));
        aerospikeClient.put(null, key, balance);

        return true;
    }
}