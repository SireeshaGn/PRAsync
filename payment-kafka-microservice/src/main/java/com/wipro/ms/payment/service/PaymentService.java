package com.wipro.ms.payment.service;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class PaymentService{

	public String makePayemnt(String orderId,double amount) {
		// TODO Auto-generated method stub
		String txnId="Txn"+UUID.randomUUID().toString();
		return txnId;
	}
	
	public String reversePaymentTxn(String txnId){
		String ctxnId= "CR"+UUID.randomUUID().toString();
		
		return ctxnId;
	}
	
	
}
