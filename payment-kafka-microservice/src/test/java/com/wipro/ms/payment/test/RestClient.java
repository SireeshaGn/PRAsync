package com.wipro.ms.payment.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class RestClient {
	
RestTemplate restTemplate;
	
	public RestClient(){
		restTemplate = new RestTemplate();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestClient client = new RestClient();
		client.getEntity();
	}
	
	
	public void getEntity(){
		System.out.println("Begin /GET request!");
		//String getUrl = "http://localhost:9084/api/payment/process?orderId=2000&amount=500";
		String getUrl = "http://localhost:9084/api/payment/process";
		String restUrl = UriComponentsBuilder.fromUriString(getUrl).queryParam("orderId", 2000).queryParam("amount", 800).build().toUri().toString();
		System.out.println("restUrl:"+restUrl);
		ResponseEntity<String> getResponse = restTemplate.getForEntity(restUrl, String.class);
		if(getResponse.getBody() != null){
			System.out.println("Response for Get Request: " + getResponse.getBody().toString());	
		}else{
			System.out.println("Response for Get Request: NULL");
		}
	}

}
