package io.nem.main;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;

import com.prowidesoftware.swift.model.mt.AbstractMT;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103_STP;

/**
 * The Class BuildTransactionTest.
 */
public class SwiftParserTest {

	private String sampleSwiftMsg = "";

	/**
	 * Test cb build transaction.
	 */
	@Test
	public void testSwiftParse() {

		try {
			AbstractMT mt = AbstractMT.parse(new File("src/test/resources/mt103.txt"));
			
			System.out.println(((MT103_STP)mt).getField32A().getAmount());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
