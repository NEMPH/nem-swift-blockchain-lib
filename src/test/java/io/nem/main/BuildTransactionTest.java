package io.nem.main;

import org.junit.Test;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.model.Account;
import org.nem.core.model.Address;

import io.nem.builders.TransactionBuilder;
import io.nem.model.AttachmentFactory;
import io.nem.model.EntityFactory;
import io.nem.model.MessageFactory;

public class BuildTransactionTest {

	@Test
	public void testCbBuildTransaction() {

		// Build a transaction.
		TransactionBuilder.getInstance()
				.setAmount(0l)
				.setRecipient(EntityFactory.createAccount(""))
				.setSender(EntityFactory.createAccount(""))
				.setAttachment(AttachmentFactory
						.createTransferTransactionAttachment(MessageFactory.createSwiftMessage(0, "message")))
				.buildTransaction(); // build only.

		// Build a transaction and send it.
		TransactionBuilder.getInstance().setAmount(0l)
				.setRecipient(EntityFactory.createAccount(""))
				.setSender(EntityFactory.createAccount(""))
				.setAttachment(AttachmentFactory
						.createTransferTransactionAttachment(MessageFactory.createSwiftMessage(0, "payload message")))
				.buildAndSendTransaction(); // build and send it!

	}
	


}
