package com.wipro.ms.payment.message;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.ms.payment.adapter.PaymentReceivedCommnadPayload;
import com.wipro.ms.payment.domain.PaymentCommandPayload;
import com.wipro.ms.payment.service.PaymentService;

@Component
@EnableBinding(Sink.class)
public class MessageListener {

	@Autowired
	PaymentService paymentService;
	
	@Autowired
	MessageSender msgSender;
	
	 

	  @StreamListener(target = Sink.INPUT, 
	      condition="payload.messageType.toString()=='PaymentRequestCommand'")
	  @Transactional
	public void paymentReceived(String json) {

		try {
			java.util.logging.Logger.getLogger("===Payment Application msg listerner:"+json);
			System.out.println("Payment Service===:"+json);
			Message<PaymentCommandPayload> message = new ObjectMapper().readValue(json,
					new TypeReference<Message<PaymentCommandPayload>>() {
					});
			String paymentTxnId = paymentService.makePayemnt(message.getPayload().getRefId(), message.getPayload().getAmount());
			System.out.println("paymentTxnId:"+paymentTxnId+" RefId:"+message.getPayload().getRefId()+" TraceId:"+message.getTraceId());
			PaymentReceivedCommnadPayload paymentReceived = new PaymentReceivedCommnadPayload();
			//message.getPayload().getRefId(),paymentTxnId,message.getPayload().getAmount()
			paymentReceived.setRefId(message.getPayload().getRefId());
			paymentReceived.setTxnId(paymentTxnId);
			paymentReceived.setAmount(message.getPayload().getAmount());
			System.out.println("Payment service traceId:"+message.getTraceId());
			msgSender.send(new Message<PaymentReceivedCommnadPayload>("PaymentReceivedEvent",message.getTraceId(),paymentReceived));

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
