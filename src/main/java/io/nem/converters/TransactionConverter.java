package io.nem.converters;

import java.io.IOException;

import com.prowidesoftware.swift.io.parser.SwiftParser;
import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.mt.AbstractMT;

public class TransactionConverter {

	public static String toAsciiText(String swiftMessage) {

		try {
			AbstractMT mt = AbstractMT.parse(swiftMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return swiftMessage;
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
