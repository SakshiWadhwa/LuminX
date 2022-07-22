package com.uwindsor.ase.lightpollution.dto;

public class MessageResponse {

	private String Message;
	
	public MessageResponse() {}

	public MessageResponse(String message) {
		super();
		Message = message;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}
}
