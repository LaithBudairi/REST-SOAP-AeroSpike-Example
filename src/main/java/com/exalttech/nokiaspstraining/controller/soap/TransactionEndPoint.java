package com.exalttech.nokiaspstraining.controller.soap;
import com.roytuts.jaxb.GetTransactionDetailsRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@Endpoint
public class TransactionEndPoint {
    private final String NAMESPACE = "http://techprimers.com/spring-boot-soap-example";

    @PayloadRoot(namespace = NAMESPACE, localPart = "getTransactionDetailsRequest")
    public void getTransactionDetailsRequest(@RequestPayload GetTransactionDetailsRequest request) {
        System.out.println("soap req");
    }
}