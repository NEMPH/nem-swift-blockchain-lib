package io.nem.main;

import org.junit.Test;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;

import io.nem.builders.BlockchainTransactionBuilder;
import io.nem.factories.AttachmentFactory;
import io.nem.factories.EntityFactory;

/**
 * The Class BuildTransactionTest.
 */
public class BuildTransactionTest {

	private String sampleSwiftMsg = "{1:F01BICFOOYYAXXX8683497519}{2:O1031535051028ESPBESMMAXXX54237522470510281535N}{3:{113:ROMF}{108:0510280182794665}{119:STP}}{4: :20:0061350113089908 :13C:/RNCTIME/1534+0000 :23B:CRED :23E:SDVA :32A:061028EUR100000, :33B:EUR100000, :50K:/12345678 AGENTES DE BOLSA FOO AGENCIA AV XXXXX 123 BIS 9 PL 12345 BARCELONA :52A:/2337 FOOAESMMXXX :53A:FOOAESMMXXX :57A:BICFOOYYXXX :59:/ES0123456789012345671234 FOO AGENTES DE BOLSA ASOC :71A:OUR :72:/BNF/TRANSF. BCO. FOO -}{5:{MAC:88B4F929}{CHK:22EF370A4073}} ";
	
	
	/**
	 * Test cb build transaction.
	 */
	// @Test
	public void testCbBuildTransaction() {

		// Build a transaction.
		BlockchainTransactionBuilder.getInstance().setAmount(2l)
				.setRecipient(
						EntityFactory.createAccount("498664896462446e683ecb04468c9d75807495f2bacf08a76ca90695a38c1539"))
				.setSender(
						EntityFactory.createAccount("22fee844f2c07ce8b3f02b2373897f998ebd13f64db1ef96eb006cfd7d3c85e5"))
				.buildTransaction(); // build only.
	}

	// @Test
	public void testCbBuildAndSendTransactionWOAttachment() {
		// Build a transaction and send it.
		try {
			BlockchainTransactionBuilder.getInstance().setAmount(0l)
					.setRecipient(EntityFactory
							.createAccount("498664896462446e683ecb04468c9d75807495f2bacf08a76ca90695a38c1539"))
					.setSender(EntityFactory
							.createAccount("22fee844f2c07ce8b3f02b2373897f998ebd13f64db1ef96eb006cfd7d3c85e5"))
					.buildAndSendTransaction(); // build and send it!
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test cb build and send transaction.
	 */
	@Test
	public void testCbBuildAndSendSwiftStringTransaction() {
		
		// test from a string.
		final Account senderAccount = EntityFactory
				.createAccount("22fee844f2c07ce8b3f02b2373897f998ebd13f64db1ef96eb006cfd7d3c85e5");
		final Account recipientAccount = EntityFactory
				.createAccount("498664896462446e683ecb04468c9d75807495f2bacf08a76ca90695a38c1539");
		
		final SecureMessage message = SecureMessage.fromDecodedPayload(
				senderAccount,
				recipientAccount,
				sampleSwiftMsg.getBytes());
		// Build a transaction and send it.
		try {
			BlockchainTransactionBuilder.getInstance().setAmount(100l)
					.setSender(senderAccount)
					.setRecipient(recipientAccount)
					.setAttachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendTransaction(); // build and send it!
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testCbBuildAndSendSwiftFileTransaction() {
		
		// test from a string.
		final Account senderAccount = EntityFactory
				.createAccount("22fee844f2c07ce8b3f02b2373897f998ebd13f64db1ef96eb006cfd7d3c85e5");
		final Account recipientAccount = EntityFactory
				.createAccount("498664896462446e683ecb04468c9d75807495f2bacf08a76ca90695a38c1539");
		
		final SecureMessage message = SecureMessage.fromDecodedPayload(
				senderAccount,
				recipientAccount,
				sampleSwiftMsg.getBytes());
		// Build a transaction and send it.
		try {
			BlockchainTransactionBuilder.getInstance().setAmount(100l)
					.setSender(senderAccount)
					.setRecipient(recipientAccount)
					.setAttachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendTransaction(); // build and send it!
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
