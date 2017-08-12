package io.nem.main;

import org.junit.Test;
import org.nem.core.messages.PlainMessage;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.test.Utils;
import com.prowidesoftware.swift.io.ConversionService;
import io.nem.builders.SwiftBlockchainTransactionBuilder;
import io.nem.factories.AttachmentFactory;
import io.nem.factories.EntityFactory;
import io.nem.swift.crypto.SecureMessageSwiftPayloadEncoder;

/**
 * The Class BuildTransactionTest.
 */
public class EncodeBuildMultisigSignedTransactionTest {

	/** The sample swift msg. */
	final String sampleSwiftMsg = "{1:F21FOOLHKH0AXXX0304009999}{4:{177:1608140809}{451:0}}{1:F01FOOLHKH0AXXX0304009999}{2:O9401609160814FOOLHKH0AXXX03040027341608141609N}{4:\n"
			+ ":20:USD940NO1\n" + ":21:123456/DEV\n" + ":25:USD234567\n" + ":28C:1/1\n" + ":60F:C160418USD672,\n"
			+ ":61:160827C642,S1032\n" + ":86:ANDY\n" + ":61:160827D42,S1032\n" + ":86:BANK CHARGES\n"
			+ ":62F:C160418USD1872,\n" + ":64:C160418USD1872,\n" + "-}{5:{CHK:0FEC1E4AEC53}{TNG:}}{S:{COP:S}}";


	/**
	 * Test cb build and send transaction.
	 */
	@Test
	public void testCbBuildAndSendSwiftStringTransaction() {

		// Build a transaction and send it.
		try {

			// test from a string.
			final Account senderAccount = EntityFactory
					.buildAccountFromPrivateKey("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec");
			final Account recipientAccount = EntityFactory
					.buildAccountFromPublicKey("2a3e60b33982593ed436979066d44dee626695b9348d91c625cef17b807fd4ac");
			final Account multiSig = EntityFactory
					.buildAccountFromPublicKey("10a10f929a30dd1d062ef5cb337a1d54c35fe8a25e7fd3699c12e5a74e338328");

			final SecureMessage message = SecureMessage.fromDecodedPayload(senderAccount, recipientAccount,
					sampleSwiftMsg.getBytes());
			
			final PlainMessage msg = new PlainMessage(sampleSwiftMsg.getBytes());

			SwiftBlockchainTransactionBuilder.getInstance()
					.setAmount(0l)
					.setSender(senderAccount)
					.setMultisig(multiSig)
					.setRecipient(recipientAccount)
					.setAttachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendMultisigTransaction(); // build and send
																// it!
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test cb build and send swift string XML transaction.
	 */
	@Test
	public void testCbBuildAndSendSwiftStringXMLTransaction() {

		// Build a transaction and send it.
		try {

			// test from a string.
			final Account senderAccount = EntityFactory
					.buildAccountFromPrivateKey("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec");
			final Account recipientAccount = EntityFactory
					.buildAccountFromPublicKey("2a3e60b33982593ed436979066d44dee626695b9348d91c625cef17b807fd4ac");
			final Account multiSig = EntityFactory
					.buildAccountFromPublicKey("10a10f929a30dd1d062ef5cb337a1d54c35fe8a25e7fd3699c12e5a74e338328");
			
			final SecureMessage message = SecureMessage.fromDecodedPayload(senderAccount, recipientAccount,
					new ConversionService().getXml(this.sampleSwiftMsg, true).getBytes());
			
			SwiftBlockchainTransactionBuilder.getInstance()
					.setSender(senderAccount)
					.setRecipient(recipientAccount)
					.setMultisig(multiSig)
					.setAmount(0l)
					.setAttachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendMultisigTransaction();
																// it!
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test cb build and send swift transaction with mosaic.
	 */
	@Test
	public void testCbBuildAndSendSwiftTransactionWithMosaic() {

		// Build a transaction and send it.
		try {

			// test from a string.
			final Account senderAccount = EntityFactory
					.buildAccountFromPrivateKey("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec");
			final Account recipientAccount = EntityFactory
					.buildAccountFromPublicKey("2a3e60b33982593ed436979066d44dee626695b9348d91c625cef17b807fd4ac");
			final Account multiSig = EntityFactory
					.buildAccountFromPublicKey("10a10f929a30dd1d062ef5cb337a1d54c35fe8a25e7fd3699c12e5a74e338328");
			
			SecureMessage message = SecureMessageSwiftPayloadEncoder.encodeAndGzipCompress(senderAccount,
					recipientAccount, sampleSwiftMsg);
			TransferTransactionAttachment attachment = new TransferTransactionAttachment(message);
			attachment.addMosaic(Utils.createMosaic(1).getMosaicId(), new Quantity(12));

			SwiftBlockchainTransactionBuilder.getInstance().setSender(senderAccount).setRecipient(recipientAccount).setMultisig(multiSig)
					.setAttachment(attachment).buildAndSendMultisigTransaction(); // build
			// and
			// send
			// it!
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test cb build and send swift file transaction.
	 */
	@Test
	public void testCbBuildAndSendSwiftFileTransaction() {
		
		final Account senderAccount = EntityFactory
				.buildAccountFromPrivateKey("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec");
		final Account recipientAccount = EntityFactory
				.buildAccountFromPublicKey("2a3e60b33982593ed436979066d44dee626695b9348d91c625cef17b807fd4ac");
		final Account multiSig = EntityFactory
				.buildAccountFromPublicKey("10a10f929a30dd1d062ef5cb337a1d54c35fe8a25e7fd3699c12e5a74e338328");
		
		SecureMessage message = null;
		try {
			message = SecureMessage.fromDecodedPayload(senderAccount, recipientAccount, sampleSwiftMsg.getBytes());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// Build a transaction and send it.
		try {
			SwiftBlockchainTransactionBuilder.getInstance().setSender(senderAccount).setRecipient(recipientAccount).setMultisig(multiSig)
					.setAttachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendMultisigTransaction();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
