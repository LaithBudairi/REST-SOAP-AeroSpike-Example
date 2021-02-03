package com.exalttech.nokiaspstraining;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.exalttech.nokiaspstraining.config.DatabaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
        return new AerospikeClient(databaseConfig.getHost(), databaseConfig.getPort());
    }

}
