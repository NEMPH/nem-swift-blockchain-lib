package io.nem.main;
import org.junit.Test;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import io.nem.builders.SwiftBlockchainTransactionBuilder;
import io.nem.factories.AttachmentFactory;
import io.nem.factories.EntityFactory;
import io.nem.model.TransactionMessageType;

/**
 * The Class BuildTransactionTest.
 */
public class BuildTransactionTest {

	final String sampleSwiftMsg = "{1:F21FOOLHKH0AXXX0304009999}{4:{177:1608140809}{451:0}}{1:F01FOOLHKH0AXXX0304009999}{2:O9401609160814FOOLHKH0AXXX03040027341608141609N}{4:\n"+
			":20:USD940NO1\n"+
			":21:123456/DEV\n"+
			":25:USD234567\n"+
			":28C:1/1\n"+
			":60F:C160418USD672,\n"+
			":61:160827C642,S1032\n"+
			":86:ANDY\n"+
			":61:160827D42,S1032\n"+
			":86:BANK CHARGES\n"+
			":62F:C160418USD1872,\n"+
			":64:C160418USD1872,\n"+
			"-}{5:{CHK:0FEC1E4AEC53}{TNG:}}{S:{COP:S}}";
	/**
	 * Test cb build transaction.
	 */
	@Test
	public void testCbBuildTransaction() {

		// Build a transaction.
		SwiftBlockchainTransactionBuilder.getInstance().setAmount(2l)
				.setRecipient(
						EntityFactory.createAccount("498664896462446e683ecb04468c9d75807495f2bacf08a76ca90695a38c1539"))
				.setSender(
						EntityFactory.createAccount("22fee844f2c07ce8b3f02b2373897f998ebd13f64db1ef96eb006cfd7d3c85e5"))
				.buildTransaction(); // build only.
	}

	@Test
	public void testCbBuildAndSendTransactionWOAttachment() {
		// Build a transaction and send it.
		try {
			SwiftBlockchainTransactionBuilder.getInstance().setAmount(1l)
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

		// Build a transaction and send it.
		try {

			// test from a string.
			final Account senderAccount = EntityFactory
					.createAccount("22fee844f2c07ce8b3f02b2373897f998ebd13f64db1ef96eb006cfd7d3c85e5");
			final Account recipientAccount = EntityFactory
					.createAccount("498664896462446e683ecb04468c9d75807495f2bacf08a76ca90695a38c1539");

			final SecureMessage message = SecureMessage.fromDecodedPayload(senderAccount, recipientAccount,
					sampleSwiftMsg.getBytes());

			SwiftBlockchainTransactionBuilder.getInstance().setAmount(1l).setSender(senderAccount)
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

		final SecureMessage message = SecureMessage.fromDecodedPayload(senderAccount, recipientAccount,
				sampleSwiftMsg.getBytes());
		// Build a transaction and send it.
		try {
			SwiftBlockchainTransactionBuilder.getInstance().setAmount(10l).setSender(senderAccount)
					.setRecipient(recipientAccount).setTransactionMessageType(TransactionMessageType.SWIFT)
					.setAttachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendTransaction(); // build and send it!
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
