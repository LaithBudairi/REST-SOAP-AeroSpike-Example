package com.exalttech.nokiaspstraining.config;

import com.aerospike.client.Host;
import com.exalttech.nokiaspstraining.repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.config.AbstractAerospikeDataConfiguration;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableAerospikeRepositories(basePackageClasses = AccountHolderRepository.class)
class ApplicationConfig extends AbstractAerospikeDataConfiguration {

    @Autowired
    DatabaseConfig databaseConfig;

    @Override
    protected Collection<Host> getHosts() {
        return Collections.singleton(new Host(databaseConfig.getHost(), databaseConfig.getPort()));
    }

    @Override
    protected String nameSpace() {
        return "test";
    }

}