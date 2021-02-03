package com.exalttech.nokiaspstraining.controller.soap;
import com.aerospike.client.AerospikeClient;
import com.exalttech.nokiaspstraining.Service.AccountHolderService;
import com.exalttech.nokiaspstraining.Service.TransactionService;
import com.roytuts.jaxb.GetTransactionDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@Endpoint
public class TransactionEndPoint {
    private final String NAMESPACE = "http://techprimers.com/spring-boot-soap-example";

    @Autowired
    AccountHolderService accountHolderService;

    @Autowired
    TransactionService transactionService;

    @PayloadRoot(namespace = NAMESPACE, localPart = "getTransactionDetailsRequest")
    public void getTransactionDetailsRequest(@RequestPayload GetTransactionDetailsRequest request) {

        //todo fix update
        accountHolderService.updateBalance(request.getFrom(), request.getAmount() * -1);
        accountHolderService.updateBalance(request.getTo(), request.getAmount());

        transactionService.createTransaction(request);
    }
}