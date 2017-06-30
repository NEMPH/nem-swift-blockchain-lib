package io.nem.model;

import org.nem.core.model.Message;

public class SwiftMessage extends Message {
	
	private String swiftPayload;
	
	public String getSwiftPayload() {
		return swiftPayload;
	}

	public void setSwiftPayload(String swiftPayload) {
		this.swiftPayload = swiftPayload;
	}

	protected SwiftMessage(int type) {
		super(type);
	}
	
	protected SwiftMessage(int type, String swiftPayload) {
		super(type);
		this.swiftPayload = swiftPayload;
	}

	@Override
	public boolean canDecode() {
		return true;
	}

	@Override
	public byte[] getDecodedPayload() {
		return swiftPayload.getBytes();
	}

	@Override
	public byte[] getEncodedPayload() {
		return swiftPayload.getBytes();
	}
	
}
