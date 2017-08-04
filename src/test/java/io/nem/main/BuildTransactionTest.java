package io.nem.main;

import java.io.IOException;

import org.junit.Test;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.PlainMessage;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.HashMetaData;
import org.nem.core.model.HashMetaDataPair;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.observers.TransactionHashesNotification;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.test.Utils;
import org.nem.core.utils.Base32Encoder;

import com.prowidesoftware.swift.io.ConversionService;

import io.nem.builders.SwiftBlockchainTransactionBuilder;
import io.nem.factories.AttachmentFactory;
import io.nem.factories.EntityFactory;
import io.nem.model.TransactionMessageType;
import io.nem.util.GzipUtils;

/**
 * The Class BuildTransactionTest.
 */
public class BuildTransactionTest {

	/** The sample swift msg. */
	final String sampleSwiftMsg = "{1:F21FOOLHKH0AXXX0304009999}{4:{177:1608140809}{451:0}}{1:F01FOOLHKH0AXXX0304009999}{2:O9401609160814FOOLHKH0AXXX03040027341608141609N}{4:\n"
			+ ":20:USD940NO1\n" + ":21:123456/DEV\n" + ":25:USD234567\n" + ":28C:1/1\n" + ":60F:C160418USD672,\n"
			+ ":61:160827C642,S1032\n" + ":86:ANDY\n" + ":61:160827D42,S1032\n" + ":86:BANK CHARGES\n"
			+ ":62F:C160418USD1872,\n" + ":64:C160418USD1872,\n" + "-}{5:{CHK:0FEC1E4AEC53}{TNG:}}{S:{COP:S}}";

	/**
	 * Test transaction hash.
	 */
	@Test
	public void testTransactionHash() {

		// TransactionHashesNotification notification = new
		// TransactionHashesNotification(pairs);
	}

	/**
	 * Test cb build transaction.
	 */
	@Test
	public void testCbBuildTransaction() {

		// Build a transaction.
		SwiftBlockchainTransactionBuilder.getInstance()
				.setRecipient(
						EntityFactory.buildAccount("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec"))
				.setSender(
						EntityFactory.buildAccount("c9d930757f69584fc414d0b2b54a0c3aa064996f9b13b70d32c89879724153c1"))
				.buildTransaction(); // build only.
	}

	/**
	 * Test cb build and send transaction WO attachment.
	 */
	@Test
	public void testCbBuildAndSendTransactionWOAttachment() {
		// Build a transaction and send it.
		try {
			SwiftBlockchainTransactionBuilder.getInstance()
					.setRecipient(EntityFactory
							.buildAccount("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec"))
					.setSender(EntityFactory
							.buildAccount("c9d930757f69584fc414d0b2b54a0c3aa064996f9b13b70d32c89879724153c1"))
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
					.buildAccount("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec");
			final Account recipientAccount = EntityFactory
					.buildAccount("c9d930757f69584fc414d0b2b54a0c3aa064996f9b13b70d32c89879724153c1");

			final SecureMessage message = SecureMessage.fromDecodedPayload(senderAccount, recipientAccount,
					sampleSwiftMsg.getBytes());

			SwiftBlockchainTransactionBuilder.getInstance().setAmount(0l).setSender(senderAccount)
					.setRecipient(recipientAccount)
					.setAttachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendTransaction(); // build and send it!
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
					.buildAccount("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec");
			final Account recipientAccount = EntityFactory
					.buildAccount("c9d930757f69584fc414d0b2b54a0c3aa064996f9b13b70d32c89879724153c1");

			final SecureMessage message = SecureMessage.fromDecodedPayload(senderAccount, recipientAccount,
					new ConversionService().getXml(this.sampleSwiftMsg, true).getBytes());

			SwiftBlockchainTransactionBuilder.getInstance().setSender(senderAccount).setRecipient(recipientAccount)
					.setAttachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendTransaction(); // build and send it!
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
					.buildAccount("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec");
			final Account recipientAccount = EntityFactory
					.buildAccount("c9d930757f69584fc414d0b2b54a0c3aa064996f9b13b70d32c89879724153c1");
			
			

			final SecureMessage message = SecureMessage.fromDecodedPayload(senderAccount, recipientAccount,
					new ConversionService().getXml(this.sampleSwiftMsg, true).getBytes());
			
			TransferTransactionAttachment attachment = new TransferTransactionAttachment(message);
			attachment.addMosaic(Utils.createMosaic(1).getMosaicId(), new Quantity(12));
			
			SwiftBlockchainTransactionBuilder.getInstance().setSender(senderAccount).setRecipient(recipientAccount)
					.setAttachment(attachment)
					.buildAndSendTransaction(); // build and send it!
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test cb build and send swift file transaction.
	 */
	@Test
	public void testCbBuildAndSendSwiftFileTransaction() {

		// test from a string.
		final Account senderAccount = EntityFactory
				.buildAccount(Address.fromEncoded(Base32Encoder.getString("MDVJCH-6F5FXV-UOFCC3-PZTSXP-QNPCUL-YQMWEG-AOOW".getBytes())));
		final Account recipientAccount = EntityFactory
				.buildAccount(Address.fromEncoded(Base32Encoder.getString("MDYSYW-VWGC6J-DD7BGE-4JBZMU-EM5KXD-Z7J77U-4X2Y".getBytes())));
		
		PlainMessage message = null;
		try {
			message = new PlainMessage(
					GzipUtils.compress(sampleSwiftMsg));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Build a transaction and send it.
		try {
			SwiftBlockchainTransactionBuilder.getInstance().setSender(senderAccount).setRecipient(recipientAccount)
					.setAttachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendTransaction(); // build and send it!
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
