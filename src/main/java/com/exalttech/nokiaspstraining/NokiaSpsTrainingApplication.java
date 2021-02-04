package com.exalttech.nokiaspstraining;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.Language;
import com.exalttech.nokiaspstraining.config.DatabaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class NokiaSpsTrainingApplication {

    @Autowired
    DatabaseConfig databaseConfig;

    public static void main(String[] args) {
        SpringApplication.run(NokiaSpsTrainingApplication.class, args);
    }

    @Bean
    public AerospikeClient asClient() throws AerospikeException {
        AerospikeClient client = new AerospikeClient(databaseConfig.getHost(), databaseConfig.getPort());
        client.register(null, Thread.currentThread().getContextClassLoader(), "udf/transaction.lua", "transaction.lua", Language.LUA);
        return client;
    }

}
