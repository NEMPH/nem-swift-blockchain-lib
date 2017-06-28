package io.nem.converters;

import java.io.IOException;

import com.prowidesoftware.swift.io.parser.SwiftParser;
import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.mt.AbstractMT;
import io.nem.parsers.BlockchainParser;

public class Converter {

	public static String toAsciiText(String swiftMessage) {

		try {
			AbstractMT mt = AbstractMT.parse(swiftMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BlockchainParser blockchainParser = BlockchainParser.getInstance();

		// parsers
		return blockchainParser.parse(swiftMessage).toTextAscii();

	}

	public static SwiftMessage toSwiftMessage(String asciiText) {
		SwiftParser swiftParser = new SwiftParser();
		try {
			return swiftParser.parse(asciiText);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
