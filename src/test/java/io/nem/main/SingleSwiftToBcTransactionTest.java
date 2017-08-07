package io.nem.main;

import org.junit.Test;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.TransferTransactionAttachment;
import io.nem.builders.SwiftBlockchainTransactionBuilder;
import io.nem.factories.AttachmentFactory;
import io.nem.factories.EntityFactory;
import io.nem.util.GzipUtils;

public class SingleSwiftToBcTransactionTest {
	final String sampleSwiftMsg = "{1:F21FOOLHKH0AXXX0304009999}{4:{177:1608140809}{451:0}}{1:F01FOOLHKH0AXXX0304009999}{2:O9401609160814FOOLHKH0AXXX03040027341608141609N}{4:\n"
			+ ":20:USD940NO1\n" + ":21:123456/DEV\n" + ":25:USD234567\n" + ":28C:1/1\n" + ":60F:C160418USD672,\n"
			+ ":61:160827C642,S1032\n" + ":86:ANDY\n" + ":61:160827D42,S1032\n" + ":86:BANK CHARGES\n"
			+ ":62F:C160418USD1872,\n" + ":64:C160418USD1872,\n" + "-}{5:{CHK:0FEC1E4AEC53}{TNG:}}{S:{COP:S}}";

	@Test
	public void testCbBuildAndSendSwiftTransactionWithMosaic() {

		// Build a transaction and send it.
		try {

			// test from a string.
			final Account senderAccount = EntityFactory
					.buildAccountFromPrivateKey("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec");
			final Account recipientAccount = EntityFactory
					.buildAccountFromPublicKey("8043f36622be5c91e00d9977c870935c887ff9050ba0a62207db76dba1a87385");

			final SecureMessage message = SecureMessage.fromDecodedPayload(senderAccount, recipientAccount,GzipUtils.compress(this.sampleSwiftMsg));

			TransferTransactionAttachment attachment = new TransferTransactionAttachment(message);
			//attachment.addMosaic(Utils.createMosaic(1).getMosaicId(), new Quantity(12));

			SwiftBlockchainTransactionBuilder.getInstance()
					.setSender(senderAccount)
					.setRecipient(recipientAccount)
					.setAttachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendTransaction(); // build
																			// and
																			// send
																			// it!
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void testCbBuildAndSendSwiftTransactionWithMosaic1() {

		// Build a transaction and send it.
		try {

			// test from a string.
			final Account senderAccount = EntityFactory
					.buildAccountFromPrivateKey("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec");
			final Account recipientAccount = EntityFactory
					.buildAccountFromPublicKey("8043f36622be5c91e00d9977c870935c887ff9050ba0a62207db76dba1a87385");

			final SecureMessage message = SecureMessage.fromDecodedPayload(senderAccount, recipientAccount,GzipUtils.compress(this.sampleSwiftMsg));

			TransferTransactionAttachment attachment = new TransferTransactionAttachment(message);
			//attachment.addMosaic(Utils.createMosaic(1).getMosaicId(), new Quantity(12));

			SwiftBlockchainTransactionBuilder.getInstance()
					.setSender(senderAccount)
					.setRecipient(recipientAccount)
					.setAttachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendTransaction(); // build
																			// and
																			// send
																			// it!
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
