package com.exalttech.nokiaspstraining.controller.soap;

import com.exalttech.nokiaspstraining.Service.TransactionService;
import com.roytuts.jaxb.GetTransactionDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@Endpoint
public class TransactionEndPoint {
    private final String NAMESPACE = "http://techprimers.com/spring-boot-soap-example";

    @Autowired
    TransactionService transactionService;

    @PayloadRoot(namespace = NAMESPACE, localPart = "getTransactionDetailsRequest")
    public void getTransactionDetailsRequest(@RequestPayload GetTransactionDetailsRequest request) {
        transactionService.createTransaction(request);
    }
}