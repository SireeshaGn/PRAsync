package com.wipro.ms.payment.web;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ms.payment.domain.PaymentRequest;
import com.wipro.ms.payment.domain.PaymentResponse;
import com.wipro.ms.payment.service.PaymentService;

@RestController
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@RequestMapping(path="/api/payment/v1/process",method=RequestMethod.POST)
	@ResponseBody
	public PaymentResponse makePayemnt(@RequestBody PaymentRequest payReq)
	{
		Logger.getLogger("****PaymentController**:"+payReq.getOrderId()+":"+payReq.getAmount());
		String transactionId = paymentService.makePayemnt(payReq.getOrderId(), payReq.getAmount());
		PaymentResponse payRes = new PaymentResponse();
		payRes.setTransactionId(transactionId);
		return payRes;
	}
	
	@RequestMapping(path="/api/payment/process",method=RequestMethod.GET)
	@ResponseBody
	public String processPayment(@RequestParam String orderId,@RequestParam  double amount)
	{
		Logger.getLogger("****PaymentController**:"+orderId+":"+amount);
		String transactionId = paymentService.makePayemnt(orderId, amount);
		return transactionId;
	}
	
	@RequestMapping(path="/api/payment/test",method=RequestMethod.POST)
	public String payHello()
	{
		return "Welcome to PayementController";
	}
	
	@RequestMapping(path="/api/payment/creditPayment",method=RequestMethod.POST)
	public String creditPayment(@RequestParam String txnId)
	{
		String crdeitTxn="CR"+UUID.randomUUID().toString();
		return crdeitTxn;
	}
	
	
}
