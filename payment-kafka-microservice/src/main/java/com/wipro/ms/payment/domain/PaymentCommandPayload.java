package com.wipro.ms.payment.domain;

public class PaymentCommandPayload {
	
	private String refId;
	private double amount;
	
	
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
	

}
