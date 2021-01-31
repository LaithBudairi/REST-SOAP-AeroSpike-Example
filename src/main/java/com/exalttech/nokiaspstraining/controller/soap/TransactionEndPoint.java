package com.exalttech.nokiaspstraining.controller.soap;

import com.roytuts.jaxb.GetTransactionDetailsRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class TransactionEndPoint {
    private final String NAMESPACE = "https://www.w3schools.com";

    @PayloadRoot(namespace = NAMESPACE, localPart = "getTransactionDetailsRequest")
    @ResponsePayload
    public void createTransaction(@RequestPayload final GetTransactionDetailsRequest request) {
        System.out.println("soap req");
    }
}