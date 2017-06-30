package io.nem.model;

public class MessageFactory {
	
	public static SwiftMessage createSwiftMessage(int type, String payload){
		return new SwiftMessage(type,payload);
	}
}
