package io.nem.parsers;

import com.prowidesoftware.swift.model.SwiftMessage;

public class BlockchainParser  {

	private static BlockchainParser instance;

	
	public static BlockchainParser getInstance() {
		if(instance == null) {
			instance = new BlockchainParser();
		}
		return null;
	}

	public BlockchainParser parse(SwiftMessage swiftMessage) {
		return null;
	}

	public BlockchainParser parse(String swiftMessage) {
		return null;
	}
	
	public String toJson() {return null;};
	public String toTextAscii()  {return null;};
	public String toXml()  {return null;};
}
